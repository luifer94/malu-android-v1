package com.game.flower.Actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.game.flower.managers.ResourceManager;

/**
 * Created by LuiferPc on 25/5/2017.
 */

public class ButterflyActor extends Actor {
    Animation butterflyAnimation;
    float duracion;

    public ButterflyActor(int posX,int posY){
        super();
        Texture texture= ResourceManager.Instance().get("screens/mainmenu/butterflyanimation.png", Texture.class);
        TextureRegion txtRegion=new TextureRegion(texture,1401,114);
        TextureRegion[][] temp=txtRegion.split(1401/13,114);
        TextureRegion[] frames= new TextureRegion[temp.length*temp[0].length];
        setSize(1401/13,114);
        setPosition(posX,posY);
        setScale(2.8f);
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
        butterflyAnimation=new Animation(0.056f,frames);
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
