package cn.ccu2.guava.collection;

import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;

import java.util.Iterator;
import java.util.Set;

/**
 * @Description TODO
 * @date 2019/10/24 17:16
 */
public class RangeSetTest {
    public static void main(String[] args) {
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10)); // {[1,10]}
        rangeSet.add(Range.closedOpen(11, 15));//不相连区间:{[1,10], [11,15)}
        rangeSet.add(Range.closedOpen(15, 20)); //相连区间; {[1,10], [11,20)}
        rangeSet.add(Range.openClosed(0, 0)); //空区间; {[1,10], [11,20)}
        rangeSet.remove(Range.open(5, 10)); //分割[1, 10]; {[1,5], [10,10], [11,20)}

        //请注意，要合并Range.closed(1, 10)和Range.closedOpen(11, 15)这样的区间，
        // 你需要首先用Range.canonical(DiscreteDomain)对区间进行预处理，
        // 例如DiscreteDomain.integers()。



        Set<Range<Integer>> ranges = rangeSet.asRanges();
        //Iterator<Range<Integer>> rangeIterator = ranges.iterator();
        for(Iterator<Range<Integer>> iterator = ranges.iterator();iterator.hasNext();) {
         //   iterator.next().canonical(DiscreteDomain.integers());
            System.out.println(iterator.next());
        }
        //System.out.println(rangeSet);
    }
}
