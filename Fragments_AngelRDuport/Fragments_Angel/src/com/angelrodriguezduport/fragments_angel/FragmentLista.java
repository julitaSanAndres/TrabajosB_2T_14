package com.angelrodriguezduport.fragments_angel;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentLista extends Fragment {

	private Correo[] correos = new Correo[] {
			new Correo("Remitente 1", "Asunto del correo 1",
					"Este es el texto del correo número 1"),
			new Correo("Remitente 2", "Asunto del correo 2",
					"Este es el texto del correo número 2"),
			new Correo("Remitente 3", "Asunto del correo 3",
					"Este es el texto del correo número 3"),
			new Correo("Remitente 4", "Asunto del correo 4",
					"Este es el texto del correo número 4"),
			new Correo("Remitente 5", "Asunto del correo 5",
					"Este es el texto del correo número 5"),
			new Correo("Remitente 6", "Asunto del correo 6",
					"Este es el texto del correo número 6"),
			new Correo("Remitente 7", "Asunto del correo 7",
					"Este es el texto del correo número 7"),
			new Correo("Remitente 8", "Asunto del correo 8",
					"Este es el texto del correo número 8") };;

	private ListView lVListadoCorreos;
	private CorreosListener listener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_lista, container, false);
	}

	public void onActivityCreated(Bundle state) {
		super.onActivityCreated(state);
		lVListadoCorreos = (ListView) getView().findViewById(R.id.listView1);
		lVListadoCorreos.setAdapter(new MyAdapterCorreo(this,correos));

		lVListadoCorreos.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> list, View view, int pos,
					long id) {
				if (listener != null) {
					listener.onCorreoSeleccionado((Correo) lVListadoCorreos
							.getAdapter().getItem(pos));
				}
			}
		});
	}

	public interface CorreosListener {
		void onCorreoSeleccionado(Correo c);
	}

	public void setCorreosListener(CorreosListener listener) {
		this.listener = listener;
	}
}