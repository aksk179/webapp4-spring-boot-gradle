package com.boot.webapp4springbootgradle.controller;

import java.io.File;

public class BrainDump {
    public static void main(String[] args) throws Exception{
        String str = "M캐피탈";

        int i = str.getBytes().length;
        System.out.println(i);
        //7

        function1();
        System.out.println("========");
        function2();

    }


    private static void function1() {
        try {
            File file = new File("c:/temp/a.txt");
            System.out.println("a");
            System.out.println("b");
        }catch (IllegalArgumentException e) {


        }catch (Exception e) {
            System.out.println("c");
        }finally {
            System.out.println("d");
        }
    }
    private static void function2() {
        try{
            System.out.println("A");
            System.out.println(100/0);
            System.out.println("B");
        }catch (Exception e) {
            System.out.println("C");
        }finally {
            System.out.println("D");
        }
    }

    public static void sqlfunc() { //c_no dept pay
        String sql = "select a.c_no dept" +
                "     from A a" +
                "     left outer join B" +
                "     on dept='aa'";
    }
}
