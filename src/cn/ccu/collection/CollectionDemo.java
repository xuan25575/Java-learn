package cn.ccu.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description retainAll 方法集合取交集
 *  1、首先调用retainAll的方法
 *  2、通过判断集合的大小，来确定是否存在交集。不能通过方法返回的True和False来判断。
 * @date 2019/10/24 16:37
 */
public class CollectionDemo  {

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list1.add(i+"");
            if(i%2 == 0) {
                list2.add(i+"");
            }
            list3.add(i+"@");
        }
        // list1 与 list2 存在相同元素，list1集合只保留list2中存在的元素
        list1.retainAll(list2);
        if(list1.isEmpty()) {
            System.out.println("不包含");
        } else {
            System.out.println("包含");
        }
        System.out.println(list1);
        // list1 与 list3 不存在相同元素，list1集合变为空
        list1.retainAll(list3);
        if(list1.isEmpty()) {
            System.out.println("不包含");
        } else {
            System.out.println("包含");
        }
        System.out.println(list1);
    }
}
