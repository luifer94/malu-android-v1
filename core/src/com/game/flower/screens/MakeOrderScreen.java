package com.game.flower.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.game.flower.Core;
import com.game.flower.GameWorld;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;


/**
 * Created by LuiferPc on 5/4/2017.
 */

public class MakeOrderScreen extends BaseScreen {
    GameWorld gameWorld;
   // GameUI gameUI;
    private static final float FOV = 67F;
    private PerspectiveCamera perspectiveCamera;
    protected CameraInputController camController;

    public MakeOrderScreen(Core game,int id) {
        super(game, id);
        initPersCamera();

    }
    public void initAll(){
        camController = new CameraInputController(perspectiveCamera);
        gameWorld = new GameWorld(perspectiveCamera);
    }

    private void initPersCamera() {
        perspectiveCamera = new PerspectiveCamera(FOV,  Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        perspectiveCamera.position.set(1f, 1f, 1f);
        perspectiveCamera.lookAt(0,0,0);
        perspectiveCamera.near = 1f;
        perspectiveCamera.far = 300f;
        perspectiveCamera.update();
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputMultiplexer(camController));
      //  gameUI = new GameUI(game,gameWorld.character);
      //  Gdx.input.setInputProcessor(gameUI.stage);


    }

    @Override
    public void render(float delta) {
        /** Updates */
       // gameUI.update(delta);
       // gameUI.render();
        /** Draw */
        camController.update();
        gameWorld.render(delta);
       // gameUI.render();
    }

    @Override
    public void resize(int width, int height) {
       // gameUI.resize(width, height);
        gameWorld.resize(width, height);
    }

    @Override
    public void dispose() {
        gameWorld.dispose();
       // gameUI.dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }
}