package com.rpo.rp_pmdm_mini_proyecto;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Clase que lanza un diálogo de confirmación para asegurarse de que el usuario
 * quiere salir de la aplicación
 * 
 */
public class Close_Session_Dialog extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		return new AlertDialog.Builder(getActivity())
				.setMessage(getString(R.string.str_Are_you_sure))
				.setTitle(getString(R.string.str_Close_session))
				.setPositiveButton(getString(R.string.str_OK),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

								dialog.cancel();
								Intent i = new Intent(getActivity(), Login.class);
								startActivity(i);
								getActivity().finish();
							}
						})
				.setNegativeButton(getString(R.string.str_Cancel),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();

							}

						}).create();

	}
}
