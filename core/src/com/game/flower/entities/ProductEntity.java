package com.game.flower.entities;

import com.badlogic.ashley.core.Entity;
import com.game.flower.Actor.MProducto;

/**
 * Created by LuiferPc on 24/6/2017.
 */

public class ProductEntity extends Entity {
    public MProducto mProducto;

    public ProductEntity(MProducto mProducto) {
        super();
        this.mProducto = mProducto;
    }
}
