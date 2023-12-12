package com.example.stickhero;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.*;

public class ScoreManager implements Serializable {
    private static ScoreManager scoreManager;
    private int currentScore;
    private int highScore;
    private int cherriesCollected;
    private boolean continueGame=true;

    private ScoreManager(){}

    public int getCurrentScore() {
        System.out.println(currentScore);
        return currentScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public int getCherriesCollected() {
        return cherriesCollected;
    }

    public boolean isContinueGame() {
        return continueGame;
    }

    public static ScoreManager getInstance(){
        if(scoreManager==null){
            scoreManager=new ScoreManager();
        }
        return scoreManager;
    }
    public boolean stickPass(Rectangle block, Rectangle stick, Double distance){
        return stick.getBoundsInParent().intersects(block.getBoundsInParent()) && !(stick.getHeight()>(distance));
    }
    public boolean cherryCollected(ImageView hero, ImageView cherries){
        if(hero.getBoundsInParent().intersects(cherries.getBoundsInParent())){
            cherriesCollected++;
            return true;
        }
        return false;
    }
    public void gameOver(){
        continueGame = false;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("retryScreen.fxml"));
            Parent root = loader.load();
            retryScreen controller = loader.getController();

            Stage retryStage = new Stage();
            retryStage.initModality(Modality.APPLICATION_MODAL); // This makes the new window a modal window
            retryStage.setTitle("Game Over");
            retryStage.setScene(new Scene(root));
            retryStage.show();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
    public void updateScore(){
        currentScore++;
        if(currentScore>highScore){
            highScore=currentScore;
        }
    }
    public void setCurrentScore(){
        this.currentScore=0;
    }

    public void Savegame(String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(this);
            System.out.println("Game saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }

    public static void Loadgame(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            ScoreManager loadedScoreManager = (ScoreManager) inputStream.readObject();
            scoreManager=loadedScoreManager;
            scoreManager.setCurrentScore();
            System.out.println("Game loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading game: " + e.getMessage());
        }
    }
}
