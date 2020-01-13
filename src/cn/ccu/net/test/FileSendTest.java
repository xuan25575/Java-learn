package cn.ccu.net.test;

import cn.ccu.net.SendFileThread;

/**
 * @Description TODO
 * @date 2019/12/14 22:26
 */
public class FileSendTest {
    public static void main(String[] args) {
        SendFileThread sf = new SendFileThread();
        sf.start();
     }
}
