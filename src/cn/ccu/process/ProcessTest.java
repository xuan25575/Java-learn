package cn.ccu.process;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @Description TODO
 * @date 2019/11/1 18:30
 */
public class ProcessTest {


    public static void main(String[] args)  {
       // ls();

        ByteBuffer buffer = ByteBuffer.allocateDirect(4100);
        System.out.println(buffer.remaining());
    }

    public static  void ls() throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
//        String cmd = "ls -l";
        String cmd = "dir";
        Process process = runtime.exec(cmd);
        int value = process.waitFor();
    }
}
