package com.game.flower.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.game.flower.Actor.MProducto;
import com.game.flower.ArrangementWorld;
import com.game.flower.Core;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.game.flower.UI.FlowerArrangementUI;
import com.game.flower.managers.ResourceManager;


/**
 * Created by LuiferPc on 5/4/2017.
 */

public class FlowerArrangementScreen extends BaseScreen {
    public ArrangementWorld arrangementWorld;
    private static final float FOV = 67F;
    private PerspectiveCamera perspectiveCamera;
    protected CameraInputController camController;
    private FlowerArrangementUI arrangementUI;
    private MProducto product;
    private Stage fondo;

    public FlowerArrangementScreen(Core game, int id) {
        super(game, id);
        initPersCamera();

    }
    public void initAll(){
        stage = new Stage(new StretchViewport(1080, 1920));
        arrangementUI=new FlowerArrangementUI(game);
        camController = new CameraInputController(perspectiveCamera);
        arrangementWorld = new ArrangementWorld(perspectiveCamera,arrangementUI);
        fondo = new Stage(new StretchViewport(1080, 1920));
        Drawable drawable = new TextureRegionDrawable(new TextureRegion((Texture) ResourceManager.Instance().get("screens/flowerarrangementscreen/background.jpg")));
        Image image=new Image(drawable);
        fondo.addActor(image);

    }

    private void initPersCamera() {
        perspectiveCamera = new PerspectiveCamera(FOV,  Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        setCamPosition();

    }

    private void setCamPosition() {
        perspectiveCamera.position.set(0, 40f, 0f);
        perspectiveCamera.lookAt(0,0,0);
        perspectiveCamera.near = 1f;
        perspectiveCamera.far = 300f;
        perspectiveCamera.update();
    }

    @Override
    public void show() {
        InputMultiplexer inputMultiplexer= new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(arrangementWorld.flowerInputSystem);
        inputMultiplexer.addProcessor(camController);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        /** Updates */
        arrangementUI.update(delta);
        fondo.act(delta);
        /** Draw */
        camController.update();
        fondo.draw();
        arrangementWorld.render(delta);
        arrangementUI.render();
    }

    @Override
    public void resize(int width, int height) {
        arrangementUI.resize(width, height);
        arrangementWorld.resize(width, height);
    }

    @Override
    public void dispose() {
        arrangementWorld.dispose();
        arrangementUI.dispose();
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

    public void setProduct(MProducto product) {
        arrangementWorld.createFlower(product,String.valueOf(product.getRecurso()),0,0,0);
        arrangementWorld.flowerInputSystem.isDeleting=false;
        this.product = product;
        setCamPosition();
    }

    public MProducto getProduct() {
        return product;
    }
}