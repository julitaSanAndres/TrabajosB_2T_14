package info.androidhive.tabsswipe;

import info.androidhive.tabsswipe.adapter.TabsPagerAdapter;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	// lista de titulos
	private String[] tabs = { "Texto", "Imagen", "Texto2" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Iniciacion
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
		
		//le pasamos el adaptador
		viewPager.setAdapter(mAdapter);
		//Activar o desactivar el botón "Home" en la esquina del Action Bar.
		actionBar.setHomeButtonEnabled(false);
		//para trabajar con pestañas
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);		

		// añadir titulos
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		/**
		 * al desplazarnos se desplaza la seleccion del titulo
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				//le pasamos la posicion
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		//nos desplaza a la pestaña selecionada al pulsar encima del titulo
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

}
