package com.asm.ow;


import agent.org.objectweb.asm.ClassReader;
import agent.org.objectweb.asm.ClassVisitor;
import agent.org.objectweb.asm.ClassWriter;

import java.io.FileOutputStream;

/**
 * @author zrj
 * @since 2022/1/24
 **/
public class EmployeeOw2AsmTest {

    public static void main(String[] args) throws Exception {

        //1.定义ClassReader
        String sourceClassName = "com.asm.ow.Employee";
        ClassReader classReader = new ClassReader(sourceClassName);
        //2.定义ClassWriter
        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
        //3.定义ClassVisitor
        ClassVisitor classVisitor = new EmployeeClassVisitor(classWriter);

        // 定义classVisitor输入数据,
        // SKIP_DEBUG 如果设置了此标志，则这些属性既不会被解析也不会被访问
        // EXPAND_FRAMES 依次调用ClassVisitor 接口的各个方法
        classReader.accept(classVisitor, ClassReader.EXPAND_FRAMES);

        // 将最终修改的字节码以byte数组形式返回
        byte[] bytes = classWriter.toByteArray();

        String targetClassName = "com.asm.ow.Employee$EnhancedByASM";
        Class<?> clazz = new EmployeeClassLoader().defineClassFromClassFile(targetClassName, bytes);
        System.out.println("【EmployeeOw2AsmTest】clazz：" + clazz);

        // 通过文件流写入方式覆盖原先的内容，实现class文件的改写
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\data\\asm\\Employee$EnhancedByASM.class");
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

}
