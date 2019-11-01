package cn.ccu.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * NIO Client.
 *
 * @author skywalker
 */
public class Client {

    /**
     * 测试NIO客户端的阻塞表现.
     */
    public void nioRead() throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(true);

        channel.connect(new InetSocketAddress("127.0.0.1", 10010));
        while (channel.isConnectionPending()) {
            channel.finishConnect();
        }

        ByteBuffer buffer = ByteBuffer.allocate(10);
        int read = channel.read(buffer);

        System.out.println(read);
    }

    /**
     * 测试阻塞/非阻塞的写.
     */
    public static void main(String[] args) throws IOException {
        /*SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(true);
        channel.connect(new InetSocketAddress("127.0.0.1", 10010));
        while (channel.isConnectionPending()) {
            channel.finishConnect();
        }
        byte[] dirty = new byte[163832];
        byte[] data = new byte[10];
        Arrays.fill(dirty, (byte) 'a');
        Arrays.fill(data, (byte) 'a');
        //脏数据
        channel.write(ByteBuffer.wrap(dirty));
        int writed = channel.write(ByteBuffer.wrap(data));
        System.out.println(writed);*/
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1", 8080));
        try {
            Thread.sleep(90000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        socket.close();
    }

    public void connectLocalHost() throws IOException, InterruptedException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1", 8080));
        Thread.sleep(90000);
        socket.close();
    }

}
