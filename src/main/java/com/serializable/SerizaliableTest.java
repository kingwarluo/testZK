package com.serializable;

import lombok.Data;
import lombok.ToString;

import java.io.*;
import java.util.Date;

/**
 * 序列化时，指定序列化id的重要性
 * 1、序列化id一旦指定不能改变
 * 2、序列化类，一定要指定序列化id
 *
 * @author jianhua.luo
 * @date 2020/2/29
 */
public class SerizaliableTest {

    public static void main(String[] args) {
        try {
            //向文件中写入序列化的对象
//            User user = new User();
//            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("d:/serizalible"));
//            out.writeObject(user);

            //读取序列化后的文件
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("d:/serizalible"));
            System.out.println((User) in.readObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Data
    @ToString
    public static class User implements Serializable {
        //1.首先设定一个序列化id值（改变序列化id值，从保存的文件中读取时，报错 java.io.InvalidClassException，序列化id对不上）
        //2.不设置序列化id，当User只有name和age时，保存到文件；添加User属性bir，再从保存的文件读取对象，报错 java.io.InvalidClassException，序列化id对不上
//        private static final long serialVersionUID = 6211585307421095148L;

        private String name;

        private Integer age;

//        private Date bir;

    }

}
