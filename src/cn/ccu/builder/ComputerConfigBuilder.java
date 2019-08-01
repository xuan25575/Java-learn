package cn.ccu.builder;

public interface ComputerConfigBuilder {
    void setCPU();
    void setMemery();
    void setHardDisk();
    void setKeyboard();
    void setMouse();
    Computer getComputer();
}
