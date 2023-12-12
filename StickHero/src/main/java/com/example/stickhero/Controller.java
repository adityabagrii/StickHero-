package com.example.stickhero;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class Controller {


    @FXML
    public void startGame(ActionEvent e) {
        try{
            Scene inGame;
            Stage inGameStage;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inGame.fxml"));
            Parent root = loader.load();
            inGameController controller = loader.getController();
            inGameStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            inGame=new Scene(root);
            inGameStage.setScene(inGame);
            inGameStage.show();
//            controller.letsPlay();
//            letsPlay();
        }
        catch (IOException z){
            System.out.println(z.toString());
        }
    }
    @FXML
    private void Load(){
        inGameController controller=new inGameController();
        controller.Load();
    }
    public void quit(ActionEvent e){
        Platform.exit();
    }
}