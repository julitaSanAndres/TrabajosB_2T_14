package com.angelrodriguezduport.fragments_angel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentDetalle extends Fragment {
	private TextView tVDetalle;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_detalle, container, false);
	}

	public void mostrarDetalle(String texto) {
		tVDetalle = (TextView) getView().findViewById(
				R.id.textViewFragmentDetalle);

		tVDetalle.setText(texto);
	}

}
