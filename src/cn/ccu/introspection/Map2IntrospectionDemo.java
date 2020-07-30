package cn.ccu.introspection;

import java.util.Map;

/**
 * @Author huang_2
 * @Date 2020/1/12 5:24 下午
 * @Description javabean 和map 相互转换
 */
public class Map2IntrospectionDemo {

    public static void main(String[] args) throws Exception {

        Person p = new Person();
        p.setId(1);
        p.setAge(12);
        p.setName("zhangsan");

        Map<String, Object> stringObjectMap = BeanUtils.beanToMap(p);

        for (Map.Entry<String, Object> entry : stringObjectMap.entrySet()) {

            System.out.println(entry.getKey());
            System.out.println(entry.getValue());

        }
        Person person = BeanUtils.mapToBean(stringObjectMap, Person.class);
        System.out.println(person);

    }

}
