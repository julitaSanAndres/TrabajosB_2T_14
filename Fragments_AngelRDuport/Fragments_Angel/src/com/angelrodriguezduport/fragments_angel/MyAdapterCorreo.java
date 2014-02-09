package com.angelrodriguezduport.fragments_angel;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyAdapterCorreo extends ArrayAdapter<Correo> {

	Activity context;
	Correo[] correos;

	MyAdapterCorreo(Fragment context, Correo[] correos) {
		super(context.getActivity(), R.layout.listitem_correo, correos);
		this.context = context.getActivity();
		this.correos = correos;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View item = inflater.inflate(R.layout.listitem_correo, null);

		TextView remitente = (TextView) item
				.findViewById(R.id.textViewRemitente);
		remitente.setText(correos[position].getRemitente());

		TextView lblAsunto = (TextView) item
				.findViewById(R.id.textViewAsunto);
		lblAsunto.setText(correos[position].getAsunto());

		return (item);
	}

}
