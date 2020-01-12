package cn.ccu.pattrens.Observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @Description TODO
 * @date 2019/9/18 21:45
 */
public class Observer1 implements Observer {

    private Subject subject; //主题


    public Observer1(Subject subject) {
        this.subject = subject;
        subject.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Subject){
            Subject subject = (Subject)o;
            Object content = subject.getObject();
            System.out.println("观察者2 收到主题消息："+ content);
        }
    }

}
