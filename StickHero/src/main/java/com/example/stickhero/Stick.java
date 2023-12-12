package com.example.stickhero;

import javafx.scene.shape.Rectangle;

public class Stick{
    private int length;
    private Rectangle r = new Rectangle(5,0);

    public void setLength(int length) {
        this.length = length;
        r.setHeight(length);
//        r.setY(r.getY()-length);

//        r.setX(55);
    }
    public void setX(double x){
        r.setX(x);
    }
    public void setY(double y){
        r.setY(y);
    }

    public int getLength() {
        return length;
    }

    public Stick(int length) {
        this.length=length;
        setY(610);
    }
    public Rectangle getStick(){
//        System.out.println(length+" stick returned");
        return r;
    }
    public String toString(){
        return "Stick of length: "+length;
    }
}
