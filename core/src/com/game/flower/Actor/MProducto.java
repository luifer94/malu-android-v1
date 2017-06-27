package com.game.flower.Actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.game.flower.managers.ResourceManager;

/**
 * Created by luifer on 19-06-17.
 */

public class MProducto extends ImageButton {
    private int id;
    private int stock;
    private float price;
    private String name;
    Drawable drawableIcon;
    private boolean esFlor;
    private int recurso;

    public MProducto(Drawable imageUp) {
        super(imageUp);
        drawableIcon=imageUp;
        setSize(360,200);
        esFlor=true;
    }

    public int getRecurso() {
        return recurso;
    }

    public void setRecurso(int recurso) {
        this.recurso = recurso;
    }

    public boolean isEsFlor() {
        return esFlor;
    }

    public void setEsFlor(boolean esFlor) {
        this.esFlor = esFlor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Drawable getDrawableIcon() {
        return drawableIcon;
    }

    public void setDrawableIcon(Drawable drawableIcon) {
        this.drawableIcon = drawableIcon;
    }

    public String isEsFlorToStr() {
        return isEsFlor() ? "flowers":"pot";
    }
}
