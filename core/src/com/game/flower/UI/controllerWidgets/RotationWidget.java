package com.game.flower.UI.controllerWidgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by LuiferPc on 5/4/2017.
 */

public class RotationWidget {
    private static Touchpad movementPad;
    private static Vector2 movementVector;

    public RotationWidget() {
        Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();
        touchpadStyle.knob = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("touchKnob.png"))));
        touchpadStyle.knob.setMinWidth(64);
        touchpadStyle.knob.setMinHeight(64);
        touchpadStyle.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("touchBackground.png"))));
        touchpadStyle.background.setMinWidth(64);
        touchpadStyle.background.setMinHeight(64);
        movementPad = new Touchpad(10, touchpadStyle);
        movementPad.setColor(0.5f, 0.5f, 0.5f, 0.5f);
        movementVector = new Vector2(0, 0);
    }

    public void addToStage(Stage stage) {
        movementPad.setBounds(15, 15, 300, 300);
        stage.addActor(movementPad);
    }

    public static Vector2 getMovementVector() {
        movementVector.set(movementPad.getKnobPercentX(), movementPad.getKnobPercentY());
        return movementVector;
    }

}