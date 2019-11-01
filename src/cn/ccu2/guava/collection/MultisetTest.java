package cn.ccu2.guava.collection;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @date 2019/10/24 17:26
 */
public class MultisetTest {

    /**
     * add(E)添加单个给定元素
     * iterator()返回一个迭代器，包含Multiset的所有元素（包括重复的元素）
     * size()返回所有元素的总个数（包括重复的元素）
     * 当把Multiset看作Map<E, Integer>时，它也提供了符合性能期望的查询操作：
     * count(Object)返回给定元素的计数。HashMultiset.count的复杂度为O(1),TreeMultiset.count的复杂度为O(log n)。
     * entrySet()返回Set<Multiset.Entry<E>>，和Map的entrySet类似。
     * elementSet()返回所有不重复元素的Set<E>，和Map的keySet()类似。
     * 所有Multiset实现的内存消耗随着不重复元素的个数线性增长。
     */
    public static void main(String[] args) {
        //统计一个词在文档中出现了多少次，传统的做法是这样的：
        String[] words = {"a","b","b","c"};
        Map<String, Integer> counts = new HashMap<String, Integer>();
        for (String word : words) {
            Integer count = counts.get(word);
            if (count == null) {
                counts.put(word, 1);
            } else {
                counts.put(word, count + 1);
            }
        }
        System.out.println(counts.get("b"));

        Multiset<String> multiset = HashMultiset.create();
        for (String word : words) {
            multiset.add(word);
        }
        System.out.println(multiset.count("b"));

    }
}
