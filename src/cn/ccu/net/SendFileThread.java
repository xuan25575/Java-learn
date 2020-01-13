package cn.ccu.net;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

/**
 * @Description 发送方
 * @date 2019/12/14 22:19
 */
public class SendFileThread  extends Thread{
    private Socket socket= null;
    private DataOutputStream dos;
    private DataInputStream dis;
    private RandomAccessFile rad;
    private Container contentPanel;
    private JFrame frame;
    private JProgressBar progressbar;
    private JLabel label;

    public SendFileThread(){
        frame=new JFrame("文件传输");
        try {
            socket = new Socket("localhost", 8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        // 文件选择
        JFileChooser fc = new JFileChooser();
        int status= fc.showOpenDialog(null);
        if (status==JFileChooser.APPROVE_OPTION) {
            String path = fc.getSelectedFile().getPath();
            try {
                // 客户端开始写
                dos = new DataOutputStream(socket.getOutputStream());
                // 读取服务端数据。
                dis = new DataInputStream(socket.getInputStream());
                // 写 ok
                dos.writeUTF("ok");
                // 这个从这个路径下读取
                rad = new RandomAccessFile(path, "r");
                File file = new File(path);

                // 缓冲区
                byte[] buf = new byte[1024];
                dos.writeUTF(file.getName());
                dos.flush();
                // 读取服务器端写会的 ok
                String rsp = dis.readUTF();

                if (rsp.equals("ok")) {
                    // 读取服务端发送过来的文件 大小 size，用于RandomAccessFile 从哪个位置开始读取 。
                    long size = dis.readLong();//读取文件已发送的大小

                    // 客户端 将RandomAccessFile 读取文件的大小 用long字节发送给服务端
                    dos.writeLong(rad.length());
                    // 客户端 写ok
                    dos.writeUTF("ok");
                    dos.flush();
                    //如果没有临时文件 ，则是从0 开始。 有的话从 从读取的size开始。
                    long offset = size;//字节偏移量

                    int barSize=(int) (rad.length()/1024);
                    int barOffset=(int)(offset/1024);
                    // 绘制传输界面
                    paint(barSize,barOffset,file);

                    //从文件指定位置开始传输
                    int length;
                    // 没有读取完。
                    if (offset < rad.length()) {
                        // 设置读取位置。
                        rad.seek(offset);
                        while((length = rad.read(buf))>0){
                            // 将数据写到服务端
                            dos.write(buf,0,length);
                            // 设置进度条。
                            progressbar.setValue(++barOffset);
                            dos.flush();
                        }
                    }
                    label.setText(file.getName()+" 发送完成");
                }

                dis.close();
                dos.close();
                rad.close();
            } catch (IOException e) {
                label.setText(" 取消发送,连接关闭");
            }finally {
                frame.dispose();
            }

        }
    }

    /**
     * 传输界面
     * @param barSize
     * @param barOffset
     * @param file
     */
    private void paint(int barSize, int barOffset,File file){
        //传输界面
        frame.setSize(380,120);
        contentPanel = frame.getContentPane();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        progressbar = new JProgressBar();//进度条
        label=new JLabel(file.getName()+" 发送中");
        contentPanel.add(label);
        progressbar.setOrientation(JProgressBar.HORIZONTAL);
        progressbar.setMinimum(0);
        progressbar.setMaximum(barSize);
        progressbar.setValue(barOffset);
        progressbar.setStringPainted(true);
        progressbar.setPreferredSize(new Dimension(150, 20));
        progressbar.setBorderPainted(true);
        progressbar.setBackground(Color.RED);

        JButton cancel=new JButton("取消");
        JPanel barPanel=new JPanel();
        barPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        barPanel.add(progressbar);
        barPanel.add(cancel);
        contentPanel.add(barPanel);
        cancel.addActionListener(new cancelActionListener());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    class cancelActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e3){
            try {
                label.setText(" 取消发送,连接关闭");
                JOptionPane.showMessageDialog(frame, "取消发送给，连接关闭!", "提示：", JOptionPane.INFORMATION_MESSAGE);
                dis.close();
                dos.close();
                rad.close();
                frame.dispose();
                socket.close();
            } catch (IOException e1) {

            }
        }
    }


}
