package cn.ccu.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RemoveEleInList {


    //
    public static void main(String[] args) {

        System.out.println(4/0);

//          removeEle04();


//          removeEle03();

        removeEle02();

//        removeEle01();
    }



    private static void removeEle04() {
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList<String>(list);
        for (String str : cowList) {
            if ("aa".equals(str)) {
                cowList.remove(str);
            }
        }
        System.out.println(cowList.size());
    }


    /**
     * Iterator 遍历不能 直接调用 list的 remove 来删除元素
     * 否则
     */
    private static void removeEle03() {
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        Iterator<String> it = list.iterator();
        while(it.hasNext()){
            String str = (String)it.next();
            if("aa".equals(str)){
                it.remove();
            }
        }
        System.out.println(list.size());
    }


    private static void removeEle02() {
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("cc");

//        for(String str :list){
//            list.remove(str);
//        }
        for (int i = 0; i < list.size(); i++) {
            list.remove(i);
        }
        System.out.println(list.size());
        System.out.println(list);
    }


    private static void removeEle01() {
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("cc");
        for (int i = list.size() - 1; i >= 0; i--) {
            String str = list.get(i);
            if ("aa".equals(str)) {
                list.remove(str);
            }
        }
        System.out.println(list.size());
    }
}
