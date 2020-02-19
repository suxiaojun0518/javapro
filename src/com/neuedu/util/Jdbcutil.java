package com.neuedu.util;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author SuXiaojun
 * @Date 2020/2/19 14:25
 * @Version 1.0
 */
public class Jdbcutil {
    private static final String URL="jdbc:mysql://192.168.180.151:3306/db1?useUnicode=true&characterEncoding=utf8";
    private static final String USERNAME="root";
    private static final String PASSWORD="123456";
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    static Connection getConnection(){
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    // 封装通用的增删改
    public static int executeUpdate(String sql,Object... params){
        int result = 0;
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(sql);
            if(params != null){
                for(int i = 0; i < params.length; i++){
                    pstmt.setObject(i+1,params[i]);
                }
            }
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(con,pstmt);
        }
        return result;
    }
    // 封装通用查询
    //不确定泛型的情况下可以用<T>代替，只需要在参数列表中输入类型，用反射，因为static类型不需要new,因此类型前需要加限定泛型<T>
    public static <T> List<T> executeQuery(String sql,Class<T>clz,Object...params){
        // Student 继承 Object
        // List<Student> 没有继承关系 List<Object>
        List<T> list=new ArrayList<>();
        Connection con = getConnection();
        PreparedStatement pstmt=null;
        ResultSet rs =null;
         /*  ResultSet 每次调用 next()方法的时候 会做两件事
           *    1 看一下有没有下一行 如果没有返回fasle
           *    2 如果有 将游标推向下一行 返回true
           * */
        try {
            pstmt =con.prepareStatement(sql);
            if(params!=null){
                for(int i=0;i<params.length;i++){
                    pstmt.setObject(i+1,params[i]);//插入
                }
            }
            rs=pstmt.executeQuery();//返回ResultSet游标,对应数据库每一行的值
            while(rs.next()){
                // 创建一个T类型的对象
                // 该方法是通过反射 class对象 调用 无参构造来创建对象
                T t=clz.newInstance();
                // 通过 字段名 从 rs中 拿值  然后赋值给 对象的属性
                // 在不知道 该类有多少个属性的情况下 应该是 有多少个属性 就set多少个属性
                // 我们必须先获取有多少个属性
                Field []field=clz.getDeclaredFields();//获取该对象下的所有属性，GetFields()方法获取的为public属性
                for(Field f:field){
                    // 属性名 刚好和 数据库字段名一样了
                    // 我们就可以把属性名 当做数据库里的字段名 用 rs.getObject(属性名)

                    try {

                        // f.getName() 可能和 数据库中的字段名不同
                        // 那么我们就先看 该属性有没有 Column注解  如果有 我们用注解的值当做字段名
                        // 如果没有注解 我们再把 f.getName() 当做字段名
                        String column=f.getName();
                        if(f.isAnnotationPresent(Column.class))// 此刻就要先判断 该属性下 有没有 Column注解
                        {   // 如果有 我们就要获取到注解的值 来替换 column的值
                            // 获取注解
                            Column c=f.getAnnotation(Column.class);
                            // 用注解的value 替换 column
                            column=c.value();
                        }
                        /*拿出值之后 我们就可以为 属性赋值了
                         为属性赋值 属性对象是 Field 但是我们必须指明 我们要为哪个对象的属性赋值
                         我们用 field 调用set方法 可以进行赋值
                        第一个参数 传一个对象  表示为哪个对象的该属性赋值
                        第二个参数 传值 表示 赋什么样的值

                        注意 如果该属性是 private 那么在赋值之前必须先打开权限
                         */
                        Object value = rs.getObject(column);//获取该游标下的字段值，rs.getObject(字段名)
                        f.setAccessible(true);//设置权限，可以访问私有属性
                        f.set(t,value);
                    }catch (Exception e){}


                }
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }finally {
            close(con,pstmt,rs);
        }
        return list;
    }
    // 封装关闭方法
    static void close(Connection con,PreparedStatement pstmt){
        try {
            if(pstmt != null)
                pstmt.close();
            if(con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static void close(Connection con, PreparedStatement pstmt, ResultSet rs){
        try {
            if(rs!= null)
                rs.close();
            close(con,pstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
