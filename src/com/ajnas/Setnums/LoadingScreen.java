package com.ajnas.Setnums;

import com.ajnas.framework.Game;
import com.ajnas.framework.Graphics;
import com.ajnas.framework.Graphics.ImageFormat;
import com.ajnas.framework.Screen;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        
        super(game);
    }
    
    public static boolean visible=true;

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
        Assets.one = g.newImage("1.png", ImageFormat.ARGB4444);
        Assets.two = g.newImage("2.png", ImageFormat.ARGB4444);
        Assets.three = g.newImage("3.png", ImageFormat.ARGB4444);
        Assets.four  = g.newImage("4.png", ImageFormat.ARGB4444);
        Assets.five = g.newImage("5.png", ImageFormat.ARGB4444);
        Assets.six = g.newImage("6.png", ImageFormat.ARGB4444);

        
        Assets.seven = g.newImage("7.png", ImageFormat.ARGB4444);
        Assets.eight = g.newImage("8.png", ImageFormat.ARGB4444);
        Assets.nine  = g.newImage("9.png", ImageFormat.ARGB4444);
        Assets.ten  = g.newImage("10.png", ImageFormat.ARGB4444);
        Assets.eleven = g.newImage("11.png", ImageFormat.ARGB4444);


        
        Assets.twelve= g.newImage("12.png", ImageFormat.ARGB4444);
        Assets.thirteen = g.newImage("13.png", ImageFormat.ARGB4444);
        Assets.fourteen= g.newImage("14.png", ImageFormat.ARGB4444);
        Assets.fifteen = g.newImage("15.png", ImageFormat.ARGB4444);
        
        
        Assets.button = g.newImage("button.jpg", ImageFormat.RGB565);

        //This is how you would load a sound if you had one.
        //Assets.click = game.getAudio().createSound("explode.ogg");

        
        game.setScreen(new MainMenuScreen(game));

    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.loading, 0, 0);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {

    }
}
