package cn.ccu.Test;

import java.io.File;
import java.util.Date;

/**
 * @Description TODO
 * @date 2019/8/20
 */
public class Math {
    public static void main(String[] args) {

        File file = new File("D:\\data\\java 整理.pdf");
        System.out.println(file.getName());


        String s= "a";
        Date date = new Date();

        System.out.println(s+"_"+date);


        System.out.println(File.separatorChar);





//         int a =1;
//         int b =2;
//         int c = (a+b)*10;




    }
    public  static  int math(int a,int b){
        return (a+b)/10;
    }
}
