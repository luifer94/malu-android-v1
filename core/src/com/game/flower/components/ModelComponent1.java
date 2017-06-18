package com.game.flower.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

/**
 * Created by LuiferPc on 25/5/2017.
 */

public class ModelComponent1 implements Component {
    public Model model;
    public ModelInstance instance;
    public final Vector3 center = new Vector3();
    public final Vector3 dimensions = new Vector3();
    public final float radius;

    private final static BoundingBox bounds = new BoundingBox();
    public ModelComponent1(Model model, float x, float y, float z) {
        this.model = model;
        this.instance = new ModelInstance(model, new Matrix4().setToTranslation(x, y, z));
        instance.calculateBoundingBox(bounds);
        bounds.getCenter(center);
        bounds.getDimensions(dimensions);
        radius = dimensions.len() / 2f;
    }
}