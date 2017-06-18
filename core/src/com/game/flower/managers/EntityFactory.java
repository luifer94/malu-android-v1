package com.game.flower.managers;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.model.data.ModelData;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.g3d.utils.TextureProvider;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.badlogic.gdx.physics.bullet.collision.btBroadphaseProxy;
import com.badlogic.gdx.physics.bullet.collision.btCapsuleShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.collision.btPairCachingGhostObject;
import com.badlogic.gdx.physics.bullet.dynamics.btKinematicCharacterController;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.UBJsonReader;
import com.game.flower.bullet.MotionState;
import com.game.flower.components.BulletComponent;
import com.game.flower.components.CharacterComponent;
import com.game.flower.components.ModelComponent;
import com.game.flower.systems.BulletSystem;

/**
 * Created by LuiferPc on 5/4/2017.
 */

public class EntityFactory {
    private static Model playerModel, enemyModel,boxModel;
    private static Texture playerTexture;
    private static ModelBuilder modelBuilder;
    private static ModelData enemyModelData;
    private static ModelComponent enemyModelComponent;

    static {
        modelBuilder = new ModelBuilder();
        playerTexture = new Texture(Gdx.files.internal("badlogic.jpg"));
        Material material = new Material(TextureAttribute.createDiffuse(playerTexture), ColorAttribute.createSpecular(1, 1, 1, 1), FloatAttribute.createShininess(8f));
        playerModel = modelBuilder.createCapsule(2f, 6f, 16, material, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        boxModel = modelBuilder.createBox(1f, 1f, 1f, new Material(ColorAttribute.createDiffuse(Color.WHITE),
                ColorAttribute.createSpecular(Color.WHITE), FloatAttribute.createShininess(64f)), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
    }


    public static Entity createStaticEntity(Model model, float x, float y, float z) {
        /*final BoundingBox boundingBox = new BoundingBox();
        model.calculateBoundingBox(boundingBox);
        Vector3 tmpV = new Vector3();
        btCollisionShape col = new btBoxShape(tmpV.set(boundingBox.getWidth() * 0.5f, boundingBox.getHeight() * 0.5f, boundingBox.getDepth() * 0.5f));
        Entity entity = new Entity();
        ModelComponent modelComponent = new ModelComponent(model, x, y, z);
        entity.add(modelComponent);
        BulletComponent bulletComponent = new BulletComponent();
        bulletComponent.bodyInfo = new btRigidBody.btRigidBodyConstructionInfo(0, null, col, Vector3.Zero);
        bulletComponent.body = new btRigidBody(bulletComponent.bodyInfo);
        bulletComponent.body.userData = entity;
        bulletComponent.motionState = new MotionState(modelComponent.instance.transform);
        ((btRigidBody) bulletComponent.body).setMotionState(bulletComponent.motionState);
        entity.add(bulletComponent);*/
        Entity entity = new Entity();
        ModelComponent modelComponent = new ModelComponent(model, x, y, z);
        entity.add(modelComponent);
        return entity;
    }

    private static Entity createCharacter(float x, float y, float z) {
        Entity entity = new Entity();
        Model flower= ResourceManager.Instance().get("flowers/funcionan/5/file.obj",Model.class);
        ModelComponent m=new ModelComponent(flower,x,y,z);
        entity.add(m);
        return entity;
    }
    private static Entity createCharacter2( float x, float y, float z) {
        Entity entity = new Entity();
        ModelBuilder modelBuilder = new ModelBuilder();
        Model model;
        Material mat = new
                Material(ColorAttribute.createDiffuse(Color.BLUE));
        model = modelBuilder.createBox(5, 5, 5, mat,
                VertexAttributes.Usage.Position |
                        VertexAttributes.Usage.Normal);

        ModelComponent modelComponent = new ModelComponent(model, x, y, z);
        entity.add(modelComponent);
        return entity;
    }

    public static Entity createFlower(float x, float y, float z) {
        Entity entity = createCharacter( x, y, z);
        return entity;
    }



}
