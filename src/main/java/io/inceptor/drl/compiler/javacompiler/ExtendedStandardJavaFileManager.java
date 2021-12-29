package io.inceptor.drl.compiler.javacompiler;

import io.inceptor.drl.classloader.DrlClassLoader;
import io.inceptor.drl.compiler.javacompiler.CompiledCode;
import io.inceptor.drl.drl.DeclaredClass;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;

/**
 * Created by trung on 5/3/15. Edited by turpid-monkey on 9/25/15, completed
 * support for multiple compile units.
 */
public class ExtendedStandardJavaFileManager extends
		ForwardingJavaFileManager<JavaFileManager> {

	private List<CompiledCode> compiledCode = new ArrayList<CompiledCode>();
	private DrlClassLoader cl;

	/**
	 * Creates a new instance of ForwardingJavaFileManager.
	 *
	 * @param fileManager
	 *            delegate to this file manager
	 * @param cl
	 */
	protected ExtendedStandardJavaFileManager(JavaFileManager fileManager,
											  DrlClassLoader cl) {
		super(fileManager);
		this.cl = cl;
	}

	@Override
	public JavaFileObject getJavaFileForOutput(
			JavaFileManager.Location location, String className,
			JavaFileObject.Kind kind, FileObject sibling) throws IOException {

		try {
			CompiledCode innerClass = new CompiledCode(className);
			compiledCode.add(innerClass);
			cl.addCode(innerClass);
			return innerClass;
		} catch (Exception e) {
			throw new RuntimeException(
					"Error while creating in-memory output file for "
							+ className, e);
		}
	}

	@Override
	public ClassLoader getClassLoader(JavaFileManager.Location location) {
		return cl;
	}

	protected ExtendedStandardJavaFileManager(JavaFileManager fileManager) {
		super(fileManager);
	}

	@Override
	public Iterable<JavaFileObject> list(Location location, String packageName, Set<JavaFileObject.Kind> kinds, boolean recurse) throws IOException {
		if (kinds.contains(JavaFileObject.Kind.CLASS) && packageName.contains(DeclaredClass.packageName)) {
			ArrayList<JavaFileObject> arrayList = new ArrayList<>();
			Iterable<JavaFileObject> parents = super.list(location, packageName, kinds, recurse);
			parents.forEach(javaFileObject -> {arrayList.add(javaFileObject);});
			InMemoryJavaCompiler.instance().getPojoCodes().values().forEach(pojoClass -> {
				arrayList.add(pojoClass);
			});
			return arrayList;
		}
		return super.list(location, packageName, kinds, recurse);
	}

	@Override
	public String inferBinaryName(Location location, JavaFileObject file) {
		if (file instanceof PojoClass) {
			return ((PojoClass)file).getFullClassName();
		}
		return super.inferBinaryName(location, file);
	}

	@Override
	public boolean isSameFile(FileObject a, FileObject b) {
		return super.isSameFile(a, b);
	}

	@Override
	public boolean handleOption(String current, Iterator<String> remaining) {
		return super.handleOption(current, remaining);
	}

	@Override
	public boolean hasLocation(Location location) {

		return super.hasLocation(location);
	}

	@Override
	public int isSupportedOption(String option) {
		return super.isSupportedOption(option);
	}

	@Override
	public JavaFileObject getJavaFileForInput(Location location, String className, JavaFileObject.Kind kind) throws IOException {
		return super.getJavaFileForInput(location, className, kind);
	}

	@Override
	public FileObject getFileForInput(Location location, String packageName, String relativeName) throws IOException {
		return super.getFileForInput(location, packageName, relativeName);
	}

	@Override
	public FileObject getFileForOutput(Location location, String packageName, String relativeName, FileObject sibling) throws IOException {
		return super.getFileForOutput(location, packageName, relativeName, sibling);
	}

	@Override
	public void flush() throws IOException {
		super.flush();
	}

	@Override
	public void close() throws IOException {
		super.close();
	}
}
