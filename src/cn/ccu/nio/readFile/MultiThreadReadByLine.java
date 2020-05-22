package cn.ccu.nio.readFile;

public class MultiThreadReadByLine {

    public static void main(String[] args){
        FileReader fileReader = new FileReader("D:\\BaiduNetdiskDownload/Netty权威指南 第2版 完整版.pdf",1000,3);
        fileReader.registerHanlder(new FileLineDataHandler());
        fileReader.startRead();
    }


}
