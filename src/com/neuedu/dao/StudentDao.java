package com.neuedu.dao;

import com.neuedu.pojo.Student;
import com.neuedu.util.Jdbcutil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author SuXiaojun
 * @Date 2020/2/18 15:26
 * @Version 1.0
 */
public class StudentDao implements IstudentDao {
    @Override
    public List<Student> query() {
        String sql="select id,name,age,gender from student";
        List <Student> list=Jdbcutil.executeQuery(sql,Student.class);
        return list;
    }

    @Override
    public int add(Student student) {
        String sql="insert into student(name,age,gender) values(?,?,?)";
        int i= Jdbcutil.executeUpdate(sql,student.getName(),student.getAge(),student.getGender());
        return i;
    }

    @Override
    public int update(Student student) {
        String sql="update student set name=?,age=?,gender=? where id=?";
        int i=Jdbcutil.executeUpdate(sql,student.getName(),student.getAge(),student.getGender(),student.getId());
        return i;
    }

    @Override
    public int del(int id) {
        String sql="delete from student where id=?";
        int i=Jdbcutil.executeUpdate(sql,id);
        return i;
    }

    @Override
    public Student queryOne(int id) {
        return null;
    }
}
