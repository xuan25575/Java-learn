package cn.ccu.thread;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Description TODO
 * @date 2019/9/17 23:04
 */
public class Test {

    private static final  int FILE_QUEUE_SIZE = 10;
    private static BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    public static void main(String[] args) throws InterruptedException,IOException{
        for (int i = 0; i < 50; i++) {
            queue.put(new File(""));
        }
        System.out.println(".....");
    }
}
