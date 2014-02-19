package com.rubenPen.enviarsms;


import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	
	public void enviarSms(View v){
		Intent intent = new Intent(this, SMSTexto.class);	
		startActivity(intent);
	}
	
	public void enviarEmail(View v){
		Intent intent = new Intent(this, EMail.class);	
		startActivity(intent);
	}

}
