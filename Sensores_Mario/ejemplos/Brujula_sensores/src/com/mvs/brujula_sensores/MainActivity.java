package com.mvs.brujula_sensores;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button mostrarTexto;
	Button mostrarGrafico;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mostrarTexto = (Button) findViewById(R.id.button_texto);
		mostrarGrafico = (Button) findViewById(R.id.button_grafico);

		// Boton que muestra informacion de la brujula en texo
		mostrarTexto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent i = new Intent();
				i.setClass(MainActivity.this, Texto_activity.class);

				startActivity(i);
			}
		});

		// Boton que muestra informacion de la brujula graficamente
		mostrarGrafico.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent i = new Intent();
				i.setClass(MainActivity.this, Grafico_activity.class);

				startActivity(i);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
