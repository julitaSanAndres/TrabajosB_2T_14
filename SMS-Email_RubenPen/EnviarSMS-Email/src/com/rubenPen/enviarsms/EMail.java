package com.rubenPen.enviarsms;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.app.Activity;
import android.content.Intent;

public class EMail extends Activity {

	private EditText destino;
	private EditText asunto;
	private EditText mensaje;
	private RadioGroup archivosAdjuntos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email);

		destino = (EditText) findViewById(R.id.mailAddress);
		asunto = (EditText) findViewById(R.id.asunto);
		mensaje = (EditText) findViewById(R.id.cuerpo);
		archivosAdjuntos = (RadioGroup) findViewById(R.id.radioGroup1);

	}

	public void enviar(View v) {
		// Obtenemos los datos
		String[] to = { destino.getText().toString() };
		String issue = asunto.getText().toString();
		String body = mensaje.getText().toString();

		// Creamos el intent que enviar� el mensaje y le a�adimos los datos
		Intent intent = new Intent(Intent.ACTION_SEND);

		// A�adimos la direcci�n de destino
		intent.putExtra(Intent.EXTRA_EMAIL, to);
		// A�adimos el asunto
		intent.putExtra(Intent.EXTRA_SUBJECT, issue);
		// A�adimos el mensaje
		intent.putExtra(Intent.EXTRA_TEXT, body);

		/* A�adimos un archivo adjunto u otro en funci�n de qu� radiobutton est�
		 marcado */
		if (archivosAdjuntos.getCheckedRadioButtonId() == R.id.radio0) {

			intent.putExtra(
					Intent.EXTRA_STREAM,
					Uri.parse("android.resource://" + getPackageName() + "/"
							+ R.drawable.zorro));

			// establecemos el tipo del intent
			intent.setType("multipart/mixed");

		} else {

			intent.putExtra(Intent.EXTRA_STREAM,
					Uri.parse("file://raw//hola.zip"));

			// establecemos el tipo del intent
			intent.setType("application/zip");
		}

		/* Creamos un chooser, que ser� el que pregunte realmente qu� aplicaciones saben
		 * manejar el tipo de intent indicado con el m�todo setType()
		 */
		startActivity(Intent.createChooser(intent, "E-Mail"));
	}

}
