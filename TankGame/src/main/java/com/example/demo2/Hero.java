package com.example.demo2;

public class Hero extends Tank {
    private static Hero hero = null;

    private Hero(int x, int y) {//私有构造方法，避免外部创建实例
        super(x, y);
        setType(0);//初始化己方坦克类型 0->己方
        setDirect(0);//初始化己方坦克方向 0->上
        setSpeed(4);//初始化己方坦克速度为4
    }

    public static Hero getInstance() {//单例模式
        if (hero == null) {
            hero = new Hero(300,300);
        }
        return hero;
    }

}
