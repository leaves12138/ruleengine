package io.inceptor.drl.drl;

import io.inceptor.drl.asm.AnnotationVisitor;
import io.inceptor.drl.asm.ClassWriter;
import io.inceptor.drl.asm.FieldVisitor;
import io.inceptor.drl.asm.MethodVisitor;
import io.inceptor.drl.classloader.ClassLoaderFactory;
import io.inceptor.drl.classloader.DrlClassLoader;
import io.inceptor.drl.classwriter.*;
import io.inceptor.drl.drl.symboltable.SymbolTable;
import io.inceptor.drl.exceptions.NoInstanceException;

import static io.inceptor.drl.asm.Opcodes.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DeclaredClass {

    public static String jvmPrefix = "io/inceptor/asm/AUTOGENERATE/";
    public static String javaPrefix = "io.inceptor.asm.AUTOGENERATE.";
    public static String jvmAnnotationPrefix = "io/inceptor/drl/annotation/";

    private String oriText;

    private String className;

    private String location;

    private boolean isStreamClass;

    private List<Field> fields;

    private List<Annotation> annotations;

    private Class instance;

    private boolean inited = false;

    public DeclaredClass(String location) {
        this.location = location;
    }

    //generate instance by asm
    public void init() {
        if (!inited)
            resolve();
        inited = true;
    }

    public String getOriText() {
        return oriText;
    }

    public void setOriText(String oriText) {
        this.oriText = oriText;
    }

    public boolean isStreamClass() {
        return isStreamClass;
    }

    public void setStreamClass(boolean streamClass) {
        isStreamClass = streamClass;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public Class getInstance() {
        if (instance == null)
            throw new NoInstanceException();
        return instance;
    }

    public void setInstance(Class instance) {
        this.instance = instance;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Class resolve() throws RuntimeException {
        if (instance != null) {
            return instance;
        }

        ClassWriter classWriter = ClassWriterFactory.getClassWriter();
        generateConstructor(classWriter);
        classWriter.visit(V1_8, ACC_PUBLIC, getJVMFullName(), null, "java/lang/Object", new String[]{});
        for (Annotation annotation : annotations) {
            AnnotationVisitor av = classWriter.visitAnnotation("L" + jvmAnnotationPrefix + annotation.getName() + ";", true);
            for (Map.Entry<String, Object> entry : annotation.getValueEntries())
                av.visit(entry.getKey(), entry.getValue());
        }
        for (Field field : fields) {
            generateField(field, classWriter);
            generateGetterMethod(field, classWriter);
            generateSetterMethod(field, classWriter);
        }
        classWriter.visitEnd();
        DrlClassLoader drlClassLoader = ClassLoaderFactory.getDrlClassLoader();

        Class c = drlClassLoader.defineClass(getJavaFullName(), classWriter.toByteArray());
        instance = c;

        ParsedDrlFile.classImportResolverFactory.addClass(instance);

        return instance;
    }

    private void generateConstructor(ClassWriter classWriter) {
        MethodVisitor mv = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
    }

    private void generateField(Field field, ClassWriter classWriter) {
        List<Annotation> annotations = field.getAnnotations();
        FieldVisitor fieldVisitor = classWriter.visitField(ACC_PRIVATE, field.getFieldName(), field.getJVMType(), null, null);
        for (Annotation at : annotations) {
            AnnotationVisitor av = fieldVisitor.visitAnnotation("L" + jvmAnnotationPrefix + at.getName() + ";", true);
            for (Map.Entry<String, Object> entry : at.getValueEntries())
                av.visit(entry.getKey(), entry.getValue());
        }
        fieldVisitor.visitEnd();
    }

    private void generateGetterMethod(Field field, ClassWriter classWriter) {
        MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC, field.getGetterName(), field.getJVMGetterType(), null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, getJVMFullName(), field.getFieldName(), field.getJVMType());
        methodVisitor.visitInsn(ARETURN);
        methodVisitor.visitMaxs(1, 1);
        methodVisitor.visitEnd();
    }

    private void generateSetterMethod(Field field, ClassWriter classWriter) {
        MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC, field.getSetterName(), field.getJVMSetterType(getJVMType()), null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitFieldInsn(PUTFIELD, getJVMFullName(), field.getFieldName(), field.getJVMType());
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitInsn(ARETURN);
        methodVisitor.visitMaxs(1, 1);
        methodVisitor.visitEnd();
    }

    public String getJVMFullName() {
        return getJVMFullLocation() + className;
    }

    public String getJavaFullName() {
        return getJavaFullLocation() + className;
    }

    public String getJVMFullLocation() {
        return jvmPrefix + location.replace(".", "/") + "/";
    }

    public String getJavaFullLocation() {
        return javaPrefix + location + ".";
    }

    public String getJVMType() {
        return "L" + getJVMFullName() + ";";
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> annotations) {
        this.annotations = annotations;
    }

    public static class Field {


        private List<Annotation> annotations;

        private String fieldName;

        private String javaType;

        public String getJVMType() {
            String type;
            if ("String".equals(javaType)) {
                type = "java/lang/String;";
            } else if ("Int".equals(javaType) || "Integer".endsWith(javaType)) {
                type = "java/lang/Integer;";
            } else if ("Boolean".equals(javaType)) {
                type = "java/lang/Boolean;";
            } else if ("Byte".equals(javaType)) {
                type = "java/lang/Byte;";
            } else if ("Short".equals(javaType)) {
                type = "java/lang/Short;";
            } else if ("Long".equals(javaType)) {
                type = "java/lang/Long;";
            } else if ("Float".equals(javaType)) {
                type = "java/lang/Float;";
            } else if ("Double".equals(javaType)) {
                type = "java/lang/Double;";
            } else if ("Character".equals(javaType)) {
                type = "java/lang/Character;";
            } else if ("Time".equals(javaType)) {
                type = "java/sql/Time;";
            } else if ("Timestamp".equals(javaType)) {
                type = "java/sql/Timestamp;";
            } else if ("Date".equals(javaType)) {
                type = "java/util/Date;";
            } else if ("byte[]".equals(javaType)) {
                type = "[B";
                return type;
            } else if ("BigDecimal".equals(javaType)) {
                type = "java/math/BigDecimal;";
            } else if ("Json".equals(javaType)) {
                type = "com/fasterxml/jackson/databind/JsonNode;";
            } else if ("Object[]".equals(javaType)) {
                type = "[Ljava/lang/Object;";
                return type;
            } else {
                type = javaType.replaceAll("\\.", "/") + ";";
            }
            return "L" + type;
        }

        public String getJVMGetterType() {
            return "()" + getJVMType();
        }

        public String getJVMSetterType() {
            return "(" + getJVMType() + ")" + "V";
        }

        public String getJVMSetterType(String re) {
            return "(" + getJVMType() + ")" + re;
        }

        public String getGetterName() {
            return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        }

        public String getSetterName() {
            return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        }

        public List<Annotation> getAnnotations() {
            return annotations;
        }

        public void setAnnotations(List<Annotation> annotations) {
            this.annotations = annotations;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getJavaType() {
            return javaType;
        }

        public void setJavaType(String javaType) {
            this.javaType = javaType;
        }
    }

    public static class Annotation {

        private String name;

        private Map<String, Object> values = new HashMap();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getValue(String name) {
            if (values.containsKey(name))
                return values.get(name);
            return null;
        }

        public void setValue(String name, Object value) {
            values.put(name, value);
        }

        public Set<Map.Entry<String, Object>> getValueEntries() {
            return values.entrySet();
        }
    }

    public static class DumpClassBytes {
        private DumpClassBytes() {
        }

        public String name;
        public String packageName;
        public byte[] bytes;
    }

    public DumpClassBytes dumpClass() {
        ClassWriter classWriter = ClassWriterFactory.getClassWriter();
        generateConstructor(classWriter);
        classWriter.visit(V1_8, ACC_PUBLIC, getJVMFullName(), null, "java/lang/Object", new String[]{});
        for (Annotation annotation : annotations) {
            AnnotationVisitor av = classWriter.visitAnnotation("L" + jvmAnnotationPrefix + annotation.getName() + ";", true);
            for (Map.Entry<String, Object> entry : annotation.getValueEntries())
                av.visit(entry.getKey(), entry.getValue());
            av.visitEnd();
        }
        for (Field field : fields) {
            generateField(field, classWriter);
            generateGetterMethod(field, classWriter);
            generateSetterMethod(field, classWriter);
        }
        classWriter.visitEnd();

        DumpClassBytes dumpClassBytes = new DumpClassBytes();
        dumpClassBytes.packageName = getJVMFullLocation();
        dumpClassBytes.name = className;
        dumpClassBytes.bytes = classWriter.toByteArray();
        return dumpClassBytes;
    }
}
