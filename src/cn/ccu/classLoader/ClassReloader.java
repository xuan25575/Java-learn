package cn.ccu.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Description 演示热部署
 * @date 2019/8/20
 */
public class ClassReloader extends ClassLoader {

    private String classPath;
    private  String classname = "cn.ccu.reflect.ServiceImpl";

    public ClassReloader(String classPath) {
        super();
        this.classPath = classPath;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classDate = getDate(name);
        if (classDate == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(classname, classDate, 0, classDate.length);
        }

    }

    private byte[] getDate(String className) {
        String path = classPath+className;
        try {
            InputStream in = new FileInputStream(path);
            ByteArrayOutputStream out  = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = 0;
            while (( length = in.read(buffer)) != -1){
                out.write(buffer,0,length);
            }
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) throws Exception {
        //  输出：\
        System.out.println("++" +File.separatorChar+"++");

       // System.out.println(packageName.startsWith("cn.ccu.reflect.ServiceImpl.class"));
        // 演示热部署
        // 先编译一个Class文件
        String path = "D:/ideaspace/Java-Patterns/src/cn/ccu/reflect/";
        ClassReloader pathClassLoader1= new ClassReloader(path);
        Class<?> c1 = pathClassLoader1.findClass("ServiceImpl.class");
        System.out.println(c1.newInstance());
        ClassReloader pathClassLoader2= new ClassReloader(path);
        Class<?> c2 = pathClassLoader2.findClass("ServiceImpl.class");
        System.out.println(c2.newInstance());

    }
}
