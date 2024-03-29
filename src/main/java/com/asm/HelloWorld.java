package com.asm;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * the hello world byte code generate 
 * by asm.
 * @author Administrator
 * 参考：https://blog.51cto.com/memory/1035551
 */
public class HelloWorld {
    
    public static void main(String args[]) throws Exception {

        new SecurityChecker().getA();

        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        String className = "com/HelloWorld";
        classWriter.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC, className, null,
                                          "java/lang/Object", null);

        MethodVisitor initVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>",
                                           "()V", null, null);
        initVisitor.visitCode();//访问开始
        initVisitor.visitVarInsn(Opcodes.ALOAD, 0);//this指针入栈
        initVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>",
                                            "()V");//调用构造函数
        initVisitor.visitInsn(Opcodes.RETURN);
        initVisitor.visitMaxs(1, 1);//设置栈长、本地变量数
        initVisitor.visitEnd();//访问结束

        // 添加sayHello方法
        MethodVisitor sayVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "sayHello",
                "()V", null, null);
        sayVisitor.visitCode();
        sayVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out",
                "Ljava/io/PrintStream;");
        sayVisitor.visitLdcInsn("h!e!l!l!o!");
        sayVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
                "println", "(Ljava/lang/String;)V");
        sayVisitor.visitInsn(Opcodes.RETURN);
        sayVisitor.visitMaxs(0, 0);
        sayVisitor.visitEnd();

        MethodVisitor mainVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "main",
                "([Ljava/lang/String;)V", null, null);
        mainVisitor.visitCode();
        mainVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out",
                                             "Ljava/io/PrintStream;");
        mainVisitor.visitLdcInsn("hello world!");
        mainVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
                                                "println", "(Ljava/lang/String;)V");
        mainVisitor.visitMethodInsn(Opcodes.INVOKESTATIC, "com/HelloWorld",
                "sayHello", "()V");
        mainVisitor.visitInsn(Opcodes.RETURN);
        mainVisitor.visitMaxs(0, 0);
        mainVisitor.visitEnd();
        
        classWriter.visitEnd();
        byte[] code = classWriter.toByteArray();
//        // 输出到本地文件
//        File file = new File("D:\\com\\HelloWorld.class");
//        FileOutputStream output = new FileOutputStream(file);
//        output.write(code);
//        output.close();

        Class clazz = new ClassLoader() {
            @Override
            protected Class findClass(String name)
                throws ClassNotFoundException {
                return defineClass(name, code, 0, code.length);
            }
        }.loadClass("com.HelloWorld");

        // 利用反射获取com.HelloWorld类中的main方法 
        Method method = clazz.getMethod("main", new Class[]{String[].class});
        // 用反射的方式调用com.HelloWorld类中的main() 
        method.invoke(null, (Object) new String[0]);
    }

}