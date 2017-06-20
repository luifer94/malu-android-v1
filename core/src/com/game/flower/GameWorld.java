package com.game.flower;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.game.flower.components.CharacterComponent;
import com.game.flower.managers.EntityFactory;
import com.game.flower.systems.BulletSystem;
import com.game.flower.systems.RenderSystem;

/**
 * Created by LuiferPc on 5/4/2017.
 */

public class GameWorld {
    private ModelBatch modelBatch;
    private Environment environment;
    PerspectiveCamera perspectiveCamera;
    private Engine engine;
    public Entity character;
    //public BulletSystem bulletSystem;



    public ModelBuilder modelBuilder = new ModelBuilder();

    Model groundModel = modelBuilder.createBox(40, 1, 40,
            new Material(ColorAttribute.createDiffuse(Color.YELLOW), ColorAttribute.createSpecular(Color.BLUE), FloatAttribute
                    .createShininess(16f)), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

    public GameWorld(PerspectiveCamera perspectiveCamera) {
        Bullet.init();
        int w= Gdx.graphics.getWidth();
        int h= Gdx.graphics.getHeight();
        this.perspectiveCamera=perspectiveCamera;
        initEnvironment();
        initModelBatch();
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
        createFlower(0, 0, 0);
    }
    private void createGround() {
        engine.addEntity(EntityFactory.createStaticEntity(groundModel, 0, 0, 0));
    }
    private void createFlower(float x, float y, float z) {

        character = EntityFactory.createFlower(x, y, z);
        engine.addEntity(character);
    }
    private void addSystems() {
        engine = new Engine();
        engine.addSystem(new RenderSystem(modelBatch, environment));
       // engine.addSystem(bulletSystem = new BulletSystem());
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
       // bulletSystem.collisionWorld.removeAction(character.getComponent(CharacterComponent.class).characterController);
       // bulletSystem.collisionWorld.removeCollisionObject(character.getComponent(CharacterComponent.class).ghostObject);
        //bulletSystem.dispose();

       // bulletSystem = null;

        groundModel.dispose();
        modelBatch.dispose();

        modelBatch = null;
        //character.getComponent(CharacterComponent.class).characterController.dispose();
        // character.getComponent(CharacterComponent.class).ghostObject.dispose();
        //character.getComponent(CharacterComponent.class).ghostShape.dispose();
    }

    public void remove(Entity entity) {
        engine.removeEntity(entity);
        //bulletSystem.removeBody(entity);
    }
}