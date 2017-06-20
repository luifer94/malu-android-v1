package com.game.flower.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.game.flower.Actor.MProducto;
import com.game.flower.Actor.SideMenu;
import com.game.flower.Core;
import com.game.flower.UI.NavigationDrawer;
import com.game.flower.managers.Listener;
import com.game.flower.managers.ProductoFactory;
import com.game.flower.managers.ResourceManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LuiferPc on 13/5/2017.
 */

public class NavigationDrawerScreenExample extends BaseScreen implements Listener{
    Table contenedorPSeleccionados;
    ScrollPane scrollpane;
    List<MProducto> vasijas;
    List<MProducto> flores;
    List<MProducto> productosSeleccionados;
    Listener listener;

    Table containerr;
    public NavigationDrawerScreenExample(Core game, int id) {
        super(game, id);
    }

    @Override
    public void show() {
        stage = new Stage(new StretchViewport(1080, 1920));
        inicializarProductos();
        inicializarContenedor();

      /*  icon.addListener(new ClickListener() {
            private int clicked = 0;
            public void clicked(InputEvent event, float x, float y) {
                if (clicked % 2 == 0) {
                    clicked++;
                    Drawable drawableIconx = new TextureRegionDrawable(new TextureRegion((Texture) ResourceManager.Instance().get("flowers/1/1.png")));
                    ImageButton iconx = new ImageButton(drawableIconx);
                    iconx.setSize(360,200);
                    containerr.add(iconx).row();
                    NavigationDrawer.show(true);

                } else {
                    clicked++;
                    NavigationDrawer.show(false);
                }
            }
        });*/

        initNavigationDrawer();

        Gdx.input.setInputProcessor(stage);

    }

    private void inicializarContenedor() {
        contenedorPSeleccionados = new Table();
        Table floresTablas=new Table();
        Table vasijasTablas = new Table();
        for (int i=0;i<this.flores.size();i++)
        {
            floresTablas.add(this.flores.get(i)).row();
        }
        for(int i=0;i<this.vasijas.size();i++)
        {
            vasijasTablas.add(this.vasijas.get(i)).row();
        }

        ScrollPane scrollflores=new ScrollPane(floresTablas);
        scrollflores.setSize(360,1920);
        ScrollPane scrollvasijas=new ScrollPane(vasijasTablas);
        scrollvasijas.setSize(360,1920);

        SideMenu sideMenu=new SideMenu(new TextureRegionDrawable(new TextureRegion((Texture) ResourceManager.Instance().get("screens/makeaorder/side_menu.png"))));
        sideMenu.addListener(new ClickListener(){
            private int clicked = 0;
            public void clicked(InputEvent event, float x, float y) {
                if (clicked % 2 == 0) {
                    clicked++;
                    NavigationDrawer.show(true);

                } else {
                    clicked++;
                    NavigationDrawer.show(false);
                }
            }
        });

        contenedorPSeleccionados.add(sideMenu).row();
        ScrollPane scrollSeleccionados=new ScrollPane(contenedorPSeleccionados);
        scrollSeleccionados.setSize(360,1920);

        Table contenedorTotal = new Table();
        contenedorTotal.setWidth(1080);
        contenedorTotal.setHeight(1920);
        contenedorTotal.add(scrollSeleccionados).left().top();
        contenedorTotal.add(scrollflores).center().top();
        contenedorTotal.add(scrollvasijas).right().top();



        stage.addActor(contenedorTotal);


    }

    private void inicializarProductos() {
        flores= ProductoFactory.dummyProductoGenerator(this);
        vasijas= ProductoFactory.dummyProductoGenerator(this);
        productosSeleccionados=new ArrayList<>();
    }

    private void initNavigationDrawer() {
        NavigationDrawer.NavigationDrawerListener listener = new NavigationDrawer.NavigationDrawerListener() {

            @Override
            public void onStart() {
                System.out.println("onStart");
            }

            @Override
            public void onRun() {
                System.out.println("onRun");
            }

            @Override
            public void onFinish(float camX) {
                System.out.println("onFinish: " + camX);
            }
        };

        // You must be initialize NavigationDrawer Firstly
        NavigationDrawer.initialize(stage, listener);
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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

    @Override
    public void dispose() {

    }

    @Override
    public void onTap(int id,int recurso) {
        //todo mejorar la manera de buscar
       for (int i=0;i<flores.size();i++)
       {

           if(flores.get(i).getId()==id && !estaEnlaLista(id))
           {
               String flower="flowers/"+recurso+"/"+recurso+".png";
               Drawable drawableIcon = new TextureRegionDrawable(new TextureRegion((Texture) ResourceManager.Instance().get(flower)));
               MProducto mProducto=new MProducto(drawableIcon);
               mProducto.setId(id);
               productosSeleccionados.add(mProducto);
               contenedorPSeleccionados.add(mProducto).row();
           }
       }
    }

    private boolean estaEnlaLista(int id) {
        for (int i=0;i<this.productosSeleccionados.size();i++)
        {
            if(this.productosSeleccionados.get(i).getId()==id)
            {
                 return true;
            }
        }
        return false;
    }

}
