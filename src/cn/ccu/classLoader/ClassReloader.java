package cn.ccu.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Description 自定义类加载器
 * @date 2019/8/20
 */
public class PathClassLoader extends ClassLoader {

    private String classPath;
    private  String packageName = "cn.ccu.reflect";

    public PathClassLoader(String classPath) {
        this.classPath = classPath;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if(packageName.startsWith(name)){
            byte[] classDate = getDate(name);
            if(classDate==null){
                throw new ClassNotFoundException();
            }else {
                return defineClass(name,classDate,0,classDate.length);
            }
        }else {
            return super.findClass(name);
        }
    }

    private byte[] getDate(String className) {
        String path =  classPath+ File.separatorChar +
                className.replace('.',File.separatorChar)+".class";
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
        String path = "D:\\ideaspace\\Java-Patterns\\src";
        PathClassLoader pathClassLoader1= new PathClassLoader(path);
        Class<?> c1 = pathClassLoader1.findClass("cn.ccu.reflect.");
        System.out.println(c1.newInstance());
        PathClassLoader pathClassLoader2= new PathClassLoader(path);
        Class<?> c2 = pathClassLoader1.findClass("cn.ccu.reflect");
        System.out.println(c2.newInstance());

    }
}
