package com.game.flower.UI;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.game.flower.Core;
import com.game.flower.UI.controllerWidgets.MenuWidget;
import com.game.flower.UI.controllerWidgets.RotationWidget;
import com.game.flower.components.ModelComponent;
import com.game.flower.managers.ResourceManager;
import com.game.flower.screens.FlowerArrangementScreen;

/**
 * Created by LuiferPc on 5/4/2017.
 */

public class FlowerArrangementUI {
    private Core game;
    private Stage stage;
    private MenuWidget menuWidget;
    private ImageButton menubutton;
    private ImageButton deleteButton;

    public FlowerArrangementUI(Core game) {
        this.game = game;
        stage = ((FlowerArrangementScreen) game.currentScreen).stage;
        setWidgets();
        configureWidgets();
    }

    public void setWidgets()
    {
        Drawable drawable = new TextureRegionDrawable(new TextureRegion((Texture) ResourceManager.Instance().get("screens/flowerarrangementscreen/menu.png")));
        Drawable drawable1 = new TextureRegionDrawable(new TextureRegion((Texture) ResourceManager.Instance().get("screens/flowerarrangementscreen/deleteButton.png")));
        menubutton=new ImageButton(drawable);
        deleteButton=new ImageButton(drawable1);
        Skin skin = new Skin();
        FileHandle fileHandle = Gdx.files.internal("skin/uiskin.json");
        FileHandle atlasFile = fileHandle.sibling("uiskin.atlas");
        if (atlasFile.exists()) {
            skin.addRegions(new TextureAtlas(atlasFile));
        }
        skin.load(fileHandle);
        menuWidget= new MenuWidget("Menu",skin,game,stage);
    }


    public void configureWidgets()
    {
        menuWidget.setSize(1080,1920);
        menuWidget.setPosition(0, 0);
        menubutton.setSize(150,150);
        menubutton.setPosition(0,Gdx.graphics.getHeight()-150);
        deleteButton.setSize(150,150);
        deleteButton.setPosition(152,Gdx.graphics.getHeight()-150);

        stage.addActor(menubutton);
        stage.addActor(deleteButton);
        deleteButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                boolean b= ((FlowerArrangementScreen)game.currentScreen).arrangementWorld.flowerInputSystem.isDeleting;
                ((FlowerArrangementScreen)game.currentScreen).arrangementWorld.flowerInputSystem.isDeleting=!b;
                return true;
            }
        });
        menubutton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                stage.addActor(menuWidget);
                return true;
            }
        });
    }

    public void update(float delta)
    {
        stage.act(delta);
    }

    public void render()
    {
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    public void dispose() {
        stage.dispose();
    }
}