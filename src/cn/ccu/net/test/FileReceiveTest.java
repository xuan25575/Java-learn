package cn.ccu.net.test;

import cn.ccu.net.ReceiveFileThread;

/**
 * @Description java 文件断点续传 测试
 * @date 2019/12/14 22:26
 */
public class FileReceiveTest {

    public static void main(String[] args) {
        ReceiveFileThread  r  = new ReceiveFileThread();
        r.start();

    }
}
