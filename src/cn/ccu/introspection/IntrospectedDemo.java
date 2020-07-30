package cn.ccu.introspection;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * @Author huang_2
 * @Date 2020/1/12 4:37 下午
 * @Description javabean内省机制操作person类
 */
public class IntrospectedDemo {


    public static void main(String[] args) throws Exception {


        Person p = Person.class.newInstance();
        System.out.println(p);
        //1.获取Javabean的描述对象
//        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class,Object.class);

        //2.获取javabean 的属性的描述器
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();


        for (PropertyDescriptor pd : propertyDescriptors) {

            System.out.println("属性名称："+pd.getName());
            System.out.println("setter："+pd.getWriteMethod());
            System.out.println("getter："+pd.getReadMethod());
            if("name".equals(pd.getName())){
                Method writeMethod = pd.getWriteMethod();
                writeMethod.invoke(p,"111");
            }
            System.out.println("----------------------");
        }
        System.out.println(p);
    }
}
