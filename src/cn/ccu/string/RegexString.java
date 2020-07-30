package cn.ccu.string;

/**
 * @Description TODO
 * @date 2019/9/23 15:31
 */
public class RegexString {


    public static void main(String[] args) {
        boolean matches = "1/16".matches("^1\\/\\d+$");
//        boolean matches2 = "11/62".matches("1+([/.]\\d+)?");
        System.out.println(matches);
        //System.out.println(matches2);

        String s1 = "1";
        String s2 = "1";
        System.out.println(s1.intern()== s2.intern());
    }
}
