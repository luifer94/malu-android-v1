package com.game.flower.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.game.flower.UI.NavigationDrawer;

/**
 * Created by LuiferPc on 13/5/2017.
 */

public class NavigationDrawerScreenExample implements Screen {
    Stage stage;
    @Override
    public void show() {
        stage = new Stage(new StretchViewport(1080, 1920));

        // May be you want to make some Actions with NavigationDrawer state
        NavigationDrawer.NavigationDrawerListener listener = new NavigationDrawer.NavigationDrawerListener() {

            @Override
            public void onStart() {
                System.out.println("onStart");
            }

            @Override
            public void onRun() {
                System.out.println("onRun");
            }

            @Override
            public void onFinish(float camX) {
                System.out.println("onFinish: " + camX);
            }
        };

        // You must be initialize NavigationDrawer Firstly
        NavigationDrawer.initialize(stage, listener);

        // This image is sample to show how navigationDrawer look like on the screen
        Image background= new Image(new Texture(Gdx.files.internal("1.jpg")));
        background.addListener(new ClickListener() {
            private int clicked = 0;
            public void clicked(InputEvent event, float x, float y) {
                if (clicked % 2 == 0) {
                    clicked++;
                    NavigationDrawer.show(true);
                } else {
                    clicked++;
                    NavigationDrawer.show(false);
                }
            }
        });
        background.setFillParent(true);
        stage.addActor(background);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
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
