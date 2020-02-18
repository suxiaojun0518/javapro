package com.neuedu.text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author SuXiaojun
 * @Date 2020/2/18 11:13
 * @Version 1.0
 */
public class JdbcText {
    public static void main(String[] args) {
        String url="jdbc:mysql://192.168.180.151:3306/db1?useUnicode=true&characterEncoding=utf8";
        String usename="root";
        String password="123456";
        Connection con=null;
        PreparedStatement pstmt=null;
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");//加载驱动
            // 2 新建连接  通过 主机ip或者主机名  端口号 用户名  密码
            // 创建连接 通过DriverManager类来实现
           con = DriverManager.getConnection(url,usename,password);
            //创建一个可以写sql语句的地方
            pstmt = con.prepareStatement("insert into student(Sno,Sname,Sage) values(001,'苏晓军',1)");
           int i=pstmt.executeUpdate();
            System.out.println(i);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{

                try {
                    if(pstmt!=null)
                        pstmt.close();
                    if(con!=null)
                        con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
}

