package com.game.flower.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

/**
 * Created by LuiferPc on 5/4/2017.
 */

public class ModelComponent implements Component {
    public Model model;
    public ModelInstance instance;
    public Matrix4 matrix4;

    public ModelComponent(Model model, float x, float y, float z) {
        this.matrix4 = new Matrix4();
        this.model = model;
        this.instance = new ModelInstance(model,x,y,z);
    }


}