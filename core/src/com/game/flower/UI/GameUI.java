package com.game.flower.UI;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.game.flower.Core;
import com.game.flower.UI.controllerWidgets.RotationWidget;
import com.game.flower.components.ModelComponent;

/**
 * Created by LuiferPc on 5/4/2017.
 */

public class GameUI {
    private Core game;
    public Stage stage;
    private RotationWidget controllerWidget;
    Entity flower;
    Vector3 startPoint;
    public GameUI(Core game,Entity objeto) {
        this.game = game;
        startPoint=new Vector3(5,3,5);
        this.flower=objeto;
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        setWidgets();
        configureWidgets();
    }

    public void setWidgets() {
        if (Gdx.app.getType() == Application.ApplicationType.Android) controllerWidget = new RotationWidget();
    }

    public void configureWidgets() {
        if (Gdx.app.getType() == Application.ApplicationType.Android) controllerWidget.addToStage(stage);
    }

    public void update(float delta) {
        checkForRotation();
        stage.act(delta);
    }

    private void checkForRotation() {
        Vector2 movement=controllerWidget.getMovementVector();
        if (movement.y > 0 && noXmoved(movement.x)) {flower.getComponent(ModelComponent.class).instance.transform.rotate(startPoint,new Vector3(0.04f,0f,0f));}
        else if (movement.y < 0  && noXmoved(movement.x)) {flower.getComponent(ModelComponent.class).instance.transform.rotate(startPoint,new Vector3(-0.04f,0f,0f));}
        else if (movement.x < 0) {flower.getComponent(ModelComponent.class).instance.transform.rotate(startPoint,new Vector3(0f,0.04f,0f));}
        else if (movement.x > 0) {flower.getComponent(ModelComponent.class).instance.transform.rotate(startPoint,new Vector3(0f,-0.04f,0f));}
    }

    private boolean noXmoved(float x) {
        return (x>-0.40f && x<0.40f);
    }

    public void render() {
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    public void dispose() {
        stage.dispose();
    }
}