package cn.ccu.pattrens.Observer;

/**
 * @Description Main java 观察者模式。
 * @date 2019/9/18 21:51
 */
public class Main {
    public static void main(String[] args) {

        Subject subject = new Subject();

        Observer1 observer1  = new Observer1(subject);
        Observer2 observer2  = new Observer2(subject);

        subject.publish("hello , 你好！！");

    }
}
