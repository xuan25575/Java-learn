package cn.ccu.callback;

import com.sun.codemodel.internal.JOp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


/**
 * @Description TimerTest  10 秒钟之后 ， 第1条定时器消息才会显示出来
 * 回调  在这种模式中,可以指出某个特定事件发生时应该采取的动作
 * @date 2019/9/15 19:35
 */
public class TimerTest {

    private static int i ;

    private double j;

    private  TimePrinter timePrinter;


    public static void main(String[] args) {

       ActionListener actionListener = new TimePrinter();

       // 每隔10 秒通告 listen 一次.
       Timer timer = new Timer(10000, actionListener);

       timer.start();

       // 显示一个对话框。
        JOptionPane.showMessageDialog(null,"quick program ? ");

        System.exit(0);

    }
// --------------------------内部类测试-----------------------------------
    public static void print(){

    }

    public  void print2(){

    }

    private class  A{

        // public static  int a; // 报错


        // 报错。
//        static void say(){
//
//        }

        private void say(){

            print(); //


        }
    }

    static class  B{

        private static  int  b1; // final ?

        static void say(){

            int b  = i;
         //    double b1 = j;  报错
        }
    }

}

class  TimePrinter implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println ( " At the tone , the time is " + new Date() ) ;
        Toolkit.getDefaultToolkit().beep();
    }
}
