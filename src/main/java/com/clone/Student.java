package com.clone;

/**
 * @author kingwarluo
 * @{description}
 * @date 2022/3/29 14:23
 */
public class Student implements Cloneable {

    private Long id;
    private Integer age;
    private String name;

    private Professor professor;

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Student(Long id, Integer age, String name, Professor professor) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.professor = professor;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Student student = (Student) super.clone();
        student.professor = (Professor) professor.clone();
        return student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", professor=" + professor +
                '}';
    }
}
