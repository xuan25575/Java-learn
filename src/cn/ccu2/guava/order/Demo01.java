package cn.ccu2.guava.order;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Demo01 {
    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("10","2","5");
        String min = Ordering.from(Comparator.comparingInt((String s) -> Integer.valueOf(s))).min(list);
        System.out.println(min);

        // onResultOf  使用
        // 对集合中元素调用Function，再按返回值用当前排序器排序。
        Ordering<FastCode> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<FastCode, String>() {
            public String apply(FastCode foo) {
                return foo.getMeaning();
            }
        });
        FastCode fastCode = new FastCode();
        fastCode.setMeaning("11");
        FastCode fastCode2 = new FastCode();
        fastCode2.setMeaning("12");
        FastCode fastCode3 = new FastCode();
        fastCode3.setMeaning("13");


        List<FastCode> fastCodeList = Lists.newArrayList(fastCode2,fastCode,fastCode3);
        Collections.sort(fastCodeList,ordering);

        for (FastCode code : fastCodeList) {
            System.out.print(code.getMeaning()+" ");
        }
    }
}
