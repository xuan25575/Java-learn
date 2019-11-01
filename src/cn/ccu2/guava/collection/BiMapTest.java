package cn.ccu2.guava.collection;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * @Description biMap 实现键值对的双向映射
 * key - value  都是保证唯一的，
 * 在BiMap中，如果你想把键映射到已经存在的值，会抛出IllegalArgumentException异常。
 * 如果对特定值，你想要强制替换它的键，请使用 BiMap.forcePut(key, value)。
 * @date 2019/10/24 16:50
 */
public class BiMapTest {
    public static void main(String args[]){
        BiMap<Integer, String> empIDNameMap = HashBiMap.create();

        empIDNameMap.put(new Integer(101), "Mahesh");
        empIDNameMap.put(new Integer(102), "Sohan");
        empIDNameMap.put(new Integer(103), "Ramesh");
        // error
//        empIDNameMap.put(new Integer(104), "Ramesh");

        //Emp Id of Employee "Mahesh"
        System.out.println(empIDNameMap.inverse().get("Ramesh"));
    }
}
