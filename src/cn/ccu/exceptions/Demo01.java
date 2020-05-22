package cn.ccu.exceptions;

public class Demo01 {

    public static void main(String[] args){
        String s = "abc";
        if(s.equals("abc")) {
            //异常抛出可参看 parseInt源码
            Integer.parseInt(s);
            //throw new NumberFormatException();
        } else {
            System.out.println(s);
        }
        //function();
    }
}
