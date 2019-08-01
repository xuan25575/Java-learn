package cn.ccu.builder;

/**
 *     build 建造者设计模式。
 */
public class Test {

    public static void main(String[] args) {

        Director director = new Director();//创建装机人员
        director.setBuilder(new LowConfigBuilder()); //告诉装机人员电脑配置，这里为低配版
        director.createComputer(); //装机人员开始组装
        Computer computer = director.getComputer(); //从装机人员获取组装好的电脑
        System.out.print("电脑配置：" + computer.toString());  //查看电脑配置
        System.out.println();

        System.out.println("======================================");

        director.setBuilder(new HighConfigBuilder());
        director.createComputer();
        Computer computer1 = director.getComputer();
        System.out.print("电脑配置：" + computer1.toString());


    }
}
