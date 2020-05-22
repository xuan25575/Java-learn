package cn.ccu.collection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Arrays2SteamDistinctDemo {

    public static void main(String[] args) {

        List<Integer> integers = Arrays.asList(1, 1, 3, 5, 3, 44, 23, 23);
        //List<Integer> collect = integers.stream().distinct().collect(Collectors.toList());

        List<Integer> collect  = integers.stream().distinct().sorted().collect(Collectors.toList());
        System.out.println(collect);

    }
}
