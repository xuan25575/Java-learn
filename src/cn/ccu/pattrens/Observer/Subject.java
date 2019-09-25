package cn.ccu.pattrens.Observer;

import java.util.Observable;

/**
 * @Description Subject 主题
 * @date 2019/9/18 21:41
 */
public class Subject extends Observable{

    private Object object ; // 主题内容


    // 发布
    public void publish(Object obj){
        this.object  = obj;
        setChanged();
        notifyObservers();
    }



    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
