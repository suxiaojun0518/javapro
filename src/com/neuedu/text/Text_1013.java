package com.neuedu.text;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author SuXiaojun
 * @Date 2020/2/13 14:57
 * @Version 1.0
 */
public class Text_1013 {
    public static void main(String[] args) {
        //FeiBoNaQie();
        //QiuLuoDi();
        //fun();
        BaoShu();
    }
    public static void FeiBoNaQie(){
        List list =new ArrayList();
        list.add(1);
        list.add(1);
        for (int i=2;i<40;i++){
            int a=(int)list.get(i-1)+(int)list.get(i-2);
            list.add(a);
        }
        System.out.println(list);

    }
    public  static void QiuLuoDi(){
        double a=100;
        for(int i=0;i<10;i++){
             a=a/2;
        }
        System.out.println(a);
    }
    public static void fun(){
        int x;
        float y;
        for(int i=1;i<=13;i++){
            y=84/i-i/2;
            if((int)y==y){
                x=(int)(y*y-100);
                System.out.println(x);
            }

        }

    }
    public static void BaoShu(){
        List <Integer> list =new ArrayList();
        for (int i=1;i<=1000;i++){
            list.add(i);
        }
        int m=1;
        while (list.size()!=1){
            if(m!=3){
                int first =list.remove(0);
                list.add(first);
                m++;
            }else {
                list.remove(0);
                m=1;

            }
        }
        System.out.println(list.get(0));
    }

}
