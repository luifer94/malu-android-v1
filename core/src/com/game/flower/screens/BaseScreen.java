package com.game.flower.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.game.flower.Core;

/**
 * Created by LuiferPc on 24/5/2017.
 */

public class BaseScreen implements Screen {

    public static final int SPLASH_SCREEN=0;
    public static final int MAIN_MENU_SCREEN=1;
    public static final int MAKE_A_ORDER_SCREEN=2;

    public Core game;
    private int id;
    Stage stage;
    public int getId() {
        return id;
    }


    public BaseScreen(Core game,int id)
    {
        this.game=game;
        this.id=id;
        stage=new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()),game.getScreenSprite());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
