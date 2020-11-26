package in.inceptor.drl;

import io.inceptor.drl.asm.ClassWriter;
import io.inceptor.drl.asm.MethodVisitor;
import io.inceptor.drl.classloader.DrlClassLoader;

import static io.inceptor.drl.asm.Opcodes.*;

public class TestClassVisitor {
    public static void main(String[] args){
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_5, ACC_PUBLIC ,
                "io/Comparable", null, "java/lang/Object",
                null);
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I",
                null, new Integer(-1)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I",
                null, new Integer(0)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I",
                null, new Integer(1)).visitEnd();
        MethodVisitor methodVisitor = cw.visitMethod(ACC_PUBLIC, "compareTo",
                "(Ljava/lang/Object;)I", null, null);

        methodVisitor.visitEnd();
        cw.visitEnd();
        byte[] b = cw.toByteArray();

        DrlClassLoader drlClassLoader = new DrlClassLoader(ClassLoader.getSystemClassLoader());
        Class c = drlClassLoader.defineClass("io.Comparable", b);
        return;
    }
}
