package com.game.flower;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.game.flower.Actor.MProducto;
import com.game.flower.UI.FlowerArrangementUI;
import com.game.flower.components.ModelComponent;
import com.game.flower.entities.ProductEntity;
import com.game.flower.managers.EntityFactory;
import com.game.flower.systems.FlowerInputSystem;
import com.game.flower.systems.RenderSystem;

/**
 * Created by LuiferPc on 5/4/2017.
 */

public class ArrangementWorld {
    private ModelBatch modelBatch;
    private Environment environment;
    PerspectiveCamera perspectiveCamera;
    private Engine engine;

    public FlowerInputSystem flowerInputSystem;
    private FlowerArrangementUI flowerArrangementUI;
    private Entity groundEntity;
    private ProductEntity masetaActual;

    public ArrangementWorld(PerspectiveCamera perspectiveCamera, FlowerArrangementUI flowerArrangementUI) {
        this.flowerArrangementUI=flowerArrangementUI;
        this.perspectiveCamera=perspectiveCamera;
        initEnvironment();
        initModelBatch();
        masetaActual=null;
        addSystems();
        addEntities();
    }
    private void initEnvironment() {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.3f, 0.3f, 0.3f, 1.f));
    }
    private void initModelBatch() {
        modelBatch = new ModelBatch();
    }
    private void addEntities() {
        createGround();
    }
    private void createGround() {
        engine.addEntity(groundEntity=EntityFactory.createStaticEntity(0,0,0));
    }
    public void createFlower(MProducto mProducto,String resource, float x, float y, float z) {

        ProductEntity character = EntityFactory.createFlower(mProducto,resource,x, y, z);
        if(mProducto.isEsFlor()){
            flowerInputSystem.setProducto((ProductEntity)character);
        }else
        {
            if(masetaActual!=null){engine.removeEntity(masetaActual);}
            masetaActual=character;
            flowerInputSystem.setProducto(null);
            ModelComponent m=character.getComponent(ModelComponent.class);
            float h=groundEntity.getComponent(ModelComponent.class).bounds.getHeight();
            y=m.getPosition().y;
            y -=h;
            groundEntity.getComponent(ModelComponent.class).setPosition(m.getPosition().x,y,m.getPosition().z);

        }
        engine.addEntity(character);
    }
    private void addSystems() {
        engine = new Engine();
        engine.addSystem(new RenderSystem(modelBatch, environment));
        engine.addSystem(flowerInputSystem=new FlowerInputSystem(perspectiveCamera,engine));
    }
    public void render(float delta) {
        renderWorld(delta);
    }
    protected void renderWorld(float delta) {

        modelBatch.begin(perspectiveCamera);
        engine.update(delta);
        modelBatch.end();
    }

    public void resize(int width, int height) {
        perspectiveCamera.viewportHeight = height;
        perspectiveCamera.viewportWidth = width;
    }

    public void dispose() {
        modelBatch.dispose();
        modelBatch = null;
    }

    public void remove(Entity entity) {
        engine.removeEntity(entity);
        //bulletSystem.removeBody(entity);
    }
}