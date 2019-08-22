package cn.ccu.reflect;

public class ServiceImpl {

    public void say(String name){
        System.out.println("hello , "+ name);
    }


    public static void main(String[] args) {
        //ExtClassLoader
        ClassLoader parent = ServiceImpl.class.getClassLoader().getParent();
        if(parent != null){
            System.out.println(parent);
            parent = parent.getParent();
        }
        // null
        System.out.println(parent);

        System.out.println("-----------------");
        // AppClassLoader
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println("-----------------");

        System.out.println(ServiceImpl.class.getClassLoader().getResource("").toString());
    }
}
