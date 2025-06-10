package com.example.demo2;
public interface Spirit {
     int x = 0;//x坐标
     int y = 0;//y坐标
     int direct = 0;//方向(0->上 1->右 2->下 3->左)
     int speed = 0;//初始速度为2
     int type = 0;//种类(0->己方 1->敌方)
     boolean isLive = true;//是否存在
    public void moveUp();
    public void moveRight();
    public void moveDown();
    public void moveLeft();
}
