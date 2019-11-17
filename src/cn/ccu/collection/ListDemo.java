package cn.ccu.collection;

import com.google.common.collect.Lists;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.sql.SQLOutput;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {
        List<Integer> list  = Lists.newArrayList(1,2,3,4,5,6);

        list.forEach(System.out::println);
    }
}
