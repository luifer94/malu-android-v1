package com.game.flower.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.game.flower.Core;
import com.game.flower.managers.ResourceManager;

/**
 * Created by luifer on 18-06-17.
 */

public class Scrollpanel extends BaseScreen {

    ScrollPane scrollpane;
    //Skin skin;
    Stage stage;
    Table container, table1, table2, table3;
   // Texture texture1, texture2, texture3;

    public Scrollpanel(Core game, int id) {
        super(game, id);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);    //sets up the clear color (background color) of the screen.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  //instructs openGL to actually clear the screen to the newly set clear color.
        stage.draw();
        stage.act(delta);

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {

        // setup skin

        // table that holds the scroll pane
        container = new Table();
        container.setWidth(Gdx.graphics.getWidth());
        container.setHeight(Gdx.graphics.getHeight());

        Drawable drawable = new TextureRegionDrawable(new TextureRegion((Texture) ResourceManager.Instance().get("screens/mainmenu/orderbutton.jpg")));
        ImageButton image1 = new ImageButton(drawable);
        ImageButton image2 = new ImageButton(drawable);
        ImageButton image3 = new ImageButton(drawable);

        Table innerContainer = new Table();
        innerContainer.add(image1);
        innerContainer.row();
        innerContainer.add(image2);
        innerContainer.row();
        innerContainer.add(image3);


        image1.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("TOUCHDOWN: X= "+x+",Y="+y);
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                System.out.println("TOUCHDRAGGED: X= "+x+",Y="+y);
                super.touchDragged(event, x, y, pointer);
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("TOUCHUP: X= "+x+",Y="+y);
            }
        });

        image2.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("pulso el boton 2");
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("pulso el boton 2");
            }
        });


        image3.addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("pulso el boton 3");
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("pulso el boton 3");
            }
        });

        // create the scrollpane
        scrollpane = new ScrollPane(innerContainer);

        //add the scroll pane to the container
        container.add(scrollpane).fill().expand();

        // setup stage
        stage = new Stage();

        // add container to the stage
        stage.addActor(container);


        // setup input processor (gets clicks and stuff)
        Gdx.input.setInputProcessor(stage);

        // setup a listener for the tables with out data



    }

    @Override
    public void hide() {
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
}
