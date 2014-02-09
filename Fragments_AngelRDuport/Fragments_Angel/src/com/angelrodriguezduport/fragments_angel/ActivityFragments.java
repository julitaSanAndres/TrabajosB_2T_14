package com.angelrodriguezduport.fragments_angel;
import com.angelrodriguezduport.fragments_angel.FragmentLista.CorreosListener;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

public class ActivityFragments extends FragmentActivity implements CorreosListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);

		FragmentLista frgLista = (FragmentLista) getSupportFragmentManager()
				.findFragmentById(R.id.fragment1);
		frgLista.setCorreosListener(this);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onCorreoSeleccionado(Correo correo) {
		boolean siExisteElFragmentDetalle = (getSupportFragmentManager().findFragmentById(
				R.id.fragment2) != null);

		if (siExisteElFragmentDetalle) {
			((FragmentDetalle) getSupportFragmentManager().findFragmentById(
					R.id.fragment2)).mostrarDetalle(correo.getContenido());
		} else {
			Intent i = new Intent(this, ActivityDetalle.class);
			i.putExtra("contenido", correo.getContenido());
			startActivity(i);
		}
	}

}
