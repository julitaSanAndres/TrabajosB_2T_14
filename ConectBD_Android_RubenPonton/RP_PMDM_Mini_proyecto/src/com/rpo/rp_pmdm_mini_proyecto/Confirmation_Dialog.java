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
public class Confirmation_Dialog extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		return new AlertDialog.Builder(getActivity())
				.setMessage(getString(R.string.str_Are_you_sure))
				.setTitle(getString(R.string.str_Exit))
				.setPositiveButton(getString(R.string.str_OK),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								/*
								 * Declaro un nuevo intent como si fuera a ser
								 * la MAIN activity Le asigno la categoría de
								 * HOME Le asigno la bandera de nueva tarea
								 * Inicio la nueva actividad, que no contiene
								 * nada y para terminar la finalizo
								 */
								Intent startMain = new Intent(
										Intent.ACTION_MAIN);
								startMain.addCategory(Intent.CATEGORY_HOME);
								startMain
										.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
												| Intent.FLAG_ACTIVITY_NEW_TASK);

								startActivity(startMain);
								dialog.cancel();
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
