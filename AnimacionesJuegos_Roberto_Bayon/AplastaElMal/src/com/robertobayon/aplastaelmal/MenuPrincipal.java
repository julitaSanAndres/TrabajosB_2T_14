package com.robertobayon.aplastaelmal;

import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.view.Menu;
import android.view.View;
import android.view.Window;

public class MenuPrincipal extends Activity {
	private AssetManager assets;
	private AndroidMusic musica;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_menu_principal);
		assets = this.getAssets();
		AssetFileDescriptor assetDescriptor;
		try {
			assetDescriptor = assets.openFd("inter.mid");
			musica = new AndroidMusic(assetDescriptor);
			musica.play();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		if(!musica.isPlaying()){
			AssetFileDescriptor assetDescriptor;
			try {
				assetDescriptor = assets.openFd("inter.mid");
				musica = new AndroidMusic(assetDescriptor);
				musica.play();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		musica.stop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_principal, menu);
		return true;
	}

	public void jugar(View v) {
		musica.stop();
		Intent inten = new Intent(this, GestionJuego.class);
		startActivity(inten);
	}

	public void instrucciones(View v) {
		//musica.stop();
		Intent inten = new Intent(this, Instrucciones.class);
		startActivity(inten);
	}

	public void salir(View v) {
		musica.stop();
		finish();
	}

}
