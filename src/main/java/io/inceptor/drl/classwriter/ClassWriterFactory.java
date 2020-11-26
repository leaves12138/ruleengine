package io.inceptor.drl.classwriter;

import io.inceptor.drl.asm.ClassWriter;

public class ClassWriterFactory {
    public static ClassWriter getClassWriter() {
        return new ClassWriter(ClassWriter.COMPUTE_FRAMES);
    }
}
