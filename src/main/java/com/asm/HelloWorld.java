package com.asm;

import java.io.File;
import java.io.FileOutputStream;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * the hello world byte code generate 
 * by asm.
 * @author Administrator
 *
 */
public class HelloWorld {
    
    public static void main(String args[]) throws Exception {

        new SecurityChecker().getA();

        ClassWriter classWriter = new ClassWriter(0);
        String className = "com/asm/HelloWorld";
        classWriter.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC, className, null, 
                                          "java/lang/Object", null);

        MethodVisitor initVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>",
                                           "()V", null, null);
        initVisitor.visitCode();//访问开始
        initVisitor.visitVarInsn(Opcodes.ALOAD, 0);//this指针入栈
        initVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", 
                                            "V()");//调用构造函数
        initVisitor.visitInsn(Opcodes.RETURN);
        initVisitor.visitMaxs(1, 1);//设置栈长、本地变量数
        initVisitor.visitEnd();//访问结束
        
        MethodVisitor helloVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "sayHello", 
                                           "()V;", null, null);
        helloVisitor.visitCode();
        helloVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", 
                                             "Ljava/io/PrintStream;");
        helloVisitor.visitLdcInsn("hello world!");
        helloVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", 
                                                "println", "(Ljava/lang/String;)V");
        helloVisitor.visitInsn(Opcodes.RETURN);
        helloVisitor.visitMaxs(1, 1);
        helloVisitor.visitEnd();
        
        classWriter.visitEnd();
        byte[] code = classWriter.toByteArray();
        File file = new File("D:\\HelloWorld.class");
        FileOutputStream output = new FileOutputStream(file);
        output.write(code);
        output.close();
    }

}