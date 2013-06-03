package com.ajnas.Setnums;

import com.ajnas.framework.Game;
import com.ajnas.framework.Graphics;
import com.ajnas.framework.Screen;
import com.ajnas.framework.Graphics.ImageFormat;

public class SplashLoadingScreen extends Screen {
	public SplashLoadingScreen(Game game) {
		super(game);
	}

	
	
    public static boolean visible=true;
    @Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Assets.loading = g.newImage("loading.jpg", ImageFormat.RGB565);

		game.setScreen(new LoadingScreen(game));

	}

	@Override
	public void paint(float deltaTime) {

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