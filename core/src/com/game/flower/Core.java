package com.game.flower;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.flower.screens.BaseScreen;
import com.game.flower.screens.ArregloScreen;
import com.game.flower.screens.MainMenuScreen;
import com.game.flower.screens.MakerOrderScreen;
import com.game.flower.screens.MakerOrderScreen;
import com.game.flower.screens.SplashScreen;

import java.util.HashMap;
import java.util.Map;

public class Core extends ApplicationAdapter {
	private final Map<Integer, Screen> screens = new HashMap<>();
	public Screen currentScreen;
	private SpriteBatch screenSprite;
 	private Plataforma plataforma;

	public Core(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	public SpriteBatch getScreenSprite() {
		return screenSprite;
	}

	public Plataforma getPlataforma() {
		return plataforma;
	}

	@Override
	public void create() {
		screenSprite=new SpriteBatch();
		screens.put(BaseScreen.SPLASH_SCREEN, new SplashScreen(this,BaseScreen.SPLASH_SCREEN));
		screens.put(BaseScreen.MAIN_MENU_SCREEN, new MainMenuScreen(this,BaseScreen.MAIN_MENU_SCREEN));
		screens.put(BaseScreen.MAKE_A_ORDER_SCREEN, new MakerOrderScreen(this,BaseScreen.MAKE_A_ORDER_SCREEN));
		setScreen(BaseScreen.SPLASH_SCREEN);
	}

	@Override
	public void resume() {
		super.resume();
		currentScreen.resume();
	}

	@Override
	public void pause() {
		super.pause();
		currentScreen.pause();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		currentScreen.render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void resize(int width, int height) {
		currentScreen.resize(width, height);
	}

	public void setScreen(int id) {
		this.currentScreen = screens.get(id);
	}

	@Override
	public void dispose() {
		currentScreen.dispose();
	}
}