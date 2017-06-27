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
import com.game.flower.ArrangementWorld;
import com.game.flower.Core;
import com.game.flower.UI.NavigationDrawer;
import com.game.flower.UI.controllerWidgets.RotationWidget;
import com.game.flower.managers.Listener;
import com.game.flower.managers.MProduct;
import com.game.flower.managers.ProductGesture;
import com.game.flower.managers.ProductoFactory;
import com.game.flower.managers.ResourceManager;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.Gdx.input;

/**
 * Created by LuiferPc on 13/5/2017.
 */

public class MakerOrderScreen extends BaseScreen implements Listener, EventListener {
    Table contenedorPSeleccionados;
    List<MProducto> vasijas;
    List<MProducto> flores;
    List<MProducto> productosSeleccionados;
    Listener listener;
    MProducto seleccionado;
    Stage stageSeleccionado;
    RotationWidget circuloVicioso;

    public MakerOrderScreen(Core game, int id)
    {
        super(game, id);
        seleccionado=null;
    }

    @Override
    public void show() {
        if(circuloVicioso==null) {
            stage = new Stage(new StretchViewport(1080, 1920));
            Drawable drawable = new TextureRegionDrawable(new TextureRegion((Texture) ResourceManager.Instance().get("screens/makeaorder/background.jpg")));
            Image fondo=new Image(drawable);
            fondo.setPosition(-1080,0);
            fondo.setSize(1080*2,1920);
            stage.addActor(fondo);
            stageSeleccionado = new Stage(new StretchViewport(1080, 1920));
            circuloVicioso = new RotationWidget(this);
            circuloVicioso.addToStage(stage, -720, 500);


            inicializarProductos();
            inicializarContenedor();
            initNavigationDrawer();
        }
        input.setInputProcessor(stage);

    }

    private void inicializarContenedor() {
        contenedorPSeleccionados = new Table();
        Table floresTablas=new Table();
        Table vasijasTablas = new Table();
        for (int i=0;i<this.flores.size();i++)
        {
            floresTablas.add(this.flores.get(i)).space(20).row();
        }
        for(int i=0;i<this.vasijas.size();i++)
        {
            vasijasTablas.add(this.vasijas.get(i)).space(20).row();
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

        contenedorPSeleccionados.add(sideMenu).spaceBottom(25).row();
        ScrollPane scrollSeleccionados=new ScrollPane(contenedorPSeleccionados);
        scrollSeleccionados.setSize(360,1920);

        Table contenedorTotal = new Table();
        contenedorTotal.setFillParent(true);
        contenedorTotal.top();
        contenedorTotal.setWidth(1080);
        contenedorTotal.setHeight(1920);
        contenedorTotal.add(scrollSeleccionados).left().top();
        contenedorTotal.add(scrollflores).center().top();
        contenedorTotal.add(scrollvasijas).right().top();
        contenedorTotal.setFillParent(true);




        stage.addActor(contenedorTotal);


    }

    private void inicializarProductos() {
        List<MProducto> productos=ProductoFactory.Instance().productosGenerator(this);
        flores=new ArrayList<>();
        vasijas=new ArrayList<>();
        for(MProducto p:productos)
        {
            if(p.isEsFlor())
            {
                flores.add(p);
            }else {
                vasijas.add(p);
            }
        }
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
        circuloVicioso.controlarAccion();
        stage.draw();

        if(input.isTouched() && seleccionado!=null)
        {
            int x = (int) (input.getX()-seleccionado.getWidth()/2);
            int y = Gdx.graphics.getHeight() - input.getY();
            seleccionado.setPosition(x,y);
            stageSeleccionado.act();
            stageSeleccionado.draw();
        }else if(!input.isTouched() && seleccionado!=null) {
            seleccionado=null;
            stageSeleccionado.clear();
        }

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
    public void onTap(boolean esDeSeleccion,int id,int recurso) {
        //todo mejorar la manera de buscar
        if(!esDeSeleccion){
           for (int i=0;i<flores.size();i++)
           {

               if(flores.get(i).getId()==id && !estaEnlaLista(id))
               {
                   String flower="flowers/"+recurso+"/"+recurso+".png";
                   Drawable drawableIcon = new TextureRegionDrawable(new TextureRegion((Texture) ResourceManager.Instance().get(flower)));
                   MProducto mProducto=new MProducto(drawableIcon);
                   mProducto.setId(id);
                   mProducto.setName(flores.get(i).getName());
                   mProducto.setPrice(flores.get(i).getPrice());
                   mProducto.setRecurso(recurso);
                   mProducto.addListener(new ProductGesture(true,this,id,recurso));
                   productosSeleccionados.add(mProducto);
                   contenedorPSeleccionados.add(mProducto).row();
               }
           }
            for (int i=0;i<vasijas.size();i++)
            {

                if(vasijas.get(i).getId()==id && !estaEnlaLista(id))
                {
                    String flower="pot/"+recurso+"/"+recurso+".png";
                    Drawable drawableIcon = new TextureRegionDrawable(new TextureRegion((Texture) ResourceManager.Instance().get(flower)));
                    MProducto mProducto=new MProducto(drawableIcon);
                    mProducto.setId(id);
                    mProducto.setName(flores.get(i).getName());
                    mProducto.setPrice(flores.get(i).getPrice());
                    mProducto.setEsFlor(false);
                    mProducto.setRecurso(recurso);
                    mProducto.addListener(new ProductGesture(true,this,id,recurso));
                    productosSeleccionados.add(mProducto);
                    contenedorPSeleccionados.add(mProducto).row();
                }
            }
        }
    }

    @Override
    public void onTouchDown(boolean esDeSeleccion, int id, int recurso,float x,float y) {
        if(esDeSeleccion && seleccionado==null)
        {
            MProducto mProduct=getProduct(id);
            String flower=mProduct.isEsFlorToStr();
            flower+="/"+recurso+"/"+recurso+".png";
            Drawable drawableIcon = new TextureRegionDrawable(new TextureRegion((Texture) ResourceManager.Instance().get(flower)));
            seleccionado=new MProducto(drawableIcon);
            seleccionado.setEsFlor(mProduct.isEsFlor());
            seleccionado.setName(mProduct.getName());
            seleccionado.setPrice(mProduct.getPrice());
            seleccionado.setRecurso(recurso);
            seleccionado.setPosition(x,y);
            seleccionado.setScaleX(1.9f);
            seleccionado.setScaleY(1.9f);
            seleccionado.setId(id);
            stageSeleccionado.addActor(seleccionado);
        }
    }

    private MProducto getProduct(int id) {
        for(MProducto flor:flores)
        {
            if(flor.getId()==id){
                return flor;
            }
        }
        for(MProducto vasija:vasijas)
        {
            if(vasija.getId()==id){
                return vasija;
            }
        }
        return null;
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

    @Override
    public boolean handle(Event event) {
        if(this.seleccionado!=null)
        {
            game.setScreen(BaseScreen.FLOWER_ARRANGEMENT_SCREEN);
            ArrangementWorld arrangementWorld=((FlowerArrangementScreen)game.currentScreen).arrangementWorld;
            if(arrangementWorld==null) {
                ((FlowerArrangementScreen) game.currentScreen).initAll();
            }
            ((FlowerArrangementScreen) game.currentScreen).setProduct(seleccionado);
            game.currentScreen.show();
            return true;
        }
        return false;
    }
}
