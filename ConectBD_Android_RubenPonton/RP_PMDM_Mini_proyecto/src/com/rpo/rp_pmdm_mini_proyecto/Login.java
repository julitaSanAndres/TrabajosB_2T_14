package com.rpo.rp_pmdm_mini_proyecto;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import org.json.JSONException;
import org.json.JSONObject;

import com.rpo.library.Check_Server;
import com.rpo.library.JSONParser;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends FragmentActivity {
	// Variables para la conexión de la base
	// public static final String IP_Server = "10.2.253.131";
	public static final String IP_Server = "192.168.0.46";
//	public static final String IP_Server = "10.2.253.76";
	public static final String URL_server = "http://" + IP_Server;
	private String URL_connect = URL_server + "/phps/c_u_p.php";
	
	EditText et_usr;
	EditText et_pass;

	Button btn_login;

	private ProgressDialog pDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		et_usr = (EditText) findViewById(R.id.et_User);
		et_pass = (EditText) findViewById(R.id.et_Passw);

		btn_login = (Button) findViewById(R.id.btn_Login);

		btn_login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {

				// Compruebo si los campos están en blanco
				if (comprobarUsrPass(et_usr.getText().toString(), et_pass
						.getText().toString())) {

					/*
					 * Lanzo el proceso asíncrono de login con el usuario y la
					 * contraseña como argumentos
					 */
					new asyncLogin().execute(et_usr.getText().toString(),
							et_pass.getText().toString());

				} else {
					// No se ha superado la comprobación de los campos
					err_login();
				}

			}

		});
	}

	/**
	 * Hace vibrar el dispositivo y lanza un mensaje de error
	 */
	private void err_login() {
		Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(200);
		Toast toast1 = Toast.makeText(getApplicationContext(), getResources()
				.getString(R.string.str_User_Pass_Fail), Toast.LENGTH_SHORT);
		toast1.show();
	}

	/**
	 * Compruebo si los campos Usuario y Password están vacíos
	 * 
	 * @param username
	 *            - valor del campo usuario
	 * @param password
	 *            - valor del campo password
	 * @return - true o false
	 */
	private boolean comprobarUsrPass(String username, String password) {
		return !(username.equals("") || password.equals(""));
	}

	/**
	 * Comprueba si el usuario y la contraseña dados son correctos. En caso de
	 * serlo devuelve el ID del usuario electrónico en la base de datos, en caso
	 * contrario devuelve -1
	 * 
	 * @param user
	 *            - cadena con el nombre del usuario
	 * @param psw
	 *            - cadena con la contraseña
	 * @return - un entero ID
	 */
	private int check_usr_psw(String user, String psw) {
		int id_usuario = -1;

		ArrayList<NameValuePair> argumentos = new ArrayList<NameValuePair>();

		argumentos.add(new BasicNameValuePair("user", user));
		argumentos.add(new BasicNameValuePair("psw", psw));

		JSONParser jSp = new JSONParser();
		JSONObject jSo = new JSONObject();
		jSo = jSp.makeHttpRequest(URL_connect, "GET", argumentos);

		try {
			id_usuario = jSo.getInt("id_usr");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return id_usuario;
	}

	/**
	 * Código de creación del menú
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_login, menu);
		return true;
	}

	/**
	 * Acciones del menú principal
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// Si se selecciona salir en el menú pido confirmación
		case R.id.menu_exit:
			FragmentManager fragmentManager = getSupportFragmentManager();
			Confirmation_Dialog conf_dialog = new Confirmation_Dialog();
			conf_dialog.show(fragmentManager, "tagConfirmation");
			break;

		default:
			break;
		}

		return true;
	}

	/*
	 * CLASE ASYNCTASK
	 * 
	 * Android obliga a hacer esto para no bloquear la actividad principal en
	 * caso contrario estaríamos forzando contínuamente el ANR
	 */
	class asyncLogin extends AsyncTask<String, Void, Integer> {
		/**
		 * Preparo el cuadro y muestro el mensaje mientras se verifica con el
		 * servidor el usuario y contraseña
		 */
		protected void onPreExecute() {
			// Preparo el ProgressDialog
			pDialog = new ProgressDialog(Login.this);
			// Fijo los parámetros
			pDialog.setMessage(getResources().getString(
					R.string.str_Checking_user_password)
					+ "...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			// Finalemente lo muestro
			pDialog.show();
		}

		/**
		 * Esta tarea se realiza en segundo plano
		 */
		protected Integer doInBackground(String... params) {
			int id_usuario = 0;
			if (Check_Server.check_it(URL_server)) {
				// Lanzo el método para comprobar el usuario y la contraseña
				// Al hacerlo dentro de este método se ejecuta en segundo
				// plano.
				id_usuario = check_usr_psw(params[0], params[1]);
				// Devuelve el ID del usuario
			}
			return id_usuario;
		}

		/**
		 * Si el id del usuario es correcto inicio la nueva actividad pasándole
		 * el número del usuario, si no aviso del problema
		 */
		protected void onPostExecute(Integer id_usuario) {

			// Al término de la ejecución del proceso en segundo plano
			// descarto el ProgressDialog
			pDialog.dismiss();

			// Compruebo si el número de usuario es correcto...
			switch (id_usuario) {
			// No se ha podido conectar con el servidor
			case 0:
				// Tengo que usar este método para volver al hilo donde se
				// maneja la UI, si no estaríamos en el hilo que trabaja
				// en segundo plano y no tendríamos acceso a la misma
				runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(getApplicationContext(),

						getString(R.string.str_Server_off_line),
								Toast.LENGTH_LONG).show();
					}
				});
				break;
			// Nombre o contraseña erróneos
			case -1:
				err_login();
				break;
			// Si todo ha ido bien...
			default:
				// Cierro la actividad de Login para que al usar la opción de
				// salir efectivamente salga de la aplicación
				finish();
				
				// Abro la nueva actividad pasándole ese número
				Intent i = new Intent(Login.this, Oper.class);
				i.putExtra("user", id_usuario);
				startActivity(i);
				break;
			}
		}

	}
}