package com.neuedu.web;

import com.neuedu.dao.IstudentDao;
import com.neuedu.pojo.Student;
import com.neuedu.service.IstudentService;
import com.neuedu.service.StudentService;

import java.util.List;
import java.util.Scanner;

/**
 * @Author SuXiaojun
 * @Date 2020/2/18 15:33
 * @Version 1.0
 */
public class StudentWeb {
    IstudentService studentService =new StudentService();
    public void showmain(){
        IstudentService studentService = new StudentService();
        System.out.println("---------------------------");
        System.out.println("1-----------------------查询");
        System.out.println("2-----------------------添加");
        System.out.println("3-----------------------修改");
        System.out.println("4-----------------------删除");
        System.out.println("其他--------------------退出");
        System.out.println("---------------------------");
    }
    public void input() {
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        switch (i) {
            case 1:
                query();
                break;
            case 2:
                add(scanner);
                break;
            case 3:
                update(scanner);
                break;
            case 4:
                del(scanner);
                break;
            case 5:
                System.exit(1);
        }
    }
    public void query(){
        // 在此要调用 服务层提供的方法来去访问数据库
        List<Student> list = studentService.query();
        for(Student student : list){
            System.out.println(student);
        }
    }
    public void add(Scanner scanner){
        System.out.println("请输入要添加的姓名");
        String name =scanner.next();
        System.out.println("请输入要添加的年龄");
        int age=scanner.nextInt();
        System.out.println("请输入性别(0-女，1-男)");
        int gender =scanner.nextInt();
        Student student =new Student (name,age,gender);
        studentService.add(student);//将student对象当做参数传给studentService类下的add方法
        query();

    }
    public void update(Scanner scanner){
        System.out.println("请输入要修改的id");
        int id = scanner.nextInt();
        System.out.println("请输入要修改的姓名");
        String name =scanner.next();
        System.out.println("请输入要修改的年龄");
        int age=scanner.nextInt();
        System.out.println("请输入性别(0-女，1-男)");
        int gender =scanner.nextInt();
        Student student =new Student (id,name,age,gender);
        studentService.update(student);//将student对象当做参数传给studentService类下的add方法
        query();

    }
    public void del(Scanner scanner){
        System.out.println("请输入要删除的ID");
        int id=scanner.nextInt();
        studentService.del(id);
        query();

    }
}
