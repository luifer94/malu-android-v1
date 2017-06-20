package com.game.flower.managers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

/**
 * Created by luifer on 19-06-17.
 */

public class ProductGesture extends ActorGestureListener {
    Listener listener;
    private int id;
    private int recurso;
    private boolean esDeSeleccion;

    public ProductGesture(boolean esDeSeleccion,Listener listener,int id,int recurso) {
        this.listener = listener;
        this.recurso=recurso;
        this.id=id;
        this.esDeSeleccion=esDeSeleccion;
    }

    @Override
    public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if(esDeSeleccion){listener.onTouchDown(esDeSeleccion,id,recurso,x,y);}
        super.touchDown(event, x, y, pointer, button);
    }

    @Override
    public void tap(InputEvent event, float x, float y, int count, int button) {
        listener.onTap(esDeSeleccion,id,recurso);
        super.tap(event, x, y, count, button);
    }
}
