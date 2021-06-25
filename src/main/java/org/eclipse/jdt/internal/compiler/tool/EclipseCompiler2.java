package org.eclipse.jdt.internal.compiler.tool;

import org.eclipse.jdt.internal.compiler.impl.CompilerOptions;

import javax.annotation.processing.Processor;
import javax.tools.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.WeakHashMap;

public class EclipseCompiler2 extends EclipseCompiler {



//    public EclipseCompilerImpl2(PrintWriter out, PrintWriter err, boolean systemExitWhenFinished) {
//        super(out, err, systemExitWhenFinished);
//    }

    //    @Override
    @Override
    @SuppressWarnings("unchecked")
    public CompilationTask getTask(Writer out, JavaFileManager fileManager, DiagnosticListener<? super JavaFileObject> someDiagnosticListener, Iterable<String> options, Iterable<String> classes, Iterable<? extends JavaFileObject> compilationUnits) {
        PrintWriter writerOut = null;
        PrintWriter writerErr = null;
        if (out == null) {
            writerOut = new PrintWriter(System.err);
            writerErr = new PrintWriter(System.err);
        } else {
            writerOut = new PrintWriter(out);
            writerErr = new PrintWriter(out);
        }
        final Thread currentThread = Thread.currentThread();
        EclipseCompilerImpl eclipseCompiler = this.threadCache.get(currentThread);
        if (eclipseCompiler == null) {
            eclipseCompiler = new EclipseCompilerImpl(writerOut, writerErr, false);
            this.threadCache.put(currentThread, eclipseCompiler);
        } else {
            eclipseCompiler.initialize(writerOut, writerErr, false, null/*options*/, null/*progress*/);
        }
        final EclipseCompilerImpl eclipseCompiler2 = new EclipseCompilerImpl(writerOut, writerErr, false);
        eclipseCompiler2.compilationUnits = compilationUnits;
        eclipseCompiler2.diagnosticListener = someDiagnosticListener;
        if (fileManager != null) {
            eclipseCompiler2.fileManager = fileManager;
        } else {
            eclipseCompiler2.fileManager = this.getStandardFileManager(someDiagnosticListener, null, null);
        }

        eclipseCompiler2.options.put(CompilerOptions.OPTION_Compliance, CompilerOptions.VERSION_1_6);
        eclipseCompiler2.options.put(CompilerOptions.OPTION_Source, CompilerOptions.VERSION_1_6);
        eclipseCompiler2.options.put(CompilerOptions.OPTION_TargetPlatform, CompilerOptions.VERSION_1_6);

        ArrayList<String> allOptions = new ArrayList<String>();
        if (options != null) {
            for (Iterator<String> iterator = options.iterator(); iterator.hasNext(); ) {
                eclipseCompiler2.fileManager.handleOption(iterator.next(), iterator);
            }
            for (String option : options) {
                allOptions.add(option);
            }
        }

        if (compilationUnits != null) {
            for (JavaFileObject javaFileObject : compilationUnits) {
                // http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6419926
                // compells us to check that the returned URIs are absolute,
                // which they happen not to be for the default compiler on some
                // unices
//                URI uri = javaFileObject.toUri();
//                if (!uri.isAbsolute()) {
//                    uri = URI.create("file://" + uri.toString()); //$NON-NLS-1$
//                }
//                allOptions.add(new File(uri).getAbsolutePath());
                allOptions.add(javaFileObject.getName());
            }
        }

        if (classes != null) {
            allOptions.add("-classNames"); //$NON-NLS-1$
            StringBuilder builder = new StringBuilder();
            int i = 0;
            for (String className : classes) {
                if (i != 0) {
                    builder.append(',');
                }
                builder.append(className);
                i++;
            }
            allOptions.add(String.valueOf(builder));
        }

        final String[] optionsToProcess = new String[allOptions.size()];
        allOptions.toArray(optionsToProcess);
        try {
            eclipseCompiler2.configure(optionsToProcess);
        } catch (IllegalArgumentException e) {
            throw e;
        }

        if (eclipseCompiler2.fileManager instanceof StandardJavaFileManager) {
            StandardJavaFileManager javaFileManager = (StandardJavaFileManager) eclipseCompiler2.fileManager;

            Iterable<? extends File> location = javaFileManager.getLocation(StandardLocation.CLASS_OUTPUT);
            if (location != null) {
                eclipseCompiler2.setDestinationPath(location.iterator().next().getAbsolutePath());
            }
        }

        return new CompilationTask() {
            private boolean hasRun = false;
            @Override
            public Boolean call() {
                // set up compiler with passed options
                if (this.hasRun) {
                    throw new IllegalStateException("This task has already been run"); //$NON-NLS-1$
                }
                Boolean value = eclipseCompiler2.call() ? Boolean.TRUE : Boolean.FALSE;
                this.hasRun = true;
                return value;
            }
            @Override
            public void setLocale(Locale locale) {
                eclipseCompiler2.setLocale(locale);
            }
            @Override
            public void setProcessors(Iterable<? extends Processor> processors) {
                ArrayList<Processor> temp = new ArrayList<Processor>();
                for (Processor processor : processors) {
                    temp.add(processor);
                }
                Processor[] processors2 = new Processor[temp.size()];
                temp.toArray(processors2);
                eclipseCompiler2.processors = processors2;
            }
        };
    }

}
