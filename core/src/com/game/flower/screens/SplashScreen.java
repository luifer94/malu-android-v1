package com.game.flower.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.game.flower.Core;
import com.game.flower.managers.ProductoFactory;
import com.game.flower.managers.ResourceManager;

/**
 * Created by LuiferPc on 24/5/2017.
 */

public class SplashScreen extends BaseScreen {

    private Skin skin;

    private Label loading;

    public SplashScreen(Core game,int id) {
        super(game,id);
        stage=new Stage(new FitViewport(500,500),game.getScreenSprite());
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        loading=new Label("Loading...",skin);
        loading.setPosition(190,250);
        stage.addActor(loading);
       ProductoFactory.Instance().getProducts();
    }

    @Override
    public void render(float delta) {
        if(ResourceManager.Instance().update() && ProductoFactory.Instance().productos!=null)
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
