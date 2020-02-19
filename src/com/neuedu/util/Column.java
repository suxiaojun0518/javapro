package com.neuedu.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author SuXiaojun
 * @Date 2020/2/19 15:09
 * @Version 1.0
 */
@Target(ElementType.FIELD)//注解的作用域，TYPE为类下，METHOD为方法下，FIELD为属性
@Retention(RetentionPolicy.RUNTIME)//生命周期
public @ interface Column {
    String value();
}
