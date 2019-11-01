package cn.ccu2.guava.collection;

/**
 * @Description TODO
 * @date 2019/10/24 17:34
 */
public class MultimapTest {
    /**
     * Multimap
     * 每个有经验的Java程序员都在某处实现过Map<K, List<V>>或Map<K, Set<V>>，并且要忍受这个结构的笨拙。例如，Map<K, Set<V>>通常用来表示非标定有向图。Guava的 Multimap可以很容易地把一个键映射到多个值。换句话说，Multimap是把键映射到任意多个值的一般方式。
     * 可以用两种方式思考Multimap的概念：
     * ”键-单个值映射”的集合：
     *  a -> 1 a -> 2 a ->4 b -> 3 c -> 5
     *  或者”键-值集合映射”的映射：
     *  a -> [1, 2, 4] b -> 3 c -> 5
     *  般来说，Multimap接口应该用第一种方式看待，但asMap()视图返回Map<K, Collection<V>>，
     *  让你可以按另一种方式看待Multimap。
     *  *** 重要的是，不会有任何键映射到空集合：一个键要么至少到一个值，要么根本就不在Multimap中。
     *  很少会直接使用Multimap接口，
     *  更多时候你会用ListMultimap或SetMultimap接口，它们分别把键映射到List或Set。
     */
}
