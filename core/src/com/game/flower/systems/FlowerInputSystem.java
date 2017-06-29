package com.game.flower.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.collision.Ray;
import com.game.flower.Actor.MProducto;
import com.game.flower.components.FlowerComponent;
import com.game.flower.components.ModelComponent;
import com.game.flower.entities.ProductEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LuiferPc on 20/6/2017.
 */

public class FlowerInputSystem extends EntitySystem implements InputProcessor {

    private ProductEntity producto;
    private final Camera camera;
    private Vector3 position = new Vector3();
    private ImmutableArray<Entity> entities;
    public boolean isDeleting;
    private Engine e;

    public FlowerInputSystem(Camera camera,Engine engine) {
        this.camera = camera;
        isDeleting=false;
        e=engine;
    }
    public void addedToEngine(Engine e) {
        entities = e.getEntitiesFor(Family.all(FlowerComponent.class).get());
    }

    public ImmutableArray<Entity> getEntities() {
        return entities;
    }
    public List<MProducto> toMProducto()
    {
        List<MProducto> mProductos=new ArrayList<>();
        for (Entity e:entities)
        {
            mProductos.add(((ProductEntity)e).mProducto);
        }
        return mProductos;
    }

    public void setEntities(ImmutableArray<Entity> entities) {
        this.entities = entities;
    }

    @Override
    public void removedFromEngine(Engine e) {

        entities = e.getEntitiesFor(Family.all(FlowerComponent.class).get());
    }

    public ProductEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductEntity producto) {
        this.producto = producto;
    }

    public boolean isFlowerTouched(int screenX, int screenY, ModelComponent modelComponent, Ray ray)
    {
        modelComponent.instance.transform.getTranslation(position);
        position.add(modelComponent.center);
        final float len = ray.direction.dot(position.x-ray.origin.x, position.y-ray.origin.y, position.z-ray.origin.z);
        if (len < 0f)
            return false;
        float dist2 = position.dst2(ray.origin.x+ray.direction.x*len, ray.origin.y+ray.direction.y*len, ray.origin.z+ray.direction.z*len);
        if (dist2 <= modelComponent.radius * modelComponent.radius) {
            return true;
        }
        return  false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(isDeleting)
        {
            int object=getObject(screenX,screenY);
            if(object<0) return false;
            e.removeEntity(entities.get(object));
            return true;
        }
        if(producto==null)return false;
        Ray ray =  camera.getPickRay(screenX, screenY);
        final ModelComponent modelComponent = producto.getComponent(ModelComponent.class);
        return isFlowerTouched(screenX,screenY,modelComponent,ray);
    }

    public int getObject (int screenX, int screenY) {
        Ray ray = camera.getPickRay(screenX, screenY);

        int result = -1;
        float distance = -1;

        for (int i = 0; i < entities.size(); ++i) {
            final ModelComponent instance =  entities.get(i).getComponent(ModelComponent.class);

            instance.instance.transform.getTranslation(position);
            position.add(instance.center);

            final float len = ray.direction.dot(position.x-ray.origin.x, position.y-ray.origin.y, position.z-ray.origin.z);
            if (len < 0f)
                continue;

            float dist2 = position.dst2(ray.origin.x+ray.direction.x*len, ray.origin.y+ray.direction.y*len, ray.origin.z+ray.direction.z*len);
            if (distance >= 0f && dist2 > distance)
                continue;

            if (dist2 <= instance.radius * instance.radius) {
                result = i;
                distance = dist2;
            }
        }
        return result;
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(!isDeleting){
            if(producto==null)return false;
            Ray ray =  camera.getPickRay(screenX, screenY);
            final ModelComponent modelComponent = producto.getComponent(ModelComponent.class);
            if(isFlowerTouched(screenX,screenY,modelComponent,ray)){
                final float distance = -ray.origin.y / ray.direction.y;
                position.set(ray.direction).scl(distance).add(ray.origin);
                if(!estaEnElLimite())
                {
                    return false;
                }
                modelComponent.instance.transform.setTranslation(position);
                return true;
            }
        }
        return false;
    }

    private boolean estaEnElLimite() {
        return (position.x<45 && position.z<35) && (position.x>-45 && position.z>-35);
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public void resetear() {
        producto=null;
        isDeleting=false;
    }
}
