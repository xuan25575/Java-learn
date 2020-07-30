package cn.ccu.introspection;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author huang_2
 * @Date 2020/1/12 5:27 下午
 * @Description beanUtils工具类
 * 可查阅： apache commons  BeanUtils工具类。
 */
public class BeanUtils {


    public static Map<String, Object> beanToMap(Object bean) throws Exception {
        Map<String, Object> map = new HashMap<>();

        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            String name = pd.getName();
            Object value = pd.getReadMethod().invoke(bean);
            map.put(name, value);
        }
        return map;
    }

    public static <T>T mapToBean(Map<String, Object> map, Class<T> beanType) throws Exception {

        // bean 实例
        T instance = beanType.newInstance();

        BeanInfo beanInfo = Introspector.getBeanInfo(beanType, Object.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            String name = pd.getName();
            // 调用setter方法设置值
            pd.getWriteMethod().invoke(instance,map.get(name));
        }
        return instance;
    }

    // 实现一个通用的方法   为任意的一个javabean的任意属性赋任意值
    public static void setProperty(Object bean, String fieldName, Object value) throws Exception {
        // 创建属性描述器
        PropertyDescriptor descriptor = new PropertyDescriptor(fieldName, bean.getClass());
        // 获得 写方法
        Method writeMethod = descriptor.getWriteMethod();
        // 调用 写方法
        writeMethod.invoke(bean, value);
    }
}