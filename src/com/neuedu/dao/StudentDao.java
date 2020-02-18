package com.neuedu.dao;

import com.neuedu.pojo.Student;

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
    String url = "jdbc:mysql://192.168.180.151:3306/db1?useUnicode=true&characterEncoding=utf8";
    String username = "root";
    String password = "123456";
    @Override
    public List<Student> query() {
        List <Student> list=new ArrayList<>();
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection(url,username,password);
            pstmt=con.prepareStatement("select id,name,age,gender from student");
            // 执行sql语句
            // 如果是查询  调用 executeQuery方法 返回一个 ResultSet 结果集
            rs=pstmt.executeQuery();
            /*  ResultSet 每次调用 next()方法的时候 会做两件事
           *    1 看一下有没有下一行 如果没有返回fasle
           *    2 如果有 将游标推向下一行 返回true
           *
           *
           * */
            while (rs.next()){
                Student student=new Student();
                student.setId(rs.getInt("id"));//获取该游标下的第一个int型的值
                student.setName(rs.getString("name"));//获取该游标下的第一个String型的值
                student.setAge(rs.getInt("age"));//获取该游标下一个int型的值
                student.setGender(rs.getInt("gender"));
                list.add(student);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(rs!=null)
                    rs.close();
                if(pstmt!=null)
                    pstmt.close();
                if(con!=null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public int add(Student student) {
        int i=0;
        Connection con=null;
        PreparedStatement pstmt=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection(url,username,password);
            pstmt=con.prepareStatement("insert into student (name,age,gender) values(?,?,?)");//"?"为占位符
            //设置问号的值
            pstmt.setString(1,student.getName());
            pstmt.setInt(2,student.getAge());
            pstmt.setInt(3,student.getGender());
            //如果是添加，调用.executeUpdate()方法，返回值为int 型，返回受影响的行数
            i=pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(pstmt!=null)
                    pstmt.close();
                if(con!=null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }


    @Override
    public int update(Student student) {
        int i=0;
        Connection con=null;
        PreparedStatement pstmt=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection(url,username,password);
            pstmt=con.prepareStatement("update student set name=?,age=?,gender=? where id=?");//"?"为占位符
            //设置问号的值
            pstmt.setString(1,student.getName());
            pstmt.setInt(2,student.getAge());
            pstmt.setInt(3,student.getGender());
            pstmt.setInt(4,student.getId());
            //如果是添加，调用.executeUpdate()方法，返回值为int 型，返回受影响的行数
            i=pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(pstmt!=null)
                    pstmt.close();
                if(con!=null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;

    }

    @Override
    public int del(int id) {
        int i=0;
        Connection con=null;
        PreparedStatement pstmt=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection(url,username,password);
            pstmt=con.prepareStatement("delete from student where id=?");//"?"为占位符
            //设置问号的值
            pstmt.setInt(1,id);
            //如果是添加，调用.executeUpdate()方法，返回值为int 型，返回受影响的行数
            i=pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(pstmt!=null)
                    pstmt.close();
                if(con!=null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    @Override
    public Student queryOne(int id) {
        return null;
    }
}
