package com.ajnas.Setnums;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.ajnas.framework.Game;
import com.ajnas.framework.Graphics;
import com.ajnas.framework.Image;
import com.ajnas.framework.Input.TouchEvent;
import com.ajnas.framework.Screen;
import com.ajnas.framework.implementation.ScoreModel;

public class GameScreen extends Screen {
	enum GameState {
		Ready, Running, Paused, GameOver, Completed;
	}

	GameState state = GameState.Ready;

	// Variable Setup

	private Image one, two, three, four, five, six, seven, eight, nine, ten,
			eleven, twelve, thirteen, fourteen, fifteen;
	Number[] numarray;
	int[] table;
	private int blankx, blanky;
	private MyCountDownTimer counter;
	Paint paint, paint2, paintscore;
	private int moves;
	private ScoreModel scoredb;

	public GameScreen(Game game) {
		super(game);

		// Initialize game objects here
		table = new int[17];

		List<Integer> randomArray = new ArrayList<Integer>();
		for (int i = 0; i < 16; i++) {
			randomArray.add(i);
		}

		Collections.shuffle(randomArray);

		numarray = new Number[16];
		for (int i = 0; i <= 15; i++) {

			int t = randomArray.get(i);
			table[i] = t;
			if (t == 0) {
				blankx = numToX(i);
				blanky = numToY(i);
			} else {
				numarray[t] = new Number(t, numToX(i), numToY(i),
						numToX(t - 1), numToY(t - 1));
			}

		}

		one = Assets.one;
		two = Assets.two;
		three = Assets.three;

		four = Assets.four;
		five = Assets.five;
		six = Assets.six;
		seven = Assets.seven;
		eight = Assets.eight;
		nine = Assets.nine;
		ten = Assets.ten;
		eleven = Assets.eleven;

		twelve = Assets.twelve;
		thirteen = Assets.thirteen;
		fourteen = Assets.fourteen;
		fifteen = Assets.fifteen;

		// Defining a paint object
		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paintscore = new Paint();
		paintscore.setColor(Color.GREEN);
		paintscore.setTextSize(60);
		paintscore.setAntiAlias(true);

		paint2 = new Paint();
		paint2.setTextSize(120);
		paint2.setTextAlign(Paint.Align.CENTER);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.WHITE);

		moves = 0;

	}

	@Override
	public void update(float deltaTime) {
		List touchEvents = game.getInput().getTouchEvents();

		// We have four separate update methods in this example.
		// Depending on the state of the game, we call different update methods.
		// Refer to Unit 3's code. We did a similar thing without separating the
		// update methods.

		if (state == GameState.Ready)
			updateReady(touchEvents);
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.Paused)
			updatePaused(touchEvents);
		if (state == GameState.Completed)
			updateCompleted(touchEvents);
	}

	private void updateReady(List touchEvents) {

		// This example starts with a "Ready" screen.
		// When the user touches the screen, the game begins.
		// state now becomes GameState.Running.
		// Now the updateRunning() method will be called!

		if (touchEvents.size() > 0)
			state = GameState.Running;

	}

	private void updateRunning(List touchEvents, float deltaTime) {

		// This is identical to the update() method from our Unit 2/3 game.

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = (TouchEvent) touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {

				if (inBounds(event, 0, 0, 480, 480)) {
					updatenum(event);

					if (correct()) {

						state = GameState.Completed;

					}

				}
				if (inBounds(event, 0, 0, 30, 30)) {
					state = GameState.Paused;

				}

			}
		}
	}

	private void updatenum(TouchEvent event) {

		int x = ((event.x) / 120);
		int y = ((event.y)) / 120;
		if ((abs(x - blankx) + abs(y - blanky)) == 1) {

			moves++;
			int n = y * 4 + x;
			int temp1 = table[n];
			table[n] = 0;
			int temp = (blanky * 4 + blankx);
			table[temp] = temp1;

			temp = blankx;

			blankx = numarray[temp1].getX();
			numarray[temp1].setX(temp);
			temp = blanky;
			blanky = numarray[temp1].getY();
			numarray[temp1].setY(temp);

		}
	}

	private boolean correct() {
		for (int i = 1; i < 16; i++)
			if ((numarray[i].getX() != numarray[i].getExactx())
					|| (numarray[i].getY() != numarray[i].getExacty()))
				return false;
		return true;
	}

	private int abs(int i) {
		// TODO Auto-generated method stub
		if (i < 0)
			return (-1 * i);
		else
			return i;
	}

	private int numToX(int num) {
		return (num % 4);
	}

	private int numToY(int num) {
		return (num / 4);
	}

	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	private void updatePaused(List touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = (TouchEvent) touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 0, 0, 800, 240)) {

					if (!inBounds(event, 0, 0, 35, 35)) {
						resume();
					}
				}

				if (inBounds(event, 0, 240, 800, 240)) {
					nullify();
					goToMenu();
				}
			}
		}
	}

	private void updateCompleted(List touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = (TouchEvent) touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 0, 0, 20, 20)) {
					nullify();
					game.setScreen(new GameScreen(game));

				} else if (inBounds(event, 780, 0, 20, 20)) {
					nullify();
					game.setScreen(new GameScreen(game));
				}
			}
		}

	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawRect(0, 0, 800, 480, Color.RED);
		g.drawImage(one, (numarray[1].getX()) * 120,
				(numarray[1].getY() * 120) + 0);
		g.drawImage(two, (numarray[2].getX()) * 120,
				(numarray[2].getY() * 120) + 0);
		g.drawImage(three, (numarray[3].getX()) * 120,
				(numarray[3].getY() * 120) + 0);
		g.drawImage(four, (numarray[4].getX()) * 120,
				(numarray[4].getY() * 120) + 0);
		g.drawImage(five, (numarray[5].getX()) * 120,
				(numarray[5].getY() * 120) + 0);
		g.drawImage(six, (numarray[6].getX()) * 120,
				(numarray[6].getY() * 120) + 0);
		g.drawImage(seven, (numarray[7].getX()) * 120,
				(numarray[7].getY() * 120) + 0);
		g.drawImage(eight, (numarray[8].getX()) * 120,
				(numarray[8].getY() * 120) + 0);
		g.drawImage(nine, (numarray[9].getX()) * 120,
				(numarray[9].getY() * 120) + 0);
		g.drawImage(ten, (numarray[10].getX()) * 120,
				(numarray[10].getY() * 120) + 0);
		g.drawImage(eleven, (numarray[11].getX()) * 120,
				(numarray[11].getY() * 120) + 0);
		g.drawImage(twelve, (numarray[12].getX()) * 120,
				(numarray[12].getY() * 120) + 0);
		g.drawImage(thirteen, (numarray[13].getX()) * 120,
				(numarray[13].getY() * 120) + 0);
		g.drawImage(fourteen, (numarray[14].getX()) * 120,
				(numarray[14].getY() * 120) + 0);
		g.drawImage(fifteen, (numarray[15].getX()) * 120,
				(numarray[15].getY() * 120) + 0);
		// g.drawRect(blankx * 120, blanky * 120 + 0, 120, 120, Color.BLACK);
		g.drawString(Integer.toString(moves), 600, 80, paintscore);

		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.Running)
			drawRunningUI();
		if (state == GameState.Paused)
			drawPausedUI();
		if (state == GameState.Completed)
			drawCompletedUI();

	}

	private void nullify() {

		// Set all variables to null. You will be recreating them in the
		// constructor.
		paint = null;
		one = null;
		two = null;
		three = null;
		four = null;
		five = null;
		six = null;
		seven = null;
		eight = null;
		nine = null;
		ten = null;
		eleven = null;
		twelve = null;
		thirteen = null;
		fourteen = null;
		fifteen = null;
		table = null;
		numarray = null;

		// Call garbage collector to clean up memory.
		System.gc();

	}

	private void drawReadyUI() {
		Graphics g = game.getGraphics();

		g.drawARGB(155, 0, 0, 0);
		g.drawString("Tap to Start.", 400, 240, paint);

	}

	private void drawRunningUI() {
		Graphics g = game.getGraphics();

		g.drawImage(Assets.button, 0, 0, 0, 195, 35, 35);

	}

	private void drawPausedUI() {
		Graphics g = game.getGraphics();
		// Darken the entire screen so you can display the Paused screen.
		g.drawARGB(155, 0, 0, 0);
		g.drawString("Resume", 400, 165, paint2);
		g.drawString("Menu", 400, 360, paint2);

	}

	private void drawCompletedUI() {
		Graphics g = game.getGraphics();
		g.drawRect(0, 0, 800, 480, Color.BLACK);
		g.drawString("Total moves : " + Integer.toString(moves) + " !", 400,
				240, paint2);
		g.drawString("<---menu              once more --->", 400, 290, paint);

	}

	@Override
	public void pause() {
		if (state == GameState.Running)
			state = GameState.Paused;

	}

	@Override
	public void resume() {
		if (state == GameState.Paused)
			state = GameState.Running;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
		pause();
	}

	private void goToMenu() {
		// TODO Auto-generated method stub
		game.setScreen(new MainMenuScreen(game));

	}

}