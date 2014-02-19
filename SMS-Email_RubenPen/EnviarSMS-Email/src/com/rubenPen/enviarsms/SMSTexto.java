package com.rubenPen.enviarsms;

import java.util.ArrayList;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;

public class SMSTexto extends Activity {

	private EditText destino;
	private EditText mensaje;
	private static final int LONGITUD = 160;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smstexto);

		destino = (EditText) findViewById(R.id.destino);
		mensaje = (EditText) findViewById(R.id.mensaje);

	}

	// método para enviar el mensaje
	public void enviar(View v) {

		// Primero, se obtienen los valores del destinatario y del mensaje
		String numero = destino.getText().toString();
		String sms = mensaje.getText().toString();

		/* Instanciamos un objeto SMSManager, que será el que nos permita mandar el
		 * mensaje. La clase SMSManager no tiene constructor, y se utiliza el
		 * método estático getDefault() */
		SmsManager manager = SmsManager.getDefault();

		/* Debemos comprobar si la longitud del mensaje supera los 160
		 * caracteres y en función de eso llamaremos a un método o a otro*/
		try {

			if (sms.length() > LONGITUD) {
				ArrayList<String> partes = dividirMensaje(sms);
				manager.sendMultipartTextMessage(numero, null, partes, null,
						null);
			} else {
				manager.sendTextMessage(numero, null, sms, null, null);
			}

			/* los métodos lanzan la excepción IllegalArgumentException si los
			 * campos destinatario y/o mensaje están vacíos*/
		} catch (IllegalArgumentException ex) {
			Toast.makeText(this, "Debes introducir el número y un mensaje",
					Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * Método para dividir el mensaje. No importa por dónde separe, ya que al
	 * destinatario le llegará como un único SMS
	 * 
	 * @param mensaje
	 *            El texto para dividir
	 * @return un arrayList con las partes en las que se ha dividido el mensaje
	 */
	public ArrayList<String> dividirMensaje(String mensaje) {
		ArrayList<String> partes = new ArrayList();
		int numPartes = mensaje.length() % LONGITUD;
		int inicio = 0;

		for (int i = 0; i < numPartes; i++) {
			inicio = i * LONGITUD;
			partes.add(mensaje.substring(inicio, inicio + LONGITUD));
		}

		return partes;
	}
}
