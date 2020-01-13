package cn.ccu.io;

import java.io.*;

/**
 * @Description 文件断电续传
 * 我们去模拟一个中断行为的发生。这里是当targetFile的文件长度为3个字节则模拟抛出一个我们自定义的异常。
 * (我们可以想象为实际下载中，已经上传(下载)了”x”个字节的内容，
 * 这个时候网络中断了，那么我们就在网络中断抛出的异常中将”x”记录下来)。
 * @date 2019/12/14 19:19
 */
public class FilePowerOffTest{

    private static int position = -1;

    public static void main(String[] args) {
        // 源文件与目标文件
        File sourceFile = new File("D:/", "test.txt");
        File targetFile = new File("D:/", "test2.txt");
        // 输入输出流
        FileInputStream fis = null;
        FileOutputStream fos = null;
        // 数据缓冲区
        byte[] buf = new byte[1];

        try {
            fis = new FileInputStream(sourceFile);
            fos = new FileOutputStream(targetFile);
            // 数据读写
            while (fis.read(buf) != -1) {
                fos.write(buf);
                // 当已经上传了3字节的文件内容时，网络中断了，抛出异常
                if (targetFile.length() == 3) {
                    // 记录位置
                    position = 3;
                    throw new FileAccessException();
                }
            }
        } catch (FileAccessException e) {
            // 续传文件
            keepGoing(sourceFile,targetFile, position);
        } catch (FileNotFoundException e) {
            System.out.println("指定文件不存在");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭输入输出流
                if (fis != null)
                    fis.close();

                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 利用RandomAccessFile  的seek 方法 ---（设置到此文件开头测量到的文件指针偏移量，在该位置发生下一个读取或写入操作。）
     * 从断开的位置 开始读取文件。
     * @param source 源文件
     * @param target 目标文件
     * @param position  文件读取的偏移量
     */
    private static void keepGoing(File source, File target, int position) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            RandomAccessFile readFile = new RandomAccessFile(source, "rw");
            RandomAccessFile writeFile = new RandomAccessFile(target, "rw");
            readFile.seek(position);
            writeFile.seek(position);

            // 数据缓冲区
            byte[] buf = new byte[1];
            // 数据读写
            while (readFile.read(buf) != -1) {
                writeFile.write(buf);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class FileAccessException extends Exception {
}
