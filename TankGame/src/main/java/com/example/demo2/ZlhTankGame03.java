package com.example.demo2;

import javax.swing.*;

public class ZlhTankGame03 extends JFrame {
    MyPanel myPanel = null;//定义画板

    public ZlhTankGame03() {
        myPanel = new MyPanel();//初始化画板
        new Thread(myPanel).start();
        this.add(myPanel);//将画板放入窗口
        this.addKeyListener(myPanel);//加入键盘事件监听者
        this.setSize(1000,780);//设置窗口大小
        this.setVisible(true);//设置窗口可视化
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置点击窗口退出按钮自动结束程序

    }

}
