package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class tankGameController {
    private HelloApplication main;
    public void setMain(HelloApplication main) {
        this.main = main;
    }
    @FXML
    private Button start;

    @FXML
    void action(ActionEvent event) {
        ZlhTankGame03 game = new ZlhTankGame03();
    }

}