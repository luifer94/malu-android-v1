package com.game.flower.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.game.flower.Actor.MProducto;
import com.game.flower.screens.NavigationDrawerScreenExample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by luifer on 19-06-17.
 */

public class ProductoFactory {

    public static List<MProducto> dummyProductoGenerator(NavigationDrawerScreenExample nav)
    {
        List<MProducto> productos=new ArrayList<>();
        int n=1;
        for (int i=1;i<=6;i++)
        {

            String flower="flowers/"+n+"/"+n+".png";
            Drawable drawableIcon = new TextureRegionDrawable(new TextureRegion((Texture) ResourceManager.Instance().get(flower)));
            MProducto mProducto=new MProducto(drawableIcon);
            mProducto.setId(n);
            mProducto.addListener(new ProductGesture(nav,n,n));
            productos.add(mProducto);
            n++;
            if(i==3)
            {
                n=1;
            }

        }
        return productos;
    }
}
