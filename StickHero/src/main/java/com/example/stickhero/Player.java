package com.example.stickhero;

public class Player {
    private static Player player;
    private Player(){}
    public static Player getInstance(){
        if(player==null){
            player=new Player();
        }
        return player;
    }
    public Stick generateStick(int length){
        return new Stick(length);
    }
}
