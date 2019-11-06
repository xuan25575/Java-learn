package cn.ccu.timejkd8;

import java.lang.reflect.Method;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @Description 日期 类
 *
 * Instant         时间戳
 * Duration        持续时间、时间差
 * LocalDate       只包含日期，比如：2018-09-24
 * LocalTime       只包含时间，比如：10:32:10
 * LocalDateTime   包含日期和时间，比如：2018-09-24 10:32:10
 * Peroid          时间段
 * ZoneOffset      时区偏移量，比如：+8:00
 * ZonedDateTime   带时区的日期时间
 * Clock           时钟，可用于获取当前时间戳
 * java.time.format.DateTimeFormatter      时间格式化类
 *
 * @date 2019/9/24 15:57
 */
public class Test {

    public static void main(String[] args) {


        // 测试本地时间
       //  testLocalDate();

        String className = Test.class.getMethods()[1].getDeclaringClass().getName();
        System.out.println(className);
        LocalTime time = LocalTime.now();
        System.out.println("当前时间=" + time);


        // 日期格式化
        testFormatter();

        // 比较日期
        compareDateTest();

        // 测试时区
        testTimezone();

    }


    private static void testLocalDate(){
        LocalDate today = LocalDate.now();
        System.out.println("Today : " + today.toString());
        LocalDate birthDate = LocalDate.of(1993, Month.OCTOBER, 19);
        LocalDate localDate = LocalDate.parse("2019-09-22");
        System.out.println("BirthDate : " + birthDate);

        //   Period p = Period.between(birthDate, today);
        // 时间段
        Period p = Period.between(localDate, today);
        System.out.printf("年龄 : %d 年 %d 月 %d 日", p.getYears(), p.getMonths(), p.getDays());
        System.out.println();

        //判断日期是否相等。
        LocalDate date = LocalDate.of(2018, 9, 24);
        System.out.println("日期是否相等=" + today.equals(date));

        // 日期增量
        testDateAndTimeInc();
    }



    private static  void testFormatter(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 日期时间转字符串
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        System.out.println("nowText=" + nowText);

        // 字符串转日期时间
        String datetimeText = "1999-12-31 23:59:59";
        LocalDateTime datetime = LocalDateTime.parse(datetimeText, formatter);
        System.out.println(datetime);


        // 解析日期
        String dateText = "20180924";
        LocalDate date = LocalDate.parse(dateText, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("格式化之后的日期=" + date);

        // 格式化日期
        dateText = date.format(DateTimeFormatter.ISO_DATE);
        System.out.println("dateText=" + dateText);
    }


    // 比较日期
    private static void compareDateTest(){
        LocalDate now = LocalDate.now();

        LocalDate date1 = LocalDate.of(2000, 1, 1);
        if (now.isAfter(date1)) {
            System.out.println("千禧年已经过去了");
        }

        LocalDate date2 = LocalDate.of(2020, 1, 1);
        if (now.isBefore(date2)) {
            System.out.println("2020年还未到来");
        }
    }

    public static void testDateAndTimeInc(){

      // 时间增量
        LocalTime time = LocalTime.now();
        LocalTime newTime = time.plusHours(2);
        System.out.println("newTime=" + newTime);

        // 日期增量
        LocalDate date = LocalDate.now();
        LocalDate newDate = date.plus(1, ChronoUnit.WEEKS);
        System.out.println("newDate=" + newDate);
    }


    // 时区
    public static void testTimezone(){

        ZoneId.systemDefault(); // 默认的时区

        // 上海时间
        ZoneId shanghaiZoneId = ZoneId.of("Asia/Shanghai");
        ZonedDateTime shanghaiZonedDateTime = ZonedDateTime.now(shanghaiZoneId);

        // 东京时间
        ZoneId tokyoZoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime tokyoZonedDateTime = ZonedDateTime.now(tokyoZoneId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("上海时间: " + shanghaiZonedDateTime.format(formatter));
        System.out.println("东京时间: " + tokyoZonedDateTime.format(formatter));
    }


    // 互相转换
    public static Date localDate2Date(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static LocalDate date2LocalDate(Date date) {
        if(null == date) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    // 日期格式化
    public static String formatDate(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
