package com.rpo.rp_pmdm_mini_proyecto;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.rpo.library.Check_Server;
import com.rpo.library.JSONParser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MovementsList extends FragmentActivity {
	private int id_account;
	private String iban;
	private Double balance;

	private ProgressDialog pDialog;

	private MovementManager movementManager = null;

	private String URL_connect = Login.URL_server + "/phps/l_a_m.php";

	TextView tv_iban;
	ListView lv_movements_list;
	TextView tv_balance;

	private NumberFormat formatoImporte;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movements_list);

		formatoImporte = NumberFormat.getCurrencyInstance();

		tv_iban = (TextView) findViewById(R.id.tv_account_iban);
		lv_movements_list = (ListView) findViewById(R.id.lv_account_movements);
		tv_balance = (TextView) findViewById(R.id.tv_account_balance);

		movementManager = new MovementManager();

		// Recupero los datos pasados en el intent
		Bundle bundle = this.getIntent().getExtras();
		// Guardo el ID del usuario
		id_account = bundle.getInt("id_account");
		iban = bundle.getString("iban");
		balance = bundle.getDouble("balance");

		iban = iban.substring(0, 4) + " " + iban.substring(4, 8) + " "
				+ iban.substring(8, 10) + " " + iban.substring(10, 20);

		// Pongo los valores del IBAN de la cuenta y el saldo
		tv_iban.setText(iban);
		tv_balance.setText("Balance: " + formatoImporte.format(balance));

		// Ahora relleno el ListView con los moviemientos de la cuenta
		new AsyncGetListofMovements().execute();

	}

	// Uso el mismo menú que en Oper porque voy a realizar las mismas acciones
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
	 * Recupera la información de movimientos de una cuenta dada
	 */
	private MovementManager movements_data() {
		MovementManager acc_movs_temp = new MovementManager();
		Movement movement = null;

		ArrayList<NameValuePair> argumentos = new ArrayList<NameValuePair>();

		argumentos.add(new BasicNameValuePair("id_account", String
				.valueOf(id_account)));

		JSONParser jSp = new JSONParser();
		JSONObject jSo = new JSONObject();
		// Array para volcar los datos del cliente
		JSONArray jSm = null;
		jSo = jSp.makeHttpRequest(URL_connect, "GET", argumentos);

		// Esto lo usaré luego para parsear la fecha
		SimpleDateFormat formatoFecha = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault());

		try {
			// Vuelco los datos del cliente
			jSm = jSo.getJSONArray("movimientos");
			int num_movs = jSm.length();

			for (int i = 0; i < num_movs; i++) {
				// Creo un objeto a partir de este para extraer los datos
				JSONObject jSMovement = jSm.getJSONObject(i);

				// Creo un nuevo objeto Account con los datos extraidos
				movement = new Movement(jSMovement.getInt("id_movimiento"),
						jSMovement.getDouble("cantidad"),
						jSMovement.getDouble("balance_actual"),
						jSMovement.getString("descripcion"),
						formatoFecha.parse(jSMovement.getString("fecha")),
						jSMovement.getString("tipo_movimiento"));

				// Lo añado a la lista de cuentas del cliente
				acc_movs_temp.addMovement(movement);
			}

		} catch (JSONException e) {
			// Log.e("ERROR", "Problema con el JSON");
			e.printStackTrace();
		} catch (ParseException e) {
			// Por si falla la conversión del String en Date
			e.printStackTrace();
		}
		return acc_movs_temp;
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
	class AsyncGetListofMovements extends
			AsyncTask<Void, Void, MovementManager> {
		/**
		 * Preparo el cuadro y muestro el mensaje
		 */
		protected void onPreExecute() {
			// Preparo el ProgressDialog
			pDialog = new ProgressDialog(MovementsList.this);
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
		protected MovementManager doInBackground(Void... arg0) {
			movementManager = null;
			if (Check_Server.check_it(Login.URL_server)) {
				// Lanzo el método que recupera la información de las cuentas
				// del usuario
				// Al hacerlo dentro de este método se ejecuta en segundo
				// plano.
				movementManager = movements_data();
			}

			return movementManager;
		}

		/**
		 * Al terminar la operación cancelo el diálogo
		 */
		protected void onPostExecute(MovementManager movimientos) {

			// Al término de la ejecución del proceso en segundo plano
			// descarto el ProgressDialog
			pDialog.dismiss();
			if (movementManager != null) {
				// Tengo que usar este método para volver al hilo donde se
				// maneja la UI, si no estaríamos en el hilo que trabaja
				// en segundo plano y no tendríamos acceso a la misma
				runOnUiThread(new Runnable() {
					public void run() {
						// Si se recuperó la información de movimientos
						// de las cuentas
						if (movementManager.numberOfMovements() > 0) {
							ArrayList<Movement> movimientos = new ArrayList<Movement>();
							for (int i = 0; i < movementManager
									.numberOfMovements(); i++) {
								movimientos.add(movementManager.getMovement(i));
							}
							// Creo el adaptador para el ListView
							MovementsListCustomAdapter adpCM = new MovementsListCustomAdapter(
									MovementsList.this,
									R.layout.movements_list, movimientos);

							// Asigno el adaptador al ListView
							lv_movements_list.setAdapter(adpCM);
						}

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
