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

    public ProductGesture(Listener listener,int id,int recurso) {
        this.listener = listener;
        this.recurso=recurso;
        this.id=id;
    }


    @Override
    public void tap(InputEvent event, float x, float y, int count, int button) {
        listener.onTap(id,recurso);
        super.tap(event, x, y, count, button);
    }
}
