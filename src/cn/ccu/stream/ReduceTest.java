package cn.ccu.stream;

import cn.ccu.stream.test.Transaction;
import com.google.common.collect.Lists;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ReduceTest {
    public static void main(String[] args) {

        Stream<String> stream = Stream.of("I", "love", "you", "too");
        Integer lengthSum = stream.reduce(0,  // 初始值　// (1)
        (sum, str) -> sum+str.length(), // 累加器 // (2)
                (a, b) -> a+b);// 部分和拼接器，并行执行时才会用到 // (3)
        // int lengthSum = stream.mapToInt(str -> str.length()).sum();
        System.out.println(lengthSum);


        Optional<Integer> max = Lists.newArrayList(1,22,4,5,6).stream()
                .reduce(Integer::max);
        if(max.isPresent()){
            System.out.println(max.get());
        }

        Integer reduce = Lists.newArrayList(1, 22, 4, 5, 6).stream()
                .reduce(3, Integer::max); // 初始值和累加器
        System.out.println(reduce);


        ArrayList<List<String>> strings = new ArrayList<>();
        strings.add(Arrays.asList("1", "2", "3", "4"));
        strings.add(Arrays.asList("2", "3", "4", "5"));
        strings.add(Arrays.asList("3", "4", "5", "6"));

        // 非并行流
        Integer reduce1 = strings.stream().flatMap(e -> e.stream()).reduce(0,
                (acc, e) -> acc + Integer.valueOf(e), (u, t) -> {
                    // 非并行流，不会执行第三个参数
                    System.out.println("u----:" + u);
                    // 这里的返回值并没有影响返回结果
                    return null;
                });
        System.out.println("reduce1:" + reduce1);

        // 并行流
        Integer reduce2 = strings.parallelStream().flatMap(e -> e.stream()).reduce(0,
                (acc, e) -> acc + Integer.valueOf(e), (u, t) -> {
                    // u，t分别为并行流每个子任务的结果
                    System.out.println("u----:" + u);
                    System.out.println("t----:" + t);
                    return u + t;
                });
        System.out.println("reduce2:" + reduce2);


    }


}
