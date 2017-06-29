package com.game.flower.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.game.flower.managers.ResourceManager;

/**
 * Created by LuiferPc on 28/6/2017.
 */

public class FlowerActor extends Actor {
    Animation butterflyAnimation;
    float duracion;

    public FlowerActor(){
        super();
        Texture texture= ResourceManager.Instance().get("screens/makeaorder/flower.png", Texture.class);
        TextureRegion txtRegion=new TextureRegion(texture,900,420);
        TextureRegion[][] temp=txtRegion.split(900/6,420/4);
        TextureRegion[] frames= new TextureRegion[temp.length*temp[0].length];
        setSize(900/6,420/4);
        setPosition(-685,800);
        setScale(4.5f);
        int indice=0;
        duracion=0;
        for (int i=0;i<temp.length;i++)
        {
            for (int j=0;j<temp[i].length;j++)
            {
                frames[indice] = temp[i][j];

                indice++;
            }
        }
        butterflyAnimation=new Animation(0.18f,frames);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        duracion+= Gdx.graphics.getDeltaTime();
        batch.draw((TextureRegion) butterflyAnimation.getKeyFrame(duracion,true),getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
