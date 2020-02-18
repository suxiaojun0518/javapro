package com.neuedu.text;//src下不允许直接建类 必须有包！！！！！！！

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MybookText {
    public static List <MyBook> list =new ArrayList();
    public static File file =new File("E:/student");
    public static void main(String[] args) {
        if(!file.exists()){
            inputData(list);
            save();
        }
       else {
           read();
       }
        while (true){
            menu();
            Scanner scanner =new Scanner(System.in);
            int i = scanner.nextInt();
            switch (i){
                case 1:
                    add(list);
                    break;
                case 2:
                    System.out.println("请输入要删除的图书名：");
                    String name=scanner.next();
                    deleteName(list,name);
                    break;
                case 3:
                    System.out.println("请输入要查找的图书名：");
                    String searchName=scanner.next();
                    searchName(list,searchName);
                    break;
                case 4:
                    print(list);
                    break;
                case 5:
                    System.exit(1);
                    break;


            }

        }


    }

    public static void inputData(List<MyBook> books) {
        Scanner scanner =new Scanner(System.in);
        System.out.println("首次执行，请输入需要初始化的图书本数：");
        int n= scanner.nextInt();
        for (int i = 0; i <= n; i++) {
            MyBook myBook = new MyBook("书名" + i, i, "出版社" + i, "sxj" + i, "sxj0518" + i);
            books.add(myBook);
        }
    }
    public static void print(List<MyBook> books){
        for(int i=0;i<books.size();i++){
            System.out.println(books.get(i));
        }
    }
    public static void searchName(List<MyBook> books, String name){
        for (int i=0;i<books.size();i++){
            MyBook myBook=books.get(i);
            if(myBook.getName().equals(name)){
                System.out.println(myBook);
                return;
            }

        }
        System.out.println("此书不存在！");
    }
    public static void deleteName(List<MyBook> books, String name){
        for (int i=0;i<books.size();i++){
            MyBook myBook=books.get(i);
            if(myBook.getName().equals(name)){
               books.remove(i);
                System.out.println("此书删除成功。");
                save();
                System.out.println(myBook);
                return;
            }

        }
        System.out.println("此书不存在！删除失败！");
    }
    public static void add(List<MyBook> books){
        Scanner scanner =new Scanner(System.in);
        System.out.println("请输入书名：");
        String name =scanner.next();
        System.out.println("请输入价格：");
        double price =scanner.nextDouble();
        System.out.println("请输入出版社：");
        String press =scanner.next();
        System.out.println("请输入作者：");
        String author =scanner.next();
        System.out.println("请输入图书号：");
        String bookISBN =scanner.next();
        MyBook myBook = new MyBook(name,price,press,author,bookISBN);
        books.add(myBook);
        save();
        System.out.println("添加成功！");
        System.out.println(myBook);
    }
    public static void menu(){
        System.out.println("请输入1-5中的任一个数字");
        System.out.println("1：添加图书");
        System.out.println("2：删除图书");
        System.out.println("3：查找图书");
        System.out.println("4：查看所有图书");
        System.out.println("5：退出");
        System.out.print("请输入：");
    }
    public static void save(){
        OutputStream os =null;
        ObjectOutputStream oos=null;
        try {
            os =new FileOutputStream(file);
            oos=new ObjectOutputStream(os);
            oos.writeObject(list);
            oos.flush();
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
                try {
                    if(oos!=null)
                        oos.close();
                    if(os!=null)
                        os.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
    public static void read(){
        InputStream is=null;
        ObjectInputStream ois=null;
        try {
            is=new FileInputStream(file);
            ois=new ObjectInputStream(is);
            list =(List <MyBook>) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
                try {
                    if(ois!=null)
                        ois.close();
                    if(is!=null)
                        is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

}