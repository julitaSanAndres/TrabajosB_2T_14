package com.rpo.rp_pmdm_mini_proyecto;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MovementsListCustomAdapter extends ArrayAdapter<Movement> {
	private final Activity context;
	private final ArrayList<Movement> movimientos;
	private NumberFormat formatoImporte;
	private DateFormat formatoFecha;

	public MovementsListCustomAdapter(Activity context, int textViewResourceId,
			ArrayList<Movement> movimientos) {
		super(context, textViewResourceId, movimientos);
		this.context = context;
		this.movimientos = movimientos;
		formatoImporte = NumberFormat.getCurrencyInstance();
		formatoFecha = DateFormat.getDateInstance();

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Recojo la vista
		View vistaDetalle = convertView;

		ViewHolder holder;

		// Compruebo si la vista fue creada anteriormente
		if (vistaDetalle == null) {
			// Declaro un interpretador para el layout
			LayoutInflater inflater = context.getLayoutInflater();
			// Lo activo para que genere la vista
			vistaDetalle = inflater.inflate(R.layout.movements_list, null);
			// Creo un view holder para rescatarlo cuando sea necesario
			holder = new ViewHolder();

			// Asigno los campos del holder a los recursos del layout
			holder.tv_date_detail = (TextView) vistaDetalle.findViewById(R.id.tv_date_detail);
			holder.tv_description_detail = (TextView) vistaDetalle
					.findViewById(R.id.tv_description_detail);
			holder.tv_amount_detail = (TextView) vistaDetalle
					.findViewById(R.id.tv_amount_detail);

			// Asigno una etiquta a la vista
			vistaDetalle.setTag(holder);
		} else {
			// Recupero el ViewHolder si ya existía
			holder = (ViewHolder) vistaDetalle.getTag();
		}

		// Asigno el color de fondo de cada vista en función de la posición
		if (position % 2 == 0) {
			vistaDetalle.setBackgroundColor(context.getResources().getColor(
					R.color.verde_claro_lista));
//			holder.tv_date_detail.setTextColor(context.getResources().getColor(
//					R.color.Blanco_nuclear));
//			holder.tv_description_detail.setTextColor(context.getResources().getColor(
//					R.color.Blanco_nuclear));
//			holder.tv_amount_detail.setTextColor(context.getResources().getColor(
//					R.color.Blanco_nuclear));
		} else {
			vistaDetalle.setBackgroundColor(context.getResources().getColor(
					R.color.verde_oscuro_lista));
//			holder.tv_date_detail.setTextColor(context.getResources().getColor(
//					R.color.Black));
//			holder.tv_description_detail.setTextColor(context.getResources().getColor(
//					R.color.Black));
//			holder.tv_amount_detail.setTextColor(context.getResources().getColor(
//					R.color.Black));

		}
		// Aplico los valores adecuados a cada campo
		holder.tv_date_detail.setText(formatoFecha.format(movimientos.get(position).getDate()));
		holder.tv_description_detail.setText(movimientos.get(position).getDescription());
		holder.tv_amount_detail.setText(formatoImporte.format(movimientos.get(position).getAmount()));

		return vistaDetalle;
	}

	// Declaro los componentes de la vista
	static class ViewHolder {
		public TextView tv_date_detail;
		public TextView tv_description_detail;
		public TextView tv_amount_detail;

	}

}
