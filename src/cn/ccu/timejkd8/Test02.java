package cn.ccu.timejkd8;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.Locale;

public class Test02 {
    public static void main(String[] args) {
//        LocalDate birthDate = LocalDate.of(1993, 1, 1);
//        System.out.println(birthDate);

//        Tue Jan 01 00:00:00 GMT+08:00 2019

        System.out.println(cnToDate("Tue Jan 01 00:00:00 GMT+08:00 2019"));

        LocalDate today = LocalDate.now();
        System.out.println("Today : " + today.toString());
        LocalDate birthDate = LocalDate.of(1993, Month.OCTOBER, 19);
        Period period = Period.between(birthDate, today);
        System.out.println(period);
        System.out.println(period.getDays());

        LocalTime localTime  = LocalTime.now();
        LocalTime localTime2  = LocalTime.of(10,40);

        Duration duration = Duration.between(localTime, localTime2);
        System.out.println(duration);
        System.out.println(duration.getUnits());


        SimpleDateFormat format = new SimpleDateFormat("yyMM");
        String dateStr = format.format(new Date());
        System.out.println(dateStr);
    }

    public static String cnToDate(String dateString){
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", Locale.ENGLISH);
        try{
            Date date  = sdf.parse(dateString); //将字符串改为date的格式
            String resDate= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            return resDate;
        }catch(Exception e){
            return null;
        }
    }

}
