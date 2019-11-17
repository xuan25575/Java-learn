package cn.ccu.collection;

import cn.ccu.stream.MilestonesValidTO;

import java.util.*;

/**
 * set集合 源码中是这样是实现的：
 * 构建一个按自然排序的空树集。必须实现Comparable接口。创建的是一个new TreeMap。
 * 不然报错 。
 */
public class SetDemo {

    public static void main(String[] args) {

        ArrayList<MilestonesValidTO> list2 = new ArrayList<MilestonesValidTO>();
        list2.add(new MilestonesValidTO("1","A","sea","2341"));
        list2.add(new MilestonesValidTO("1","A","sea","2341"));
        list2.add(new MilestonesValidTO("3","B","sea","2341"));
        list2.add(new MilestonesValidTO("4","A","sea","2341"));
        list2.add(new MilestonesValidTO("5","C","sea","2341"));


        Set<MilestonesValidTO> set = new TreeSet<>(Comparator.comparing(o->o.getMessage()));

        for(MilestonesValidTO temp :list2){
            set.add(temp);
        }

        Iterator<MilestonesValidTO> iterator =  set.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next().getId());
        }

    }
}
