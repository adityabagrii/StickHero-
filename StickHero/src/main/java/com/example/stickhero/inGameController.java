package com.example.stickhero;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;


public class inGameController implements Initializable {
    @FXML
    private ImageView hero;
    @FXML
    private ImageView imageBg;
    @FXML
    private AnchorPane Pane;
    @FXML
    private ImageView cherries;
    @FXML
    private Button button;
    @FXML
    private Label score;
    @FXML
    private Label highScore;
    @FXML
    private Label cherryno;
    @FXML
    private Label time;

    private int length=0;
    private Timeline t;
    private Timeline gameLoop;
    private boolean isKeyReleased=true;
    private Rectangle stick_r;
    private RotateTransition rotateTransition;
    private boolean stickFell=false;
    private static Stick stick;
    private Timeline stickfalling;
    private Player stickHero;
    private ScoreManager scoreManager;
    private Timeline stickReset;
    private Rectangle Tower;
//    private Group group=new Group();
    private ArrayList<Rectangle> blocksArr=new ArrayList<>();
    private ArrayList<Double> distanceArr=new ArrayList<>();
    public void recordLength(){
        length=0;
        isKeyReleased = false;
        t = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            length += 10;
            stick_r.setY(stick_r.getY() - 10);

        }));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }

    public void mouseReleased(){
        isKeyReleased=true;
//        System.out.println("11111");
        t.stop();
        if (length > 0 && !stickFell) {
            stickFalls();
        }
    }
    private void letsPlay() {
        System.out.println("in 1");
        if (stick_r == null) {
            System.out.println("in 2");
            stick.setX(hero.getX()+37);
            stick.setY(609);
            stick_r = stick.getStick();
            Pane.getChildren().add(stick_r);
        }

        gameLoop = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            if (!scoreManager.isContinueGame()) {
                stopGame();
            } else {
                if (!isKeyReleased) {
                    stick.setLength(length);
                }
            }

        }));

        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
        length=0;
    }
    @FXML
    private void SaveGame(){
        scoreManager.Savegame("save.ser");
    }
    @FXML
    public void Load(){
        scoreManager.Loadgame("save.ser");
    }
    private void stickFalls(){
        if (stick_r != null) {
            stickFell = true;

            double pivotX = stick_r.getX() ;
            double pivotY = stick_r.getY() + stick_r.getHeight();

            Rotate rotate = new Rotate(0, pivotX, pivotY);
            stick_r.getTransforms().add(rotate);

            Duration duration = Duration.seconds(0.5);
            int angleToRotate = 90;
            AtomicInteger totalRotation = new AtomicInteger();

            stickfalling = new Timeline(
                    new KeyFrame(duration, e -> {
                        if (totalRotation.get() < 90) {
                            rotate.setAngle(rotate.getAngle() + angleToRotate);
                            totalRotation.addAndGet(angleToRotate);
                        } else {
                            stickfalling.stop();
                        }
                    })
            );

            PauseTransition pause = new PauseTransition(Duration.seconds(0.1));
            pause.setOnFinished(event -> {
                stickfalling.setOnFinished(finishedEvent -> {
//                    Pane.getChildren().remove(blocksArr.get(1));
                    if(scoreManager.stickPass(blocksArr.get(1),stick_r,distanceArr.get(0))){
                        scoreManager.updateScore();

                        score.setText("Score: "+scoreManager.getCurrentScore());
                        highScore.setText("HighScore: "+scoreManager.getHighScore());
                        try {
                            move();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else{
                        TranslateTransition move = new TranslateTransition(Duration.millis(1000), hero);
                        move.setByX(stick_r.getHeight());
                        move.setInterpolator(Interpolator.EASE_BOTH);
                        move.setCycleCount(1);

                        TranslateTransition fall = new TranslateTransition(Duration.millis(1000), hero);
                        fall.setByY(10000);
                        fall.setInterpolator(Interpolator.EASE_BOTH);
                        fall.setCycleCount(1);

                        SequentialTransition sequentialTransition=new SequentialTransition();
                        sequentialTransition.getChildren().addAll(move,fall);
                        sequentialTransition.setCycleCount(1);
                        sequentialTransition.setOnFinished(e->{
                            scoreManager.gameOver();
                        });
                        sequentialTransition.play();
//                        scoreManager.gameOver();
                    }
                });
                stickfalling.play();
            });

            pause.play();
        }
    }
    public void stopGame(){
        length=0;
        gameLoop.stop();
    }

private void move() throws InterruptedException {
    if (blocksArr.size() >= 2) {

        ParallelTransition parallelTransition = new ParallelTransition();

        for (Rectangle block : blocksArr) {
            TranslateTransition move = new TranslateTransition(Duration.millis(1000), block);
            move.setByX(-distanceArr.get(0));
            move.setInterpolator(Interpolator.EASE_BOTH);
            move.setCycleCount(1);

            parallelTransition.getChildren().add(move);
        }
        AnimationTimer a=new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(scoreManager.cherryCollected(hero,cherries)){
                    cherries.setDisable(true);
                    scoreManager.updateScore();
                    cherryno.setText("Cherries"+scoreManager.getCherriesCollected());
                }
            }
        };
        distanceArr.remove(0);
        blocksArr.remove(0);
//        System.out.println(distanceArr);
        TranslateTransition moveStick = new TranslateTransition(Duration.millis(1500), stick_r);
        moveStick.setByX(-distanceArr.get(0));
        moveStick.setInterpolator(Interpolator.EASE_BOTH);
        moveStick.setCycleCount(1);
        parallelTransition.getChildren().add(moveStick);

        TranslateTransition moveCherries = new TranslateTransition(Duration.millis(1500), cherries);
        moveCherries.setByX(-distanceArr.get(0));
        moveCherries.setInterpolator(Interpolator.EASE_BOTH);
        moveCherries.setCycleCount(1);
        parallelTransition.getChildren().add(moveCherries);

        TranslateTransition moveImage = new TranslateTransition(Duration.millis(1500), imageBg);
        moveImage.setByX(-distanceArr.get(0));
        moveImage.setInterpolator(Interpolator.EASE_BOTH);
        moveImage.setCycleCount(1);

        parallelTransition.getChildren().add(moveImage);

        parallelTransition.setOnFinished(event -> {
//            System.out.println("hogYa");
            Pane.getChildren().remove(stick_r);
            stickFell=false;
            stick =new Stick(50);
            stick_r=stick.getStick();
            stick_r.setY(609);
            stick_r.setX(hero.getX()+37);
            Pane.getChildren().addAll(stick_r);
        });

        // Play the parallel transition
        parallelTransition.play();
    }
}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timer timer = new Timer(time);
        timer.start();
        stickHero = Player.getInstance();
        scoreManager = ScoreManager.getInstance();
        stick = new Stick(0);
//        stick_r=stick.getStick();
        Blocks blocks = new Blocks();
        double distance = 10;
        final double[] spaces={123, 82, 141, 95, 104, 130, 74, 147, 112, 78};
        Random random=new Random();

        for (int i = 0; i < 100; i++) {

            Rectangle block = blocks.randomBlockGenerator();
            block.setY(600);
            if(i>4){
                block.setFill(Color.WHITE);
            }
            blocksArr.add(block);

            block.setX(distance);

            Pane.getChildren().add(block);
            double space= (spaces[random.nextInt(spaces.length)]);
            distance += block.getWidth() +space;
            double gap=block.getWidth()+space;
            distanceArr.add(gap);
        }
        Tower=blocksArr.get(0);
        hero.setX(Tower.getX()+Tower.getWidth()/2);
        cherries.setX(hero.getX()+random.nextDouble(distanceArr.get(0)-(blocksArr.get(0).getX()+blocksArr.get(0).getWidth())));
        letsPlay();
    }
}