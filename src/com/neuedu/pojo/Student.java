package com.neuedu.pojo;

/**
 * @Author SuXiaojun
 * @Date 2020/2/18 15:34
 * @Version 1.0
 */
public class Student {
    private Integer id;
    private String name;
    private Integer age;
    private Integer gender;

    public Student() {
    }

    public Student(Integer id) {
        this.id = id;
    }

    public Student(String name, Integer age, Integer gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Student(Integer id, String name, Integer age, Integer gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
