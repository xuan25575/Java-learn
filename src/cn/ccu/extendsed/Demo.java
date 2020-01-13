package cn.ccu.extendsed;

public class Demo {

    public static void main(String[] args) {
        Children.say();   // 子类可以用父类的静态方法 es6 也可以
        Parent.say();
    }
}

class Parent{
    public static void say(){
        System.out.println("say parent! ");
    }
}

class Children extends Parent{

}
