package cn.ccu.Test;

/**
 * @Author huang_2
 * @Date 2020/3/17 1:50 下午
 * @Description TODO
 */
public class Test01 {

    public static strictfp void main(String[] args) {

        int a = 5; // 0000 0101
        int b = 3; // 0000 0011
        a |= b; // 0000 00111
        System.out.println(a);

    }
}
