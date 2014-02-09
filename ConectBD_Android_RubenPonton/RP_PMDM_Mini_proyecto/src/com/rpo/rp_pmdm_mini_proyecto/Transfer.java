package com.rpo.rp_pmdm_mini_proyecto;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

public class Transfer extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transfer);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_oper, menu);
		return true;
	}

	/**
	 * Acciones del menú principal
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_close_session:

			break;

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

}
