package cn.ccu.reflect;

import java.lang.reflect.Method;

public class ReflectDemo {

    public static Object reflect(){
        ServiceImpl service = null;
        try {
            service =  (ServiceImpl)Class.forName("cn.ccu.reflect.ServiceImpl").newInstance();
            Method method = service.getClass().getMethod("say", String.class);
            method.invoke(service,"zhangsan");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return service;
    }

    public static void main(String[] args) {
//         reflect();

        Class<?>[] interfaces = ReflectDemo.class.getInterfaces();
        for (Class clazz: interfaces
             ) {
            System.out.println(clazz);
        }

    }
}
