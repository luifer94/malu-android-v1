package com.game.flower.UI.controllerWidgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.game.flower.Actor.MProducto;
import com.game.flower.Core;
import com.game.flower.entities.ProductEntity;
import com.game.flower.managers.ResourceManager;
import com.game.flower.screens.BaseScreen;
import com.game.flower.screens.FlowerArrangementScreen;
import com.game.flower.screens.OrderNowScreen;
import com.game.flower.screens.SplashScreen;

import java.util.List;

/**
 * Created by LuiferPc on 25/6/2017.
 */

public class MenuWidget extends Window {
    private Core game;
    Skin skin;
    private ImageButton closeDialog, adicionarButton, finalizarButton,quitButton;
    private Stage stage;

    public MenuWidget(String title, Skin skin,Core game, Stage stage) {
        super(title, skin);
        getTitleTable().setHeight(150);
        getTitleLabel().setWrap(true);
        this.skin=skin;
        this.game = game;
        this.stage = stage;
        setWidgets();
        configureWidgets();
        setListeners();
    }


    private void setWidgets() {
        closeDialog = new ImageButton(new TextureRegionDrawable(new TextureRegion((Texture) ResourceManager.Instance().get("screens/flowerarrangementscreen/close.png"))));
        closeDialog.setSize(600,300);
        adicionarButton = new ImageButton(new TextureRegionDrawable(new TextureRegion((Texture) ResourceManager.Instance().get("screens/flowerarrangementscreen/add.png"))));
        adicionarButton.setSize(600,300);
        finalizarButton = new ImageButton(new TextureRegionDrawable(new TextureRegion((Texture) ResourceManager.Instance().get("screens/flowerarrangementscreen/order.png"))));
        finalizarButton.setSize(600,300);
        quitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion((Texture) ResourceManager.Instance().get("screens/flowerarrangementscreen/exit.png"))));
        quitButton.setSize(600,300);
    }

    private void configureWidgets() {
        this.getTitleTable().add(closeDialog).padTop(170);
        this.add(adicionarButton).fill().row();
        this.add(finalizarButton).fill().row();
        this.add(quitButton).fill().row();
    }

    private void setListeners() {
        closeDialog.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float x, float y) {
                handleUpdates();
            }
        });

        adicionarButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float x, float y) {
                handleUpdates();
                game.setScreen(BaseScreen.MAKE_A_ORDER_SCREEN);
                game.currentScreen.show();
            }
        });

        finalizarButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float x, float y) {
                game.currentScreen=new OrderNowScreen(game,-1, (List<MProducto>) ((FlowerArrangementScreen)game.currentScreen).arrangementWorld.flowerInputSystem.toMProducto());
                game.currentScreen.show();
            }
        });
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    private void handleUpdates() {
        if (this.getStage() == null) {
            stage.addActor(this);
            Gdx.input.setCursorCatched(false);
        } else {
            this.remove();
            Gdx.input.setCursorCatched(true);
        }
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
    }


}