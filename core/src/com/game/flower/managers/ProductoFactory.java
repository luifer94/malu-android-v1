package com.game.flower.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.game.flower.Actor.MProducto;
import com.game.flower.Contants;
import com.game.flower.screens.MakerOrderScreen;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by luifer on 19-06-17.
 */

public class ProductoFactory {
    private static ProductoFactory _instance=null;
    public List<MProduct> productos;
    private ProductoFactory()
    {
        productos=null;
    }

    public static final ProductoFactory Instance()
    {
        if(_instance ==null)
        {
            _instance =new ProductoFactory();
        }
        return _instance;
    }
    public void getProducts()
    {
        Net.HttpRequest requestBests = new Net.HttpRequest(Net.HttpMethods.GET);
        requestBests.setUrl(Contants.GET_PRODUCT);
        Gdx.net.sendHttpRequest(requestBests, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                int code=httpResponse.getStatus().getStatusCode();
                try{
                if(code==200){
                    String respond=httpResponse.getResultAsString();
                    Json json=new Json();
                    ProductJsonParser productsJsonParser=json.fromJson(ProductJsonParser.class,respond);
                    productos=productsJsonParser.products;
                }else {
                    Gdx.app.exit();
                    System.out.println("Error code: "+code);
                }
                }catch (Exception ex)
                {
                    System.out.println(ex);
                }
            }

            @Override
            public void failed(Throwable t) {
                System.out.println(t);
            }

            @Override
            public void cancelled() {
                System.out.println("Cancel");
            }
        });
    }
    public List<MProducto> productosGenerator(MakerOrderScreen nav)
    {
        List<MProducto> productos=new ArrayList<>();
        int flor=1;
        int pot=4;

        for (int i=0;i<this.productos.size();i++)
        {

            MProduct p=this.productos.get(i);
            String flower=p.tipo_producto==1?"flowers/":"pot/";
            int id=p.tipo_producto==1?flor:pot;
            flower+=id+"/"+id+".png";
            Drawable drawableIcon = new TextureRegionDrawable(new TextureRegion((Texture) ResourceManager.Instance().get(flower)));
            MProducto mProducto=new MProducto(drawableIcon);
            mProducto.setId(p.id);
            mProducto.setName(p.nombre);
            mProducto.setEsFlor(p.tipo_producto==1?true:false);
            mProducto.setPrice(p.precio);
            mProducto.setRecurso(id);
            mProducto.addListener(new ProductGesture(false,nav,p.id,id));
            productos.add(mProducto);
            if(p.tipo_producto==1){flor++;}else{pot++;}
            if(flor==4)
            {
                flor=1;
            }
            if(pot==6)
            {
                pot=4;
            }

        }
        return productos;
    }

}
