package cn.ccu.pattrens.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description  中介
 * @date 2019/9/21 23:10
 */
public class Intermediary  implements InvocationHandler{

    private Person target;

    public Intermediary(Person target) {
        this.target = target;
    }

    public Person getInstance(){
        Class<?> clazz = target.getClass();
       return (Person)Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(),this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("通过中介找房子");
        method.invoke(target,args);
        System.out.println("中介找到房子");
        return null;
    }
}
