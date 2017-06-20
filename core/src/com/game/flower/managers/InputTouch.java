package com.game.flower.managers;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.physics.bullet.collision.ClosestRayResultCallback;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.game.flower.GameWorld;
import com.game.flower.Plataforma;
import com.game.flower.components.CharacterComponent;
import com.game.flower.components.ModelComponent;
import com.game.flower.components.ModelComponent1;

/**
 * Created by LuiferPc on 9/4/2017.
 */

public class InputTouch extends InputAdapter {
    Vector3 rayFrom = new Vector3();
    Vector3 rayTo = new Vector3();
    private final Vector3 tmp = new Vector3();
    private final Camera camera;
    private final Vector3 tempVector = new Vector3();
    ClosestRayResultCallback rayTestCB;
    boolean florSeleccionada;

    private GameWorld gameWorld;
    Plataforma plataforma;
    private Vector3 position = new Vector3();

    public InputTouch(GameWorld gameWorld,Camera camera,Plataforma plataforma) {
        this.camera = camera;
        this.gameWorld = gameWorld;
        this.plataforma=plataforma;
        rayTestCB = new ClosestRayResultCallback(Vector3.Zero, Vector3.Z);
        florSeleccionada=false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
       /* Ray ray = camera.getPickRay(screenX, screenY);
        rayFrom.set(ray.origin);
        rayTo.set(ray.direction).scl(50f).add(rayFrom);
        rayTestCB.setCollisionObject(null);
        rayTestCB.setClosestHitFraction(1f);
        rayTestCB.setRayFromWorld(rayFrom);
        rayTestCB.setRayToWorld(rayTo);
        gameWorld.bulletSystem.collisionWorld.rayTest(rayFrom, rayTo, rayTestCB);
        if (rayTestCB.hasHit()) {
            final btCollisionObject obj = rayTestCB.getCollisionObject();
            if (((Entity) obj.userData).getComponent(CharacterComponent.class) != null) {
                plataforma.mostrarMensaje("Toco la flor");
                //florSeleccionada=true;
                return true;
            }
        }*/
        Ray ray =  camera.getPickRay(screenX, screenY);
        final ModelComponent1 modelComponent = gameWorld.character.getComponent(ModelComponent1.class);
        return isFlowerTouched(screenX,screenY,modelComponent,ray);
    }

    public boolean isFlowerTouched(int screenX, int screenY, ModelComponent1 modelComponent, Ray ray)
    {
        modelComponent.instance.transform.getTranslation(position);
        position.add(modelComponent.center);
        final float len = ray.direction.dot(position.x-ray.origin.x, position.y-ray.origin.y, position.z-ray.origin.z);
        if (len < 0f)
            return false;
        float dist2 = position.dst2(ray.origin.x+ray.direction.x*len, ray.origin.y+ray.direction.y*len, ray.origin.z+ray.direction.z*len);
        if (dist2 <= modelComponent.radius * modelComponent.radius) {
            plataforma.mostrarMensaje("Toco la flor");
            return true;
        }
        return  false;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        /*if(florSeleccionada)
        {
            Ray ray = camera.getPickRay(screenX, screenY);
            final float distance = -ray.origin.y / ray.direction.y;
            Vector3 position=new Vector3();
            position.set(ray.direction).scl(distance).add(ray.origin);
            gameWorld.character.getComponent(ModelComponent.class).instance.transform.setTranslation(position);
        }*/

        Ray ray =  camera.getPickRay(screenX, screenY);
        final ModelComponent1 modelComponent = gameWorld.character.getComponent(ModelComponent1.class);
        if(isFlowerTouched(screenX,screenY,modelComponent,ray)){
            final float distance = -ray.origin.z / ray.direction.z;
            position.set(ray.direction).scl(distance).add(ray.origin);
            modelComponent.instance.transform.setTranslation(position);
            return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //florSeleccionada=false;
        return super.touchUp(screenX, screenY, pointer, button);
    }
}
