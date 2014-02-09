package com.rpo.rp_pmdm_mini_proyecto;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.rpo.library.Check_Server;
import com.rpo.library.JSONParser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Oper extends FragmentActivity {
	Button btn_check_account;
	Button btn_check_credit_card;
	Button btn_transfer_money;

	TextView tv_dni;
	TextView tv_full_name;
	TextView tv_address;
	TextView tv_city;

	private ProgressDialog pDialog;

	private int id_usuario;

	private Client client = null;

	private String URL_connect = Login.URL_server + "/phps/l_c_d.php";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_operations);

		btn_check_account = (Button) findViewById(R.id.btn_check_accounts);
		btn_check_credit_card = (Button) findViewById(R.id.btn_check_credit_cards);
		btn_transfer_money = (Button) findViewById(R.id.btn_transfer_money);

		tv_dni = (TextView) findViewById(R.id.tv_dni);
		tv_full_name = (TextView) findViewById(R.id.tv_full_name);
		tv_address = (TextView) findViewById(R.id.tv_address);
		tv_city = (TextView) findViewById(R.id.tv_pc_city);
		
		// Recupero los datos pasados en el intent
		Bundle bundle = this.getIntent().getExtras();
		// Guardo el ID del usuario
		id_usuario = bundle.getInt("user");

		// Lanzo el proceso de recuperación de datos del usuario
		new AsyncCheckUserData().execute();

		btn_check_account.setOnClickListener(new View.OnClickListener() {
			// Al pulsar el botón abre otra actividad donde se muestran las
			// cuentas a nombre del usuario

			@Override
			public void onClick(View v) {
				// Abro la nueva actividad pasándole ese número
				Intent i = new Intent(Oper.this, AccountsSelection.class);
				i.putExtra("user", id_usuario);
				startActivity(i);
			}
		});

		btn_check_credit_card.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(),
						getString(R.string.str_Not_Implemented),
						Toast.LENGTH_SHORT).show();
			}
		});

		btn_transfer_money.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(),
						getString(R.string.str_Not_Implemented),
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_oper, menu);
		return true;
	}

	/**
	 * Acciones del menú
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		FragmentManager fragmentManager = getSupportFragmentManager();
			
		switch (item.getItemId()) {
		case R.id.menu_close_session:
			Close_Session_Dialog close_session_dialog = new Close_Session_Dialog();
			close_session_dialog.show(fragmentManager, "tagCloseSession");
			break;

		case R.id.menu_exit:
			Confirmation_Dialog conf_dialog = new Confirmation_Dialog();
			conf_dialog.show(fragmentManager, "tagConfirmation");
			break;

		default:
			break;
		}

		return true;
	}

	/**
	 * Recupera la información del cliente y lo convierte en un objeto Client
	 */
	private Client user_data() {
		ArrayList<NameValuePair> argumentos = new ArrayList<NameValuePair>();

		argumentos.add(new BasicNameValuePair("user", String
				.valueOf(id_usuario)));

		JSONParser jSp = new JSONParser();
		JSONObject jSo = new JSONObject();
		// Array para volcar los datos del cliente
		JSONArray jSa = null;
		jSo = jSp.makeHttpRequest(URL_connect, "GET", argumentos);

		try {
			// Vuelco los datos del cliente
			jSa = jSo.getJSONArray("datos_cliente");
			// Creo un objeto a partir de este para extraer los datos
			// El índice 0 es porque sólo hay un elemento en el array
			JSONObject jSClient = jSa.getJSONObject(0);
			// Creo un nuevo objeto cliente con los datos extraidos
			client = new Client(jSClient.getInt("id_usr"),
					jSClient.getString("name"),
					jSClient.getString("surname_1"),
					jSClient.getString("surname_2"),
					jSClient.getString("address"), jSClient.getString("city"),
					jSClient.getString("postal_code"),
					jSClient.getString("DNI"));

		} catch (JSONException e) {
			// Log.e("ERROR", "Problema con el JSON");
			e.printStackTrace();
		}
		return client;
	}

	/*
	 * CLASE ASYNCTASK
	 * 
	 * Android obliga a hacer esto para no bloquear la actividad principal en
	 * caso contrario estaríamos forzando contínuamente el ANR
	 * 
	 * Los tipos entre < y > indican lo siguiente:
	 * 
	 * Parámetros (Params). El tipo de información que se necesita para procesar
	 * la tarea. Progreso (Progress). El tipo de información que se pasa dentro
	 * de la tarea para indicar su progreso. Resultado (Result). El tipo de
	 * información que se pasa cuando la tarea ha sido completada.
	 * 
	 * Además tienen que ser objetos, no sirven tipos primitivos
	 */
	class AsyncCheckUserData extends AsyncTask<Void, Void, Client> {
		/**
		 * Preparo el cuadro y muestro el mensaje
		 */
		protected void onPreExecute() {
			// Preparo el ProgressDialog
			pDialog = new ProgressDialog(Oper.this);
			// Fijo los parámetros
			pDialog.setMessage(getResources().getString(
					R.string.str_Retrieving_Client_Information)
					+ "...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			// Finalemente lo muestro
			pDialog.show();
		}

		/**
		 * Esta tarea se realiza en segundo plano
		 * 
		 * @return
		 */
		protected Client doInBackground(Void... arg0) {
			Client cliente_tmp = null;
			if (Check_Server.check_it(Login.URL_server)) {
				// Lanzo el método que recupera la información del usuario
				// Al hacerlo dentro de este método se ejecuta en segundo
				// plano.
				cliente_tmp = user_data();
			}
			return cliente_tmp;
		}

		/**
		 * Al terminar la operación cancelo el diálogo
		 */
		protected void onPostExecute(Client cliente) {

			// Al término de la ejecución del proceso en segundo plano
			// descarto el ProgressDialog
			pDialog.dismiss();

			if (client != null) {
				// Tengo que usar este método para volver al hilo donde se
				// maneja la UI, si no estaríamos en el hilo que trabaja
				// en segundo plano y no tendríamos acceso a la misma
				runOnUiThread(new Runnable() {
					public void run() {
						// Si se recuperó la información del cliente lo muesto
						// en el
						// campo correspondiente
						tv_dni.setText(client.getDNI());
						tv_full_name.setText(client.getSurname_1() + " " + client.getSurname_2() + ", " +  client.getName());
						tv_address.setText(client.getAddress());
						tv_city.setText(client.getPostal_code() + ", " + client.getCity());
						
								
					}
				});
			} else {
				runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(getApplicationContext(),

						getString(R.string.str_Server_off_line),
								Toast.LENGTH_LONG).show();
					}
				});
			}
		}

	}
}