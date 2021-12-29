package io.inceptor.drl.compiler.javacompiler;

import io.inceptor.drl.classloader.ClassLoaderFactory;
import io.inceptor.drl.classloader.DrlClassLoader;
import org.eclipse.jdt.internal.compiler.tool.EclipseCompiler;
import org.eclipse.jdt.internal.compiler.tool.EclipseCompiler2;
import org.eclipse.jdt.internal.compiler.tool.EclipseCompilerImpl;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.tools.*;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

/**
 * Compile Java sources in-memory
 */
public class InMemoryJavaCompiler {
	private static Logger log = LoggerFactory.getLogger(InMemoryJavaCompiler.class);
	private JavaCompiler javac;
//	private DrlClassLoader classLoader;
	private Iterable<String> options;
	boolean ignoreWarnings = false;

	private Map<String, SourceCode> sourceCodes = new HashMap<String, SourceCode>();
	private Map<String, PojoClass> pojoCodes = new HashMap<String, PojoClass>();

	private static InMemoryJavaCompiler inMemoryJavaCompiler = new InMemoryJavaCompiler();

	static {
		List<String> optionsList = new ArrayList<>();
		optionsList.addAll(Arrays.asList("-source","1.8","-target","1.8","-nowarn"));
		optionsList.addAll(Arrays.asList("-classpath",System.getProperty("java.class.path")));

		inMemoryJavaCompiler.useOptions(optionsList.toArray(new String[optionsList.size()]));
		inMemoryJavaCompiler.ignoreWarnings();
	}

	public static InMemoryJavaCompiler instance() {
		return inMemoryJavaCompiler;
	}

	private InMemoryJavaCompiler() {
		this.javac = ToolProvider.getSystemJavaCompiler();
//		this.javac = new EclipseCompiler2();
	}

	/**
	 * Options used by the compiler, e.g. '-Xlint:unchecked'.
	 *
	 * @param options
	 * @return
	 */
	public InMemoryJavaCompiler useOptions(String... options) {
		this.options = Arrays.asList(options);
		return this;
	}

	/**
	 * Ignore non-critical compiler output, like unchecked/unsafe operation
	 * warnings.
	 *
	 * @return
	 */
	public InMemoryJavaCompiler ignoreWarnings() {
		ignoreWarnings = true;
		return this;
	}

	/**
	 * Compile all sources
	 *
	 * @return Map containing instances of all compiled classes
	 * @throws Exception
	 */
	public Map<String, Class<?>> compileAll() throws Exception {
		if (sourceCodes.size() == 0) {
			throw new CompilationException("No source code to compile");
		}
		Collection<SourceCode> sourceCodesCollection = sourceCodes.values();
		Collection<PojoClass> pojoClassCollection = pojoCodes.values();
		Collection<JavaFileObject> compilationUnits = new ArrayList<>();
		compilationUnits.addAll(sourceCodesCollection);
		DiagnosticCollector<JavaFileObject> collector = new DiagnosticCollector<>();
		ExtendedStandardJavaFileManager fileManager = new ExtendedStandardJavaFileManager(javac.getStandardFileManager(collector, null, null), ClassLoaderFactory.getDrlClassLoader());
		JavaCompiler.CompilationTask task = javac.getTask(new Writer() {
			@Override
			public void write(@NotNull char[] cbuf, int off, int len) throws IOException {
				System.err.println(new String(cbuf, off, len));
//				log.warn(new String(cbuf, off, len));
			}

			@Override
			public void flush() throws IOException {

			}

			@Override
			public void close() throws IOException {

			}
		}, fileManager, collector, options, null, compilationUnits);
		boolean result = task.call();
		if (!result || collector.getDiagnostics().size() > 0) {
			StringBuffer exceptionMsg = new StringBuffer();
			exceptionMsg.append("Unable to compile the source");
			boolean hasWarnings = false;
			boolean hasErrors = false;
			for (Diagnostic<? extends JavaFileObject> d : collector.getDiagnostics()) {
				switch (d.getKind()) {
				case NOTE:
				case MANDATORY_WARNING:
				case WARNING:
					hasWarnings = true;
					break;
				case OTHER:
				case ERROR:
				default:
					hasErrors = true;
					break;
				}
				exceptionMsg.append("\n").append("[kind=").append(d.getKind());
				exceptionMsg.append(", ").append("line=").append(d.getLineNumber());
				exceptionMsg.append(", ").append("message=").append(d.getMessage(Locale.US)).append("]");
			}
			if (hasWarnings && !ignoreWarnings || hasErrors) {
				throw new CompilationException(exceptionMsg.toString());
			}
		}

		Map<String, Class<?>> classes = new HashMap<String, Class<?>>();
		for (String className : sourceCodes.keySet()) {
			classes.put(className, ClassLoaderFactory.getDrlClassLoader().loadClass(className));
		}
		return classes;
	}

	/**
	 * Compile single source
	 *
	 * @param fullClassName
	 * @param sourceCode
	 * @return
	 * @throws Exception
	 */
	public Class<?> compile(String fullClassName, String sourceCode) throws Exception {
		return addSource(fullClassName, sourceCode).compileAll().get(fullClassName);
	}

	/**
	 * Add source code to the compiler
	 *
	 * @param className
	 * @param sourceCode
	 * @return
	 * @throws Exception
	 * @see {@link #compileAll()}
	 */
	public InMemoryJavaCompiler addSource(String className, String sourceCode) throws Exception {
		sourceCodes.put(className, new SourceCode(className, sourceCode));
		return this;
	}

	public InMemoryJavaCompiler addPojoClass(String className, byte[] content) {
		pojoCodes.put(className, new PojoClass(className ,content));
		return this;
	}

	public Map<String, PojoClass> getPojoCodes() {
		return pojoCodes;
	}
}
