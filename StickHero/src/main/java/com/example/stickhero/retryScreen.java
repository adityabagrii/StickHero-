package com.example.stickhero;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class retryScreen implements Initializable {
//    private inGameController gameController;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void retry(ActionEvent event){
        try{
//            Pane.getChildren().remove(stick_r);
            Scene inGame;
            Stage inGameStage;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inGame.fxml"));
            Parent root = loader.load();
            inGameController controller = loader.getController();
            inGameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            inGame=new Scene(root);
            inGameStage.setScene(inGame);
            inGameStage.show();
//            controller.letsPlay();
        }
        catch (IOException z){
            System.out.println(z.toString());
        }
    }
    @FXML
    public void quit(ActionEvent event){
        Platform.exit();
    }
}
