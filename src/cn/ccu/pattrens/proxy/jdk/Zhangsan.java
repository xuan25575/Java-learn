package cn.ccu.pattrens.proxy.jdk;

import java.lang.reflect.Method;

/**
 * @Description Zhangsan
 * @date 2019/9/21 23:12
 */
public class Zhangsan implements Person {

    @Override
    public void rentingHouse(String name) {
        System.out.println(name +" 想要找房子");
    }

    @Override
    public void findLove() {

    }


    public static void main(String[] args) {


        System.out.println(Person.class.getName());

//        Method[] methods = Zhangsan.class.getMethods();
//        for(Method m : methods){
//            System.out.println(m.getParameterTypes());
//        }
    }
}
