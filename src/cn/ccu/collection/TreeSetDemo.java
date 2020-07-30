package cn.ccu.collection;

import cn.ccu.introspection.Person;

import java.util.Comparator;
import java.util.TreeSet;

/**
 *
 * 2种排序方式：自然排序或者根据提供的Comparator进行排序
 *
 *  (1）TreeSet继承于AbstractSet，并且实现了NavigableSet接口。
 * （2）TreeSet是一个包含有序的且没有重复元素的集合，通过TreeMap实现。TreeSet中含有一个"NavigableMap类型的成员变量"m，而m实际上是"TreeMap的实例"。
 *
 *
 * 1、不能有重复的元素；
 * 2、具有排序功能；
 * 3、TreeSet中的元素必须实现Comparable接口并重写compareTo()方法，
 * TreeSet判断元素是否重复 、以及确定元素的顺序 靠的都是这个方法；
 *
 *   ①对于Java类库中定义的类，TreeSet可以直接对其进行存储，如String，Integer等,因为这些类已经实现了Comparable接口);
 *   ②对于自定义类，如果不做适当的处理，TreeSet中只能存储一个该类型的对象实例，否则无法判断是否重复。
 * 4、依赖TreeMap。
 * 5、相对HashSet,TreeSet的优势是有序，劣势是相对读取慢。根据不同的场景选择不同的集合。
 *
 * 如果将compareTo()返回值写死为0，元素值每次比较，都认为是相同的元素，这时就不再向TreeSet中插入除第一个外的新元素。所以TreeSet中就只存在插入的第一个元素。
 * 如果将compareTo()返回值写死为1，元素值每次比较，都认为新插入的元素比上一个元素大，于是二叉树存储时，会存在根的右侧，读取时就是正序排列的。
 * 如果将compareTo()返回值写死为-1，元素值每次比较，都认为新插入的元素比上一个元素小，于是二叉树存储时，会存在根的左侧，读取时就是倒序序排列的。
 *
 *  public class Person implements Comparable<Person> {
 *     private String name;
 *     private int age;
 *     ...
 *     public int compareTo(Person o) {
 *         return 0;                //当compareTo方法返回0的时候集合中只有一个元素
 *         return 1;                //当compareTo方法返回正数的时候集合会怎么存就怎么取
 *         return -1;                //当compareTo方法返回负数的时候集合会倒序存储
 *     }
 * }
 *
 *
 */
public class TreeSetDemo {

    public static void main(String[] args) {

//        demoOne();
        demoTwo();

    }

    /**
     * 执行结果：会抛出一个 异常：java.lang.ClassCastException
     * 显然是出现了类型转换异常。
     * 原因在于我们需要告诉TreeSet如何来进行比较元素，如果不指定，就会抛出这个异常
     */
    public static void demoOne() {
        TreeSet<Person> ts = new TreeSet<>();
        ts.add(new Person("张三", 11));
        ts.add(new Person("李四", 12));
        ts.add(new Person("王五", 15));
        ts.add(new Person("赵六", 21));

        System.out.println(ts);
    }

    public static void demoTwo() {

        //需求:将字符串按照长度排序
        TreeSet<String> ts = new TreeSet<>(new TreeSetDemo().new CompareByLength());        //Comparator c = new CompareByLen();
        ts.add("aaaaaaaa");
        ts.add("z");
        ts.add("wc");
        ts.add("nba");
        ts.add("cba");

        System.out.println(ts);//[z, wc, nba, aaaaaaaa]
    }


    //定义一个类，实现Comparator接口，并重写compare()方法，
    class CompareByLength implements Comparator<String> {

//        @Override
//        public int compare(String s1, String s2) {        //按照字符串的长度比较
//            int num = s1.length() - s2.length();        //长度为主要条件
//            return num == 0 ? s1.compareTo(s2) : num;    //内容为次要条件
//        }

        @Override
        public int compare(String s1, String s2) {        //按照字符串的长度比较
            return s1.length() - s2.length();        //长度
        }
    }




}
