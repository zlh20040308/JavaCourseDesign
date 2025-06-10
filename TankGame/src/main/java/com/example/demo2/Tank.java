package com.example.demo2;

import java.util.Vector;

public class Tank implements Spirit{
    private int x;//坦克的x坐标
    private int y;//坦克的y坐标
    private int direct;//坦克的方向(0->上 1->右 2->下 3->左)
    private int speed = 2;//坦克初始速度为2
    private int type;//坦克的种类(0->己方 1->敌方)
    private boolean isLive = true;//坦克是否存在
    private int clipSize = 6;//弹夹容量（默认为6）
    Bullet bullet = null;//子弹
    Vector<Bullet> clip = new Vector<>();//弹夹
    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getClipSize() {
        return clipSize;
    }

    public void setClipSize(int clipSize) {
        this.clipSize = clipSize;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
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


    public void moveUp() {
        if (y - speed >= 0) {
            setDirect(0);
            y -= speed;
        }
    }

    public void moveRight() {
        if (x + 60 + speed <= 1000) {
            setDirect(1);
            x += speed;
        }
    }

    public void moveDown() {
        if (y + 60 + speed <= 750) {
            setDirect(2);
            y += speed;
        }
    }

    public void moveLeft() {
        if (x - speed >= 0) {
            setDirect(3);
            x -= speed;
        }
    }

    public void shot() {
        if (clip.size() < clipSize) {
            switch (direct) {//0->上 1->右 2->下 3->左
                case 0:
                    bullet = new Bullet(x + 20, y, direct);
                    clip.add(bullet);
                    break;
                case 1:
                    bullet = new Bullet(x + 60, y + 20, direct);
                    clip.add(bullet);
                    break;
                case 2:
                    bullet = new Bullet(x + 20, y + 60, direct);
                    clip.add(bullet);
                    break;
                case 3:
                    bullet = new Bullet(x, y + 20, direct);
                    clip.add(bullet);
                    break;
            }
            new Thread(bullet).start();
        }
    }
}
