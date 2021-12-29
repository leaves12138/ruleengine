package io.inceptor.drl.compiler.javacompiler;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.NestingKind;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import java.io.*;
import java.net.URI;

public class PojoClass implements JavaFileObject {

    private String className;
    private byte[] content;

    public PojoClass(String className, byte[] content) {
        this.content = content;
        this.className = className;
    }

    public String getFullClassName() {
        return className;
    }

    @Override
    public URI toUri() {
        return null;
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException();
    }


    @Override
    public InputStream openInputStream() throws IOException {
        return new ByteArrayInputStream(content);
    }

    @Override
    public OutputStream openOutputStream() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Reader openReader(boolean ignoreEncodingErrors) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Writer openWriter() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getLastModified() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Kind getKind() {
        return Kind.CLASS;
    }

    @Override
    public boolean isNameCompatible(String simpleName, Kind kind) {
        throw new UnsupportedOperationException();
    }

    @Override
    public NestingKind getNestingKind() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Modifier getAccessLevel() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
