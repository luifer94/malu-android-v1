package com.game.flower.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.game.flower.Actor.ButterflyActor;
import com.game.flower.Core;
import com.game.flower.managers.ResourceManager;

/**
 * Created by LuiferPc on 24/5/2017.
 */

public class MainMenuScreen extends BaseScreen {

    int w,h;
    TextureRegion malubackground;
    public MainMenuScreen(Core game,int id)
    {
        super(game,id);
    }

    @Override
    public void show() {
        super.show();
        Texture malubackground1=ResourceManager.Instance().get("screens/mainmenu/floreriaMalu.png");
        Drawable drawable = new TextureRegionDrawable(new TextureRegion((Texture) ResourceManager.Instance().get("screens/mainmenu/orderbutton.jpg")));
        ImageButton playButton = new ImageButton(drawable);
        playButton.setPosition(325,400);
        malubackground=new TextureRegion(malubackground1,0,0,1080,1776);
        stage.addActor(new ButterflyActor(100,120));
        stage.addActor(new ButterflyActor(650,500));
        stage.addActor(playButton);

        playButton.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(BaseScreen.MAKE_A_ORDER_SCREEN);
                game.currentScreen.show();
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Released");
            }
        });
        Gdx.input.setInputProcessor(stage);
        w=Gdx.graphics.getWidth();
        h=Gdx.graphics.getHeight();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        game.getScreenSprite().begin();
        game.getScreenSprite().draw(malubackground,0,0,w,h);
        game.getScreenSprite().end();
        stage.act();
        stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume()
    {
       // super.resume();
    }

    @Override
    public void hide() {

        //super.hide();
    }

    @Override
    public void dispose() {

        //super.dispose();
    }
}
