package com.angelrodriguezduport.fragments_angel;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class ActivityDetalle extends FragmentActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);
		
		FragmentDetalle detalle = 
				(FragmentDetalle)getSupportFragmentManager()
					.findFragmentById(R.id.fragment2);
		
		detalle.mostrarDetalle(getIntent().getStringExtra("correo"));
	}
}
