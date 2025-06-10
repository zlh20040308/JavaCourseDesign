package com.example.demo2;



import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable {
    Hero hero = null;//定义己方角色

    HelloApplication main;
    Vector<Enemy> enemies = new Vector<>();//定义敌军容器（内有多辆敌方坦克）
    int enemySize = 3;//定义敌方坦克数量

    public MyPanel() {

        main = new HelloApplication();
        //初始化己方坦克
        hero = Hero.getInstance();
        //初始化敌方坦克
        for (int i = 0; i < enemySize; i++) {
            Enemy enemy = new Enemy(200 * (i + 1), 0);
            new Thread(enemy).start();
            enemies.add(enemy);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //画背景
        g.fillRect(0, 0, 1000, 750);//背景颜色默认为灰
        //画己方坦克和己方子弹
        if (hero != null && hero.isLive()) {
            drawTank(hero.getX(), hero.getY(), hero.getType(), hero.getDirect(), g);
            for (int i = 0; i < hero.clip.size(); i++) {//遍历己方坦克弹夹中所有的子弹
                Bullet bullet = hero.clip.get(i);
                if (bullet != null && bullet.isLive()) {
                    drawBullet(bullet.getX(), bullet.getY(), hero.getType(), g);
                }
            }
        }
        //画敌方坦克和子弹
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            if (enemy != null && enemy.isLive()) {
                drawTank(enemy.getX(), enemy.getY(), enemy.getType(), enemy.getDirect(), g);
                for (int j = 0; j < enemy.clip.size(); j++) {
                    Bullet bullet = enemy.clip.get(j);
                    if (bullet != null && bullet.isLive()) {
                        drawBullet(bullet.getX(), bullet.getY(), enemy.getType(), g);
                    }
                }
            }
        }


    }
    //画子弹
    public void drawBullet(int x, int y, int type, Graphics g) {
        //根据坦克type决定子弹颜色
        switch (type) {//0->己方 1->敌方
            case 0: //己方子弹
                g.setColor(Color.CYAN);//青色
                break;
            case 1: //敌方子弹
                g.setColor(Color.red);//红色
                break;
        }
        g.fill3DRect(x, y, 2, 2, false);
    }

    //画坦克
    public void drawTank(int x, int y, int type, int direct, Graphics g) {
        //根据type决定坦克颜色
        switch (type) {//0->己方 1->敌方
            case 0: //己方坦克
                g.setColor(Color.CYAN);//青色
                break;
            case 1: //敌方坦克
                g.setColor(Color.red);//红色
                break;
        }
        //根据direct画出不同方向的坦克
        switch (direct) {//0->上 1->右 2->下 3->左
            case 0: //方向朝上
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边的轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克右边的轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克车身
                g.fillOval(x + 10, y + 20, 20, 20);//画出坦克盖子
                g.drawLine(x + 20, y + 30, x + 20, y);//画出坦克的炮
                break;
            case 1: //方向朝右
                g.fill3DRect(x, y, 60, 10, false);//画出坦克左边的轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//画出坦克右边的轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//画出坦克车身
                g.fillOval(x + 20, y + 10, 20, 20);//画出坦克盖子
                g.drawLine(x + 30, y + 20, x + 60, y + 20);//画出坦克的炮
                break;
            case 2: //方向朝下
                g.fill3DRect(x, y, 10, 60, false);//画出坦克左边的轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//画出坦克右边的轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//画出坦克车身
                g.fillOval(x + 10, y + 20, 20, 20);//画出坦克盖子
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//画出坦克的炮
                break;
            case 3: //方向朝左
                g.fill3DRect(x, y, 60, 10, false);//画出坦克左边的轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//画出坦克右边的轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//画出坦克车身
                g.fillOval(x + 20, y + 10, 20, 20);//画出坦克盖子
                g.drawLine(x + 30, y + 20, x, y + 20);//画出坦克的炮
                break;
        }
    }



    //击中坦克
    public void hitTank(Bullet bullet, Tank tank) {//bullet与tank都不能为空
        if (!tank.isLive() || !bullet.isLive()) {//如果bullet与tank有一个已经死亡，就没必要去判断了
            return;
        }

        //通过比较子弹与不同方向的坦克之间的坐标关系，判断是否击中
        switch (tank.getDirect()) {//0->上 1->右 2->下 3->左
            case 0:
            case 2:
                if (bullet.getX() >= tank.getX()
                        && bullet.getX() <= tank.getX() + 40
                        && bullet.getY() >= tank.getY()
                        && bullet.getY() <= tank.getY() + 60) {
                    bullet.setLive(false);
                    tank.setLive(false);
                }
                break;
            case 1:
            case 3:
                if (bullet.getX() >= tank.getX()
                        && bullet.getX() <= tank.getX() + 60
                        && bullet.getY() >= tank.getY()
                        && bullet.getY() <= tank.getY() + 40) {
                    bullet.setLive(false);
                    tank.setLive(false);
                }
                break;
        }
    }

    //击中敌方坦克
    public void hitEnemy() {
        if (hero != null && hero.isLive()){//只有当己方坦克还存在时，才有必要判断
            for (int i = 0; i < enemies.size(); i++) {//遍历敌军
                Enemy enemy = enemies.get(i);
                if (enemy != null) {
                    for (int j = 0; j < hero.clip.size(); j++) {//遍历战场上所有的己方子弹
                        Bullet bullet = hero.clip.get(j);
                        if (bullet != null) {
                            hitTank(bullet, enemy);//逐一判断是否击中
                        }
                    }
                }
            }
        }
    }
    //击中己方坦克
    public void hitHero() {
        if (hero != null && hero.isLive()) {//只有当己方坦克还存在时，才有必要判断
            for (int i = 0; i < enemies.size(); i++) {//遍历敌军
                Enemy enemy = enemies.get(i);
                if (enemy != null) {
                    if (enemy.isLive()) {
                        for (int j = 0; j < enemy.clip.size(); j++) {//遍历战场上所有的敌方子弹
                            Bullet bullet = enemy.clip.get(j);
                            if (bullet != null && hero != null) {
                                hitTank(bullet, hero);//逐一判断是否击中
                            }
                        }
                    }
                }
            }
        }
    }
    //判断游戏是否结束
    public boolean isOver(){
        if (hero == null || enemies.size() == 0){
            return true;
        }else {
            return false;
        }
    }


    //清理战场上所有已经死亡的敌方坦克、己方坦克和所有已经死亡的子弹
    public void remover() {
        //清理已经死亡的敌人坦克和敌方子弹
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            if (enemy != null) {
                if (!enemy.isLive()) {
                    enemies.remove(enemy);
                    //enemySize--;
                } else {
                    for (int j = 0; j < enemy.clip.size(); j++) {
                        Bullet bullet = enemy.clip.get(j);
                        if (bullet != null && !bullet.isLive()) {
                            enemy.clip.remove(bullet);
                        }
                    }
                }
            }
        }
        //清理已经死亡的己方坦克和己方子弹
        if (hero != null){
            if (!hero.isLive()){
                hero = null;
            }else {
                for (int i = 0; i < hero.clip.size(); i++) {
                    Bullet bullet = hero.clip.get(i);
                    if (bullet != null && !bullet.isLive()) {
                        hero.clip.remove(bullet);
                    }
                }
            }
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (hero!=null && hero.isLive()) {//只有当己方坦克还存在的时候才进行响应
            //W->上 D->右 S->下 A->左
            if (e.getKeyCode() == KeyEvent.VK_W) {
                hero.moveUp();
            } else if (e.getKeyCode() == KeyEvent.VK_D) {
                hero.moveRight();
            } else if (e.getKeyCode() == KeyEvent.VK_S) {
                hero.moveDown();
            } else if (e.getKeyCode() == KeyEvent.VK_A) {
                hero.moveLeft();
            }
            //J->发射子弹
            if (e.getKeyCode() == KeyEvent.VK_J) {
                hero.shot();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    //持续重绘画板
    @Override
    public void run() {
        while (true) {
            hitHero();
            hitEnemy();
            remover();
            if (isOver()){
                break;
            }
            this.repaint();
        }
    }
}
