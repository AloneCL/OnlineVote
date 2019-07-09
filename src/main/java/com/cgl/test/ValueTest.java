package com.cgl.test;

/**
 * @Auther: CGL
 * @Date: 2019/7/9 10:59
 * @Description:
 */
public class ValueTest {
    public static void change(int v){
        v = 66;
        System.out.println("函数中修改后的v："+v);
    }

    public static void change(String s){
        s = "hello";
        System.out.println("函数中修改后的s："+s);
    }

    public static void change(StringBuilder sb){
        sb.append(" world");
        System.out.println("函数中修改后的sb："+sb.toString());
    }

    public static void main(String[] args) {
        int value = 1000;
        String s = "world";
        StringBuilder stringBuilder = new StringBuilder("Hello");
        change(value);
        change(s);
        change(stringBuilder);
        System.out.println(value);
        System.out.println(s);
        System.out.println(stringBuilder);

    }
}
