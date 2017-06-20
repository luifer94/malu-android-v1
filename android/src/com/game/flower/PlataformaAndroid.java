package com.game.flower;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by LuiferPc on 9/4/2017.
 */

public class PlataformaAndroid implements Plataforma {
    Activity activity;
    public PlataformaAndroid(Activity activity) {
        this.activity=activity;
    }

    @Override
    public void mostrarMensaje(final String s) {
        ((AndroidLauncher)activity).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(((AndroidLauncher)activity).getApplicationContext(),s,Toast.LENGTH_SHORT).show();
            }
        });

    }
}
