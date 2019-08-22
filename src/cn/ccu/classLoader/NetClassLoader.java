package cn.ccu.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * @Description TODO
 * @date 2019/8/20
 */
public class NetClassLoader  extends ClassLoader{
    private String classPath;
    private String packageName = "cn.ccu.reflect";

    public NetClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> aClass = findLoadedClass(name);
        if(aClass != null){
            return aClass;
        }
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
            URL url = new URL(path);
            InputStream in = url.openStream();
            ByteArrayOutputStream out  = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
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

    private byte[] deCode(byte[] src){
        byte[] deCode = null;
        // 对src 进行解码
        return deCode;
    }

}
