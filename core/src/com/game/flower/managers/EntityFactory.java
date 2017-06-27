package com.game.flower.managers;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g3d.Model;
import com.game.flower.Actor.MProducto;
import com.game.flower.components.FlowerComponent;
import com.game.flower.components.ModelComponent;
import com.game.flower.entities.ProductEntity;

/**
 * Created by LuiferPc on 5/4/2017.
 */

public class EntityFactory {

    public static Entity createStaticEntity( float x, float y, float z) {
        Entity entity = new Entity();
        Model mesa= ResourceManager.Instance().get("table/2/outdoor_little_table.obj",Model.class);
        ModelComponent m=new ModelComponent(mesa,x,y,z);
        m.updateDimensions();
        float w=m.bounds.getWidth();
        float d=m.bounds.getDepth();
        entity.add(m);
        return entity;
    }

    private static ProductEntity createCharacter(MProducto mProducto,String resource, float x, float y, float z) {
        ProductEntity entity = new ProductEntity(mProducto);
        String name=interpretar(resource);
        Model flower= ResourceManager.Instance().get(mProducto.isEsFlorToStr()+"/"+resource+"/"+name+".obj",Model.class);
        ModelComponent m=new ModelComponent(flower,x,y,z);
        scalar(m,name);
        float height= m.bounds.getHeight();
        if(!mProducto.isEsFlor()){y-=height;};
        m.setPosition(x,y,z);
        Component f=new FlowerComponent();
        entity.add(m);
        entity.add(f);
        return entity;
    }

    private static void scalar(ModelComponent m, String name) {
        if(name.equals("white_flower"))
        {
            m.scalar(0.5f);
        }else if(name.equals("Southern Flower"))
        {
            m.scalar(1.5f);
        }
        else if(name.equals("Bucket"))
        {
            m.scalar(1.5f);
        }
        m.updateDimensions();
    }

    private static String interpretar(String resource) {
         if(resource.equals("1"))
        {
            return "Southern Flower";
        }else if(resource.equals("2"))
        {
            return "ItmRipStickFlower";
        }
         else if(resource.equals("3"))
         {
             return "ItmRipStick";
         }
        else if(resource.equals("4"))
        {
            return "Bucket";
        }
            return "skemptyplantpotmesh";
    }
    public static ProductEntity createFlower(MProducto mProducto,String resource,float x, float y, float z) {
        ProductEntity entity = createCharacter(mProducto,resource, x, y, z);
        return entity;
    }



}
