package cn.ccu.classLoader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @Description 自定义类加载器  方式二 继承URLClassLoader
 * @date 2019/8/20
 */
public class URLPathClassLoader extends URLClassLoader {

    private String packageName = "cn.ccu.reflect";

    public URLPathClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> aClass = findLoadedClass(name);
        if(aClass != null){
            return aClass;
        }
        if(!packageName.startsWith(name)){
            return super.findClass(name);
        }else {
            // 这一步逻辑 如何理解.
           return findClass(name);
        }
    }
}
