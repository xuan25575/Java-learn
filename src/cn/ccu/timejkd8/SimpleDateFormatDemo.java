package cn.ccu.timejkd8;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatDemo {

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date parse = format.parse("2019-08-23 12:32:44");
         Date parse1 = format.parse("2019-04-24 12:32:44");

        System.out.println(parse);
        System.out.println(parse1);

    }
}
