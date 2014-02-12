package com.fran.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Cliente extends Activity {

	private EditText envioRecibo, mensaje;

	private TCPCliente cliente;
	private DataInputStream flujoEntrada;
	private DataOutputStream flujoSalida;
	private static String textoEnviado, textoRecibido;
	private boolean envio;
	private MyTask hilo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cliente);

		envioRecibo = (EditText) findViewById(R.id.mensajesEnvRec);
		mensaje = (EditText) findViewById(R.id.eTMensaje);

		envioRecibo.setFocusable(false); // Para que no se pueda escribir en el
											// editText de resultados
		textoEnviado = "";
		envio = false;
		hilo = new MyTask();
		hilo.execute();

	}

	// Este método se encargara de informar al hilo cuando se quiere enviar un
	// mensaje al servidor
	public void envio(View v) {
		envio = true;
	}

	

	// En este hilo se realizara la comunicacion con el servidor
	class MyTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {

			cliente = new TCPCliente() {

				@Override
				public void transferencia(DataInputStream flujoI,
						DataOutputStream flujoO) {
					// El hilo se ejecutara hasta que el cliente envie fin
					do {
						// si se pulsa el boton enviar
						if (envio) {
							try {
								flujoEntrada = new DataInputStream(flujoI);
								flujoSalida = new DataOutputStream(flujoO);
								textoEnviado = mensaje.getText().toString();

								flujoSalida.writeUTF(textoEnviado);

								textoRecibido = flujoEntrada.readUTF();

								publishProgress();
								envio = false;
							} catch (IOException ex) {

							}
						} else {
							// Mientras no haya nada que enviar el hilo se
							// quedara dentro
							// de este bucle
							while (!envio) {

							}
						}

					} while (!textoEnviado.equalsIgnoreCase("Fin"));
				}
			};

			return null;
		}

		@Override
		protected void onProgressUpdate(Void... value) {
			envioRecibo.setText(envioRecibo.getText().toString() + "Dices: "
					+ textoEnviado);
			envioRecibo.setText(envioRecibo.getText().toString()
					+ "\nServidor: " + textoRecibido + "\n");
			mensaje.setText("");
		}

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cliente, menu);
		return true;
	}

}
