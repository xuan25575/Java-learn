package cn.ccu.stacktrance;

import java.util.Scanner;

/**
 * @Description StackTraceTest 递归阶乘函数的堆栈情况
 * @date 2019/9/15 22:03
 */
public class StackTraceTest {

    //阶乘  factorial
    public static int factorial(int n){

        System.out.println("factorial( "+ n + ") ") ;

        Throwable t = new Throwable();
        StackTraceElement[] stackTrace = t.getStackTrace();

        for(StackTraceElement f : stackTrace){
            System.out.println(f);
        }
        int r ;
        if(n <= 1)  r=1;
        else r =  n * factorial(n-1);
        System.out.println("return :" + r);
        return r;
    }

    public static void main(String[] args) {
        Scanner in= new Scanner(System.in);
        System.out.println("Enter n  : ");
        int n =  in.nextInt();
        factorial(n);
    }
}
