package com.rpo.rp_pmdm_mini_proyecto;

import java.text.NumberFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AccountListCustomAdapter extends ArrayAdapter<Account> {
	private final Activity context;
	private final ArrayList<Account> cuentas;
	private NumberFormat formatoImporte;

	public AccountListCustomAdapter(Activity context, int textViewResourceId,
			ArrayList<Account> cuentas) {
		super(context, textViewResourceId, cuentas);
		this.context = context;
		this.cuentas = cuentas;
		formatoImporte = NumberFormat.getCurrencyInstance();

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
			vistaDetalle = inflater.inflate(R.layout.account_list, null);
			// Creo un view holder para rescatarlo cuando sea necesario
			holder = new ViewHolder();

			// Asigno los campos del holder a los recursos del layout
			holder.tv_type = (TextView) vistaDetalle.findViewById(R.id.tv_Type);
			holder.tv_iban = (TextView) vistaDetalle
					.findViewById(R.id.tv_iban_list);
			holder.tv_balance = (TextView) vistaDetalle
					.findViewById(R.id.tv_balance_list);

			// Asigno una etiquta a la vista
			vistaDetalle.setTag(holder);
		} else {
			// Recupero el ViewHolder si ya existía
			holder = (ViewHolder) vistaDetalle.getTag();
		}

		// Asigno el color de fondo de cada vista en función de la posición
		if (position % 2 == 0) {
			vistaDetalle.setBackgroundColor(context.getResources().getColor(
					R.color.rojo_claro_lista));
//			holder.tv_type.setTextColor(context.getResources().getColor(
//					R.color.Black));
//			holder.tv_iban.setTextColor(context.getResources().getColor(
//					R.color.Black));
//			holder.tv_balance.setTextColor(context.getResources().getColor(
//					R.color.Black));
		} else {
			vistaDetalle.setBackgroundColor(context.getResources().getColor(
					R.color.rojo_oscuro_lista));
//			holder.tv_type.setTextColor(context.getResources().getColor(
//					R.color.Blanco_nuclear));
//			holder.tv_iban.setTextColor(context.getResources().getColor(
//					R.color.Blanco_nuclear));
//			holder.tv_balance.setTextColor(context.getResources().getColor(
//					R.color.Blanco_nuclear));

		}
		// Aplico los valores adecuados a cada campo
		holder.tv_type.setText(cuentas.get(position).getType());
		holder.tv_iban.setText(
				cuentas.get(position).getIban().substring(0,4) + " " +
				cuentas.get(position).getIban().substring(4,8) + " " +
				cuentas.get(position).getIban().substring(8,10) + " " +
				cuentas.get(position).getIban().substring(10,20)	
				
				);
		holder.tv_balance.setText(formatoImporte.format(cuentas
				.get(position).getBalance()));

		return vistaDetalle;
	}

	// Declaro los componentes de la vista
	static class ViewHolder {
		public TextView tv_type;
		public TextView tv_iban;
		public TextView tv_balance;

	}

}
