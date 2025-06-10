package com.example.demo2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
public class HelloApplication extends Application {
    private Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        showTankGame();
    }
    public void showTankGame() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    HelloApplication.class.getResource("tankgame.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            tankGameController controller = loader.getController();// 获取控制器引用
            controller.setMain(this);  // 将主类对象引用传入控制器
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("坦克大战");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch();
    }
}