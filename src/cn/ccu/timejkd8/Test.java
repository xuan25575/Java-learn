package cn.ccu.timejkd8;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

/**
 * @Description TODO
 * @date 2019/9/24 15:57
 */
public class Test {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("Today : " + today);
        LocalDate birthDate = LocalDate.of(1993, Month.OCTOBER, 19);
        LocalDate localDate = LocalDate.parse("2019-09-22");
        System.out.println("BirthDate : " + birthDate);

//        Period p = Period.between(birthDate, today);

        Period p = Period.between(localDate, today);
        System.out.printf("年龄 : %d 年 %d 月 %d 日", p.getYears(), p.getMonths(), p.getDays());
        System.out.println();

        String className = Test.class.getMethods()[1].getDeclaringClass().getName();
        System.out.println(className);

    }
}
