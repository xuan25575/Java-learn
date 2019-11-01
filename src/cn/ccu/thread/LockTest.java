package cn.ccu.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description TODO
 * @date 2019/11/1 18:33
 */
public class LockTest {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private class T implements Runnable {
        @Override
        public void run() {
            try {
                lock.lock();
                try {
                    System.out.println("开始等待");
                    condition.await();
                    System.out.println(Thread.currentThread().isInterrupted());
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void signal() {
        lock.lock();
        try {
            System.out.println("获得锁");
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockTest demo = new LockTest();
        new Thread(demo.new T()).start();
        Thread.sleep(2000);
        demo.signal();
    }
}
