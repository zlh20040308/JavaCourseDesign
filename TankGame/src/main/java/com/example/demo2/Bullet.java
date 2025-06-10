package com.example.demo2;

public class Bullet implements Runnable,Spirit {
    private int x;//子弹的x坐标
    private int y;//子弹的y坐标

    private int direct = 0;//子弹的方向0->上 1->右 2->下 3->左
    private int speed = 7;//子弹初始速度为7
    private boolean isLive = true;//子弹是否存在
    public Bullet(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public void moveUp() {
        y -= speed;
    }

    public void moveDown() {
        y += speed;
    }

    public void moveRight() {
        x += speed;
    }

    public void moveLeft() {
        x -= speed;
    }


    @Override
    public void run() {
        while (true) {
            switch (direct) {//0->上 1->右 2->下 3->左
                case 0:
                    moveUp();
                    break;
                case 1:
                    moveRight();
                    break;
                case 2:
                    moveDown();
                    break;
                case 3:
                    moveLeft();
                    break;
            }
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isLive)) {//如果越界或者死了就退出线程
                isLive = false;
                break;
            } else {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
