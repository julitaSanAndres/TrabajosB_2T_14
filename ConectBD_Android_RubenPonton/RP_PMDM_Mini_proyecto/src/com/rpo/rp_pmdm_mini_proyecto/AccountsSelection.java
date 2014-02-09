package com.rpo.rp_pmdm_mini_proyecto;

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
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AccountsSelection extends FragmentActivity {
	private int id_usuario;

	private ProgressDialog pDialog;

	private AccountManager accountManager = null;

	private String URL_connect = Login.URL_server + "/phps/l_a_c.php";

	ListView lv_account_selection;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accounts_selection);

		lv_account_selection = (ListView) findViewById(R.id.lv_account_selection);

		accountManager = new AccountManager();

		// Recupero los datos pasados en el intent
		Bundle bundle = this.getIntent().getExtras();
		// Guardo el ID del usuario
		id_usuario = bundle.getInt("user");

		new AsyncGetListofAccounts().execute();

		lv_account_selection.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adap, View convertView,
					int position, long id) {
				// Abro la actividad movimientos de cuenta pasándole
				// el ID de cuenta, el IBAN y el saldo
				 Intent i = new Intent(AccountsSelection.this,
				 MovementsList.class);
				
				 i.putExtra("id_account",
				 accountManager.getClient_Accounts(position)
				 .getId_cuenta());
				 i.putExtra("iban",
				 accountManager.getClient_Accounts(position)
				 .getIban());
				 i.putExtra("balance",
				 accountManager.getClient_Accounts(position)
				 .getBalance());
				
				 startActivity(i);

			}
		});

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
	 * Recupera la información de las cuentas del cliente, concretamente el IBAN
	 * y el saldo
	 */
	private AccountManager accounts_data() {
		AccountManager acc_man_temp = new AccountManager();
		Account account = null;
		ArrayList<NameValuePair> argumentos = new ArrayList<NameValuePair>();

		argumentos.add(new BasicNameValuePair("id_client", String
				.valueOf(id_usuario)));

		JSONParser jSp = new JSONParser();
		JSONObject jSo = new JSONObject();
		// Array para volcar los datos del cliente
		JSONArray jSa = null;
		jSo = jSp.makeHttpRequest(URL_connect, "GET", argumentos);

		try {
			// Vuelco los datos del cliente
			jSa = jSo.getJSONArray("cuentas");
			int num_cuentas = jSa.length();

			for (int i = 0; i < num_cuentas; i++) {
				// Creo un objeto a partir de este para extraer los datos
				JSONObject jSAccount = jSa.getJSONObject(i);
				// Creo un nuevo objeto Account con los datos extraidos
				account = new Account(jSAccount.getInt("id_cuenta"),
						jSAccount.getString("iban"),
						jSAccount.getDouble("balance"),
						jSAccount.getString("tipo_cuenta"));

				// Lo añado a la lista de cuentas del cliente
				acc_man_temp.addAccount(account);
			}

		} catch (JSONException e) {
			// Log.e("ERROR", "Problema con el JSON");
			e.printStackTrace();
		}
		return acc_man_temp;
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
	class AsyncGetListofAccounts extends AsyncTask<Void, Void, AccountManager> {
		/**
		 * Preparo el cuadro y muestro el mensaje
		 */
		protected void onPreExecute() {
			// Preparo el ProgressDialog
			pDialog = new ProgressDialog(AccountsSelection.this);
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
		protected AccountManager doInBackground(Void... arg0) {
			accountManager = null;
			if (Check_Server.check_it(Login.URL_server)) {
				// Lanzo el método que recupera la información de las cuentas
				// del usuario
				// Al hacerlo dentro de este método se ejecuta en segundo
				// plano.
				accountManager = accounts_data();
			}

			return accountManager;
		}

		/**
		 * Al terminar la operación cancelo el diálogo
		 */
		protected void onPostExecute(AccountManager cuenta) {

			// Al término de la ejecución del proceso en segundo plano
			// descarto el ProgressDialog
			pDialog.dismiss();
			if (accountManager != null) {
				// Tengo que usar este método para volver al hilo donde se
				// maneja la UI, si no estaríamos en el hilo que trabaja
				// en segundo plano y no tendríamos acceso a la misma
				runOnUiThread(new Runnable() {
					public void run() {
						// Si se recuperó la información de las cuentas lo meto
						// en el ListView
						if (accountManager.numberOfAccounts() > 0) {
							ArrayList<Account> cuentas = new ArrayList<Account>();
							for (int i = 0; i < accountManager
									.numberOfAccounts(); i++) {
								cuentas.add(accountManager
										.getClient_Accounts(i));
							}
							// Creo el adaptador para el ListView
							AccountListCustomAdapter adpCM = new AccountListCustomAdapter(
									AccountsSelection.this,
									R.layout.account_list, cuentas);

							// Asigno el adaptador al ListView
							lv_account_selection.setAdapter(adpCM);
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
