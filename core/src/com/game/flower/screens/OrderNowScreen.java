package com.game.flower.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.game.flower.Actor.MProducto;
import com.game.flower.Contants;
import com.game.flower.Core;
import com.game.flower.entities.ProductEntity;
import com.game.flower.managers.MProduct;
import com.game.flower.managers.ProductJson;
import com.game.flower.managers.ProductJsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LuiferPc on 26/6/2017.
 */

public class OrderNowScreen extends BaseScreen implements EventListener {
    List<MProducto> productsOrder;
    TextField nombreField,mensajeField,nitField;
    Table table;
    Map<Integer,Integer> productos;
    boolean pulsado;
    public OrderNowScreen(Core game, int id,List<MProducto> productsOrder) {
        super(game, id);
        this.productsOrder=productsOrder;
        stage = new Stage();

        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        Skin skin2 = new Skin(Gdx.files.internal("skin/uiskin.json"));
        Label nombrelabel = new Label("Nombre :", skin);
        Label detalleVenta=new Label(generarDetalle(),skin);
        detalleVenta.setFontScale(2);
        nombrelabel.setFontScale(3.5f);
        Label nitLabel = new Label("Nit:", skin);
        nitLabel.setFontScale((3.5f));
        TextField.TextFieldStyle textFieldStyle=skin2.get(TextField.TextFieldStyle.class);
        textFieldStyle.font.getData().scale((3.5f));
        nombreField = new TextField("", skin2);
        nitField = new TextField("", skin2);
        mensajeField = new TextField("", skin2);
        TextButton confirmar=new TextButton("Confirmar",skin2);
        confirmar.addListener(this);
        pulsado=false;


        table = new Table();
        table.setFillParent(true);
        table.center().top();
        table.setSize(stage.getWidth(),stage.getHeight());
        table.add(nombrelabel).width(450).height(300).spaceBottom(25);
        table.add(nombreField).width(600).height(300).spaceBottom(25);
        table.row();
        table.add(nitLabel).width(450).height(300).spaceBottom(25);
        table.add(nitField).width(600).height(300).spaceBottom(25);
        table.row();
        table.add(mensajeField).colspan(2).height(300).fill();
        table.row();
        table.add(detalleVenta).colspan(2).height(400).fill();
        table.row();
        table.add(confirmar).colspan(2).height(300).fill().bottom();
        table.debugAll();

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    private String generarDetalle() {
        String s="";
        productos = new HashMap<Integer, Integer>();
        for (MProducto mProducto: productsOrder)
        {

            if(!productos.containsKey(mProducto.getId()))
            {
                productos.put(mProducto.getId(),1);
            }else
            {
                productos.put(mProducto.getId(),productos.get(mProducto.getId())+1);
            }
        }
        float total=0;
        for (Map.Entry<Integer, Integer> entry : productos.entrySet()) {
            float precio=getPrecio(entry.getKey());
            s+="Producto id: "+entry.getKey()+" Cantidad: "+entry.getValue()+"  Precio: "+precio+"  Total: "+entry.getValue()*precio+" \n";
            total+=entry.getValue()*precio;
        }
        s+="    El total es : "+total;
        return s;
    }

    private float getPrecio(Integer key) {
        for (MProducto p:productsOrder)
        {
            if(p.getId()==key)
            {
                return p.getPrice();
            }
        }
        return 0;
    }


    @Override
    public void show() {
        super.show();

    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();

    }

    @Override
    public boolean handle(Event event) {
        if(pulsado){return false;}
        pulsado=true;
        final Map parameters = new HashMap();
        Json json = new Json();
        parameters.put("productos", toJson());
        parameters.put("nombre",nombreField.getText());
        parameters.put("nit",nitField.getText());
        parameters.put("mensaje",mensajeField.getText());

        Net.HttpRequest httpGet = new Net.HttpRequest(Net.HttpMethods.POST);
        httpGet.setUrl(Contants.GET_PRODUCT);
        json.setOutputType(JsonWriter.OutputType.json);
        String parametersString=json.toJson(parameters).toString();
        httpGet.setContent(parametersString);
        Gdx.net.sendHttpRequest (httpGet, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                int code=httpResponse.getStatus().getStatusCode();
                pulsado=false;
                try{
                    String respond=httpResponse.getResultAsString();
                    if(code==200 && respond.contains("OK")){

                        System.out.println(respond);
                        game.resetearMakeArrangement();
                        game.setScreen(BaseScreen.MAIN_MENU_SCREEN);
                        Gdx.input.setInputProcessor(((MainMenuScreen)game.currentScreen).stage);
                    }else if(code==200 && !respond.contains("OK")) {
                        mensajeField.setText(respond);
                    }else {
                        mensajeField.setText("Error code: "+code);
                    }
                }catch (Exception ex)
                {
                    System.out.println(ex);
                }
            }

            @Override
            public void failed(Throwable t) {
                System.out.println(t);
            }

            @Override
            public void cancelled() {
                System.out.println("Cancel");
            }
        });
        return true;
    }

    private List<ProductJson> toJson() {
        List<ProductJson> toJson=new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : productos.entrySet()) {
            ProductJson p=new ProductJson();
            p.id=entry.getKey();
            p.cantidad=entry.getValue();
            toJson.add(p);
        }
        return toJson;
    }
}
