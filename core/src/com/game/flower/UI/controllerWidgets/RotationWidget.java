package com.game.flower.UI.controllerWidgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.game.flower.managers.ResourceManager;
import com.game.flower.screens.MakerOrderScreen;

/**
 * Created by LuiferPc on 5/4/2017.
 */

public class RotationWidget{
    private static Touchpad movementPad;
    private static Vector2 movementVector;
    boolean transicion;

    public RotationWidget(MakerOrderScreen father) {
        Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();
        touchpadStyle.knob = new TextureRegionDrawable(new TextureRegion((Texture) ResourceManager.Instance().get("select/touchKnob.png")));
        touchpadStyle.knob.setMinWidth(64);
        touchpadStyle.knob.setMinHeight(64);
        touchpadStyle.background = new TextureRegionDrawable(new TextureRegion((Texture) ResourceManager.Instance().get("select/touchBackground.png")));
        touchpadStyle.background.setMinWidth(64);
        touchpadStyle.background.setMinHeight(64);
        movementPad = new Touchpad(10, touchpadStyle);
        movementPad.addAction(Actions.color(Color.ORANGE));
        transicion=true;
        movementPad.addListener(father);
        movementVector = new Vector2(0, 0);
    }

    public void controlarAccion()
    {
        if(!movementPad.hasActions())
        {
            if(transicion)
            {
                movementPad.addAction(Actions.color(Color.ORANGE,1.5f));
            }else {
                movementPad.addAction(Actions.color(Color.RED,1.5f));
            }
            transicion=!transicion;
        }
    }

    public void addToStage(Stage stage,int x,int y) {
        movementPad.setBounds(x, y, 645, 645);
        stage.addActor(movementPad);
    }

    public static Vector2 getMovementVector() {
        movementVector.set(movementPad.getKnobPercentX(), movementPad.getKnobPercentY());
        return movementVector;
    }

}