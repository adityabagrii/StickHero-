package com.example.stickhero;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class Timer extends Thread {
    private Label label;
    private int seconds;

    public Timer(Label label) {
        this.label = label;
        this.seconds = 0;
    }

    @Override
    public void run() {
        try {
            while (true) {
                sleep(1000);

                seconds++;
                Platform.runLater(() -> label.setText(formatTime()));
            }
        } catch (InterruptedException e) {
            System.out.println("Timer thread interrupted.");
        }
    }

    private String formatTime() {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }
}
