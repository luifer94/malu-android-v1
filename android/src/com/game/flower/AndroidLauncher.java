package com.game.flower;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.game.flower.Core;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		PlataformaAndroid plataforma=new PlataformaAndroid(this);
		config.useImmersiveMode = true;
		config.hideStatusBar = true;
		putFullscreen();

		Core game=new Core(plataforma);
		View MaluView = initializeForView(game, config);
		RelativeLayout layout = new RelativeLayout(this);
		layout.addView(MaluView);
		setContentView(layout);
	}
	private void putFullscreen() {
		try {
			requestWindowFeature(Window.FEATURE_NO_TITLE);
		} catch (Exception ex) {
			log("AndroidApplication", "Cannot put FEATURE_NO_TITLE", ex);
		}
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
	}
}
