package com.game.flower.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;

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
        _instance.load("flowers/1/1.png",Texture.class);
        _instance.load("flowers/2/2.png",Texture.class);
        _instance.load("flowers/3/3.png",Texture.class);
        _instance.load("1.jpg",Texture.class);
        _instance.load("flowers/3/ItmRipStickFlower.obj", Model.class);



    }
}
