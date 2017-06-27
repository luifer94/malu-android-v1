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

    public final Vector3 center = new Vector3();
    public  Vector3 dimensions = new Vector3();
    public  float radius;
    public   BoundingBox bounds = new BoundingBox();

    public ModelComponent(Model model, float x, float y, float z) {
        this.matrix4 = new Matrix4();
        this.model = model;
        this.instance = new ModelInstance(model,x,y,z);

    }

    public void updateDimensions() {
        instance.calculateBoundingBox(bounds);
        bounds.getCenter(center);
        bounds.getDimensions(dimensions);
        radius = dimensions.len() / 2f;
    }

    public void setPosition(float x,float y,float z)
    {
        Vector3 p=getPosition();
        p.y=y;
        instance.transform.setTranslation(p);

    }

    public void scalar(float scalar)
    {
        instance.transform.scl(scalar);
    }
    public Vector3 getPosition()
    {
        Vector3 p=new Vector3();
        instance.transform.getTranslation(p);
        return p;
    }
}