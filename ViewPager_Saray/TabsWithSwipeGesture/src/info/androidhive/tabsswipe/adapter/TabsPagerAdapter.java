package info.androidhive.tabsswipe.adapter;

import info.androidhive.tabsswipe.FragmentoImagen;
import info.androidhive.tabsswipe.FragmentoTexto2;
import info.androidhive.tabsswipe.FragmentoTexto;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	//metodo que recibe el numero de pagina y devolvemos el numero de Fragment 
	//que queremos que muestre
	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Activity Primer Fragmento solo texto
			return new FragmentoTexto();
		case 1:
			// Activity segundo fragmento Imagen
			return new FragmentoImagen();
		case 2:
			// Activity tercer Fragmento solo texto
			return new FragmentoTexto2();
		}

		return null;
	}

	@Override
	public int getCount() {
		//numero de pestañas
		return 3;
	}

}
