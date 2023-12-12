package com.example.stickhero;

import javafx.scene.shape.Rectangle;
import java.util.Random;

public class Blocks {
    private Rectangle block;
    private static final int[] AVAILABLE_WIDTHS = {100,150,90,75,106,125,96};
    public Rectangle randomBlockGenerator(){
        Random random=new Random();
        double width= AVAILABLE_WIDTHS[random.nextInt(AVAILABLE_WIDTHS.length)];
        double height=600;
        block=new Rectangle(width,height);
        return block;
    }
}
