package io.inceptor.drl.util;

import io.inceptor.drl.ast.DrlTreeListener;
import io.inceptor.drl.ast.DsTreeVisitor;
import io.inceptor.drl.drl.DeclaredClass;
import io.inceptor.drl.drl.ParsedDrlFile;
import io.inceptor.drl.drl.Rule;
import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.exceptions.ParseDrlRuntimeException;
import io.inceptor.drl.parser.DrlLexer;
import io.inceptor.drl.parser.DrlParser;
import io.inceptor.drl.parser.DsLexer;
import io.inceptor.drl.parser.DsParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.err;

public class DrlTool {
    static String VERSION = "2.0";

    public static void info(String info) {
        out.println(ConsoleColors.GREEN + info + ConsoleColors.RESET);
    }

    public static void red (String msg){
        out.println(ConsoleColors.RED_UNDERLINED + msg + ConsoleColors.RESET);
    }

    public static void err(String error) {
        err.println(error);
    }

    static DrlLexer lexer = new DrlLexer(null);

    static DrlParser parser = new DrlParser(null);

    static DrlTreeListener drlTreeListener = new DrlTreeListener();

    static ParseTreeWalker walker = new ParseTreeWalker();


    static DsLexer dsLexer = new DsLexer(null);

    static DsParser dsParser = new DsParser(null);

    static DsTreeVisitor dsVisitor = new DsTreeVisitor();

    static {
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer,
                                    Object offendingSymbol,
                                    int line,
                                    int charPositionInLine,
                                    String msg,
                                    RecognitionException e) {
                throw new ParseDrlRuntimeException("line " + line + ":" + charPositionInLine + " " + msg);
            }

        });
        dsParser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer,
                                    Object offendingSymbol,
                                    int line,
                                    int charPositionInLine,
                                    String msg,
                                    RecognitionException e) {
                throw new ParseDrlRuntimeException("line " + line + ":" + charPositionInLine + " " + msg);
            }

        });
    }


    public static ParsedDrlFile parse(InputStream is) throws IOException {
        lexer.setInputStream(CharStreams.fromStream(is));
        parser.setTokenStream(new CommonTokenStream(lexer));
        walker.walk(drlTreeListener, parser.file());
        return drlTreeListener.getParsedDrlFile();
    }

    public static Rule parseOneRule(InputStream in) throws IOException {
        lexer.setInputStream(CharStreams.fromStream(in));
        parser.setTokenStream(new CommonTokenStream(lexer));
        DrlParser.OneruleContext onerule = parser.onerule();
        return drlTreeListener.parseSingleRule(onerule);
    }

    public static List<Datasource> parseDs(InputStream is) throws IOException {
        dsLexer.setInputStream(CharStreams.fromStream(is));
        dsParser.setTokenStream(new CommonTokenStream(dsLexer));
        return dsVisitor.visit(dsParser.declares());
    }

    public static ParsedDrlFile fetchFromFs(String drl) {
        drl = drl.replaceAll("\\.(?!drl\b)", "/");

        ParsedDrlFile drlFile = findInFs(drl);

        return drlFile;
    }


    public static ParsedDrlFile findInFs(String drl) {
        return null;
    }

    public static void makeJarFromDrl(List<String> drlPaths, String location, String jarName) throws IOException {
        File file = new File(location, jarName);
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try (JarOutputStream jarOutputStream = new JarOutputStream(fileOutputStream)) {
            for (String drlPath : drlPaths) {
                File dir = new File(drlPath);
                if (!dir.exists())
                    throw new IOException(drlPath + " doesn't exist");
                if (!dir.isDirectory())
                    throw new IOException(drlPath + " not a directory");

                HashSet entries = new HashSet();
                Stack<File> stack = new Stack();
                stack.push(dir);
                while (!stack.isEmpty()) {
                    dir = stack.pop();
                    for (File drl : dir.listFiles()) {
                        if (drl.isDirectory()) {
                            stack.push(drl);
                            continue;
                        }
                        if (!drl.getName().endsWith(".drl"))
                            continue;
                        List<DeclaredClass> declaredClassList = DrlTool.parse(new FileInputStream(drl)).getDeclaredClasses();
                        for (DeclaredClass d : declaredClassList) {
                            DeclaredClass.DumpClassBytes dumpClassBytes = d.dumpClass();
                            String entryName = dumpClassBytes.packageName.replace(".", "/") + dumpClassBytes.name + ".class";
                            if (entries.contains(entryName))
                                continue;
                            entries.add(entryName);
                            JarEntry jarEntry = new JarEntry(entryName);
                            jarOutputStream.putNextEntry(jarEntry);
                            jarOutputStream.write(dumpClassBytes.bytes);
                            jarOutputStream.closeEntry();
                            jarOutputStream.flush();
                        }
                    }
                }
            }
        }
    }

    public static void packageDrlsInJar(List<String> drlPaths, String location, String jarName) throws IOException {
        File file = new File(location, jarName);
        if (file.exists())
            file.delete();
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();

        try (JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(file))) {
            for (String drlPath : drlPaths) {
                File drlDrl = new File(drlPath);
                if (!drlDrl.exists())
                    throw new RuntimeException("drl path doesn't exist");
                if (!drlDrl.isDirectory())
                    throw new RuntimeException("drl path doesn't refer to a directory");

                Stack<File> stack = new Stack<>();
                stack.push(drlDrl);
                byte[] bytes = new byte[1024];
                while (!stack.empty()) {
                    drlDrl = stack.pop();
                    for (File child : drlDrl.listFiles()) {
                        if (child.isDirectory()) {
                            stack.push(child);
                            continue;
                        }

                        if (!child.getName().endsWith(".drl")) {
                            if (child.getName().endsWith(".ds")) {
                                try (InputStream in = new FileInputStream(child); InputStream in2 = new FileInputStream(child)) {
                                    DrlTool.parseDs(in); //check the syntax of the ds file
                                    JarEntry jarEntry = new JarEntry(child.getName());
                                    jarOutputStream.putNextEntry(jarEntry);
                                    int res;
                                    while ((res = in2.read(bytes)) != -1) {
                                        jarOutputStream.write(bytes, 0, res);
                                    }

                                }
                            }
                            continue;
                        }


                        try (InputStream in = new FileInputStream(child); InputStream in2 = new FileInputStream(child)) {
                            ParsedDrlFile p = parse(in);
                            JarEntry jarEntry = new JarEntry(p.getLocation() + "/" + child.getName());
                            jarOutputStream.putNextEntry(jarEntry);
                            int res;
                            while ((res = in2.read(bytes)) != -1) {
                                jarOutputStream.write(bytes, 0, res);
                            }

                        }
                        catch (ParseDrlRuntimeException e){
                            throw new ParseDrlRuntimeException("in file \"" + child.getName() + "\" detected syntax error", e);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        DrlTool tool = new DrlTool(args);
        if (args.length == 0) {
            tool.help();
            exit(0);
        }
        tool.process();
    }

    public static class Option {
        String fieldName;
        String name;
        OptionArgType argType;
        String description;

        public Option(String fieldName, String name, String description) {
            this(fieldName, name, OptionArgType.NONE, description);
        }

        public Option(String fieldName, String name, OptionArgType argType, String description) {
            this.fieldName = fieldName;
            this.name = name;
            this.argType = argType;
            this.description = description;
        }
    }

    public enum OptionArgType {NONE, STRING}

    public static Option[] optionDefs = {
            new Option("outputDirectory", "-o", OptionArgType.STRING, "specify output directory where all output is generated"),
            new Option("packageDrlOut", "-pd", OptionArgType.NONE, "package the drl files into a jar directory"),
            new Option("packageDrlOutName", "-pdo", OptionArgType.STRING, "the name of drl jar file"),
            new Option("getPojo", "-pj", OptionArgType.NONE, "get the pojo jar file from drls"),
            new Option("getPojoOutName", "-pjo", OptionArgType.STRING, "the name of pojo jar file"),
            new Option("help", "-h", OptionArgType.NONE, "get help")
    };

    private String[] args;

    List<String> drlDirs = new ArrayList<String>();

    public String packageDrlOutName = "drls.jar";
    public String getPojoOutName = "pojos.jar";
    public String outputDirectory;
    public boolean packageDrlOut = false;
    public boolean getPojo = false;
    public boolean help = false;

    protected boolean haveOutputDir = false;

    public DrlTool(String[] args) {
        this.args = args;
        handleArgs();
    }

    protected void handleArgs() {
        int i = 0;
        while (args != null && i < args.length) {
            String arg = args[i];
            i++;
            if (arg.charAt(0) != '-') { // file name
                if (!drlDirs.contains(arg)) drlDirs.add(arg);
                continue;
            }
            boolean found = false;
            for (Option o : optionDefs) {
                if (arg.equals(o.name)) {
                    found = true;
                    String argValue = null;
                    if (o.argType == OptionArgType.STRING) {
                        argValue = args[i];
                        i++;
                    }
                    // use reflection to set field
                    Class<? extends DrlTool> c = this.getClass();
                    try {

                        Field f = c.getField(o.fieldName);
                        if (argValue == null) {
                            if (arg.startsWith("-no-")) f.setBoolean(this, false);
                            else f.setBoolean(this, true);
                        } else f.set(this, argValue);
                    } catch (Exception e) {

                    }
                }
            }
            if (!found) {
                err.println("can't recognize option ");
                exit(1);
            }
            if (outputDirectory != null) {
                if (outputDirectory.endsWith("/") ||
                        outputDirectory.endsWith("\\")) {
                    outputDirectory =
                            outputDirectory.substring(0, outputDirectory.length() - 1);
                }
                File outDir = new File(outputDirectory);
                haveOutputDir = true;
                if (outDir.exists() && !outDir.isDirectory()) {
                    outputDirectory = ".";
                }
            } else {
                outputDirectory = ".";
            }
        }
    }

    private void help() {
        info("\nWelcome To Transwarp Rule Engine Version " + DrlTool.VERSION);
        red("usage: lsp [options] directory [directory ...]");
        info("options:");
        for (Option o : optionDefs) {
            String name = o.name + (o.argType != OptionArgType.NONE ? " [arg]" : "");
            String s = String.format(" %-19s %s", name, o.description);
            info(s);
        }
    }

    private void process() {
        if (help) {
            help();
            exit(0);
        }

        if (packageDrlOut) {
            if (drlDirs.size() == 0) {
                err("target drl directories can't be zero");
            }
            try {
                packageDrlsInJar(drlDirs, outputDirectory, packageDrlOutName);
            } catch (IOException e) {
                err(e.getMessage());
            }
        }

        if (getPojo) {
            try {
                makeJarFromDrl(drlDirs, outputDirectory, getPojoOutName);
            } catch (IOException e) {
                err(e.getMessage());
            }
        }

        info("done!");

        exit(0);
    }

}
