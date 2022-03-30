package com.clone;

import lombok.SneakyThrows;

/**
 * @author kingwarluo
 * @{description}
 * @date 2022/3/29 14:22
 */
public class ObjectClone {

    @SneakyThrows
    public static void main(String[] args) {
        Professor professor = new Professor("liao");
        Student s = new Student(1l, 1, "huage", professor);
        Student s1 = (Student) s.clone();
        System.out.println(s1);
        s1.getProfessor().setName("chen");
        System.out.println(s);
    }

}
