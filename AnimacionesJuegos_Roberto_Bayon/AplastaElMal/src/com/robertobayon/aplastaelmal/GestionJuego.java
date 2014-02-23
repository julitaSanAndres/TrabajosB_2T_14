package com.robertobayon.aplastaelmal;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.Window;

public class GestionJuego extends FragmentActivity {

	private static int record;
	private static SharedPreferences records;
	private static AndroidAudio audio;
	public static AudioJuego audioJuego;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		audio = new AndroidAudio(this);
		audioJuego = new AudioJuego(audio);

		record = 9999;
		records = getSharedPreferences("datos", Context.MODE_PRIVATE);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(new GameView(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gestion_juego, menu);
		return true;
	}

	public static int leeRecord() {
		int recuRecord = records.getInt("record", record);
		return recuRecord;
	}

	public static void grabaRecord(int tiempo) {
		Editor editor = records.edit();
		record = tiempo;
		editor.putInt("record", record);
		editor.commit();

	}


}
