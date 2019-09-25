package cn.ccu.pattrens.Observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @Description Observer2
 * @date 2019/9/18 21:45
 */
public class Observer2 implements Observer {

   // private Subject subject; //主题
    private Object obejct;

    public Observer2(Subject subject) {
      //  this.subject = subject;
        subject.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Subject){
            Subject subject = (Subject)o;
           this.obejct =  subject.getObject();
            display();
        }
    }

    private void display() {
        System.out.println("观察者2 收到主题消息："+ obejct );
    }
}
