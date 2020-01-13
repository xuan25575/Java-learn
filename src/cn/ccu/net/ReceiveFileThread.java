package cn.ccu.net;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 *   1.传输开始之前发送方先向接收方发送一个确认信息，然后再向接收方发送准备发送的文件的文件名。
 *   2.接收方收到确认信息之后，接收从发送方发送过来的文件名，
 *   3. 接收完之后向发送方发送一个确认信息表示文件名接收完毕，然后接收方根据收到的文件名创建一个“.temp”File对象
 *   和一个“.temp”RandomAccessFile对象。获取这个File对象所对应文件的长度（大小）
 *   （这个长度就是接收方已经接受的长度，如果之前没有接收过这个文件，长度就为0），并把文件长度发送给发送方。
 *   4. 发送方收到确认信息之后，接收接受方发送的文件长度，然后向接收方发送准备发送的文件的总长度，
 *   并向接收方发送一个确认信息。然后根据接收方发送的文件长度，从文件对应长度的位置开始发送。
 *   5. 接收方收到确认信息之后，接受发送方发送过来的数据，然后从此文件的末尾写入。
 *   接受完成之后再将“.temp”文件重命名为正常的文件名。
 *
 *
 *
 * @Description 接受方
 * @date 2019/12/14 22:24
 */
public class ReceiveFileThread  extends Thread{

    //  服务端
    private ServerSocket connectSocket=null;
    // 接受客户端
    private Socket socket=null;
    private JFrame frame;
    private Container contentPanel;
    private JProgressBar progressbar;
    private DataInputStream dis;
    private DataOutputStream dos;
    private RandomAccessFile rad;
    private JLabel label;


    /**
     * 初始化 ServerSocket  等待socket
     */
    public ReceiveFileThread(){
        frame=new JFrame("接收文件");
        try {
            connectSocket = new ServerSocket(8080);
            socket = connectSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            //读取 客户端信息
            dis= new DataInputStream(socket.getInputStream());
            // 用于服务端写会客户端
            dos= new DataOutputStream(socket.getOutputStream());
            dis.readUTF();
            int permit = JOptionPane.showConfirmDialog(frame, "是否接收文件","文件传输请求：", JOptionPane.YES_NO_OPTION);
            if (permit == JOptionPane.YES_OPTION) {
                String filename = dis.readUTF();
                // 写回客户端
                dos.writeUTF("ok");
                //将缓冲区的数据写入到流中
                dos.flush();
                // 拿到已读取的临时文件
                File file = new File(filename+".temp");
                //  使用 RandomAccessFile 读取
                rad = new RandomAccessFile(filename+".temp", "rw");

                //获得文件大小
                long size = 0;
                if(file.exists() && file.isFile()){
                    // 用作 RandomAccessFile 的 seek 方法设置从哪出读取。
                    size = file.length();
                }
                // 将一个 long 值以 8-byte 值形式写入基础输出流中，先写入高字节.
                // 将读取的临时文件的大小用long字节，写回客户端
                dos.writeLong(size);//发送已接收的大小
                dos.flush();
                // 读取客户端发送的 文件总大小。
                long allSize = dis.readLong();
                // 读取 客户端写过来的 ok
                String rsp = dis.readUTF();
                int barSize=(int)(allSize/1024);
                int barOffset=(int)(size/1024);
                // 画传输界面
                paint(barSize,barOffset,filename);

                //接收文件
                if (rsp.equals("ok")) {
                    // 知道从哪个位置读取数据
                    rad.seek(size);
                    int length;
                    byte[] buf=new byte[1024];
                    while((length = dis.read(buf, 0, buf.length))!=-1){
                        rad.write(buf,0,length);
                        // 加进度条
                        progressbar.setValue(++barOffset);
                    }
                    System.out.println("end");
                }
                label.setText(filename+" 结束接收");
                dis.close();
                dos.close();
                rad.close();
                frame.dispose();

                //文件重命名
                if (barOffset >= barSize) {
                    file.renameTo(new File(filename));
                }
            }else{
                dis.close();
                dos.close();
                frame.dispose();
            }

        } catch (IOException e) {
            label.setText(" 已取消接收，连接关闭！");
        }finally {
            frame.dispose();
        }
    }

    /**
     *  传输界面
     * @param barSize 总进度条
     * @param barOffset 到哪的进度条
     * @param filename  文件名
     */
    private void paint(int barSize, int barOffset,String filename){
        //传输界面
        frame.setSize(300,120);
        contentPanel =frame.getContentPane();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        progressbar = new JProgressBar();//进度条
        label = new JLabel(filename+" 接收中");
        contentPanel.add(label);
        progressbar.setOrientation(JProgressBar.HORIZONTAL);
        progressbar.setMinimum(0);
        progressbar.setMaximum(barSize);
        progressbar.setValue(barOffset);
        progressbar.setStringPainted(true);
        progressbar.setPreferredSize(new Dimension(150, 20));
        progressbar.setBorderPainted(true);
        progressbar.setBackground(Color.RED);

        JButton cancel = new JButton("取消");
        JPanel barPanel=new JPanel();
        barPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        barPanel.add(progressbar);
        barPanel.add(cancel);
        contentPanel.add(barPanel);
        cancel.addActionListener(new cancelActionListener());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }





    /**
     *  取消按钮的事件监听
     */
    class cancelActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try {
                dis.close();
                dos.close();
                rad.close();
                JOptionPane.showMessageDialog(frame, "已取消接收，连接关闭！", "提示：", JOptionPane.INFORMATION_MESSAGE);
                label.setText(" 取消接收,连接关闭");
            } catch (IOException e1) {

            }
        }
    }
}
