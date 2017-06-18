package com.game.flower.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.game.flower.Core;
import com.game.flower.managers.ResourceManager;

/**
 * Created by LuiferPc on 24/5/2017.
 */

public class SplashScreen extends BaseScreen {

    private Skin skin;

    private Label loading;

    public SplashScreen(Core game,int id) {
        super(game,id);
        skin=new Skin(Gdx.files.internal("skin/uiskin.json"));
        loading=new Label("Loading...",skin);
        loading.setSize(256,128);
        loading.setPosition(Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()/2)-(loading.getWidth()/2),Gdx.graphics.getHeight()-(Gdx.graphics.getHeight()/2)-(loading.getHeight()/2));
        stage.addActor(loading);
    }

    @Override
    public void render(float delta) {
        if(ResourceManager.Instance().update())
        {
            dispose();
            game.setScreen(BaseScreen.MAIN_MENU_SCREEN);
            game.currentScreen.show();
        }else
        {
            loading.setText("Loading... "+(ResourceManager.Instance().getProgress()*100)+ "%");
            stage.act();
            stage.draw();
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        stage=null;
        skin=null;
    }
}
