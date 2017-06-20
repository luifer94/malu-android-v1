package com.game.flower.Actor;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Created by luifer on 19-06-17.
 */

public class SideMenu extends ImageButton {
    boolean show;
    public SideMenu(Drawable imageUp) {
        super(imageUp);
        show=false;
        setSize(360,200);
    }
}
