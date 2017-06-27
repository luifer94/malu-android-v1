package com.game.flower.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader.BitmapFontParameter;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

/**
 * Created by LuiferPc on 24/5/2017.
 */

public class ResourceManager extends AssetManager{
    private static ResourceManager _instance=null;
    private ResourceManager()
    {
        super();
    }

    public static final ResourceManager Instance()
    {
        if(_instance ==null)
        {
            _instance =new ResourceManager();
            loadResources();
        }
        return _instance;
    }

    private static void loadResources() {
        _instance.load("screens/mainmenu/butterflyanimation.png", Texture.class);
        _instance.load("screens/mainmenu/floreriaMalu.png",Texture.class);
        _instance.load("screens/mainmenu/orderbutton.jpg",Texture.class);
        _instance.load("screens/makeaorder/side_menu.png",Texture.class);
        _instance.load("screens/makeaorder/background.jpg",Texture.class);

        _instance.load("screens/flowerarrangementscreen/menu.png",Texture.class);
        _instance.load("screens/flowerarrangementscreen/add.png",Texture.class);
        _instance.load("screens/flowerarrangementscreen/close.png",Texture.class);
        _instance.load("screens/flowerarrangementscreen/exit.png",Texture.class);
        _instance.load("screens/flowerarrangementscreen/order.png",Texture.class);
        _instance.load("screens/flowerarrangementscreen/deleteButton.jpg",Texture.class);
        _instance.load("screens/flowerarrangementscreen/background.jpg",Texture.class);

        _instance.load("select/touchBackground.png",Texture.class);
        _instance.load("select/touchKnob.png",Texture.class);
        _instance.load("flowers/1/1.png",Texture.class);
        _instance.load("flowers/2/2.png",Texture.class);
        _instance.load("flowers/3/3.png",Texture.class);

        _instance.load("pot/4/4.png",Texture.class);
        _instance.load("pot/5/5.png",Texture.class);

        _instance.load("1.jpg",Texture.class);

        _instance.load("pot/4/Bucket.obj",Model.class);
        _instance.load("pot/5/skemptyplantpotmesh.obj",Model.class);

        _instance.load("flowers/1/Southern Flower.obj", Model.class);
        _instance.load("flowers/2/ItmRipStickFlower.obj", Model.class);
        _instance.load("flowers/3/ItmRipStick.obj", Model.class);

        _instance.load("table/2/outdoor_little_table.obj",Model.class);

        BitmapFontParameter fntParameters = new BitmapFontParameter();
        fntParameters.minFilter =  TextureFilter.Linear;
        _instance.load("skin/normal.fnt", BitmapFont.class, fntParameters);
    }
}
