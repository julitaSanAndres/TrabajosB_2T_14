package com.angelrodriguezduport.fragments_angel;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tvVecesIniciadaApp;
	private Button bLanzaActivity;
	private SharedPreferences preferencias;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvVecesIniciadaApp = (TextView) findViewById(R.id.textViewVecesInicioApp);
		bLanzaActivity = (Button) findViewById(R.id.buttonLanzaActivity);
		preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);

		// Declaro un SharePreferences para llevar la cuenta de las veces que se
		// ha ejecutado la app
		Integer veces = preferencias.getInt("veces", 1);
		tvVecesIniciadaApp.setText("Es la " + veces
				+ " vez que se ejecuta la App");
		Editor editor = preferencias.edit();
		editor.putInt("veces", (preferencias.getInt("veces", 1) + 1));
		editor.commit();

	}

	public void onClickLanzar(View v) {
		Intent intent = new Intent(this, ActivityFragments.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
