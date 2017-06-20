package com.game.flower.managers;

/**
 * Created by luifer on 19-06-17.
 */

public interface Listener {
    public void onTap(boolean esDeSeleccion,int id,int recurso);
    public void onTouchDown(boolean esDeSeleccion,int id,int recurso,float x,float y);
}
