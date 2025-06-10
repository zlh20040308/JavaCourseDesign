package com.example.demo2;

public class Enemy extends Tank implements Runnable {
    public Enemy(int x, int y) {
        super(x, y);
        setType(1);//初始化敌方坦克类型 1->敌方
        setSpeed(3);//初始化敌方坦克类型速度为3
        setDirect(2);//初始化敌方坦克方向 2->下

    }
    @Override
    public void run() {
        while (true) {
            this.shot();
            switch (getDirect()) {//0->上 1->右 2->下 3->左
                case 0:
                    for (int i = 0; i < 30; i++) {
                        moveUp();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        moveRight();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        moveDown();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        moveLeft();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
            if (!isLive()) {//如果死了就退出线程
                break;
            } else {
                setDirect((int) (Math.random() * 4));//随机生成下一个要前进的方向0->上 1->右 2->下 3->左
            }
        }
    }
}
