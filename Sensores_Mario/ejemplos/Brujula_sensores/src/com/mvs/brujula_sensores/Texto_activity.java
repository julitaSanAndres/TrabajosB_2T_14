package com.mvs.brujula_sensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class Texto_activity extends Activity implements SensorEventListener {

	private TextView orientacion;
	
	private SensorManager mSensorManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_texto_activity);

		orientacion = (TextView) findViewById(R.id.textView_mostrarTexto);
		
		// Accedemos al servicio de sensores
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		
		//inicio
		Ini_Sensor();
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.texto_activity, menu);
		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		String txt = "";			
		
		txt = " -->Direccion: " + getDireccion(event.values[0]);
		txt += "\n\n Valores de los ejes";
		txt += "\n  y: " + event.values[1] + "¼";
		txt += "\n  z: " + event.values[2] + "¼";
		
		orientacion.setText(txt);
		
		
		
	}
	private String getDireccion(float values) {
		String txtDirection = "";
		if (values < 22)
			txtDirection = "N";
		else if (values >= 22 && values < 67)
			txtDirection = "NE";
		else if (values >= 67 && values < 112)
			txtDirection = "E";
		else if (values >= 112 && values < 157)
			txtDirection = "SE";
		else if (values >= 157 && values < 202)
			txtDirection = "S";
		else if (values >= 202 && values < 247)
			txtDirection = "SO";
		else if (values >= 247 && values < 292)
			txtDirection = "O";
		else if (values >= 292 && values < 337)
			txtDirection = "NO";
		else if (values >= 337)
			txtDirection = "N";

		return txtDirection;
	}
	@Override
	protected void onStop() {

		Parar_Sensor();

		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub

		
		Parar_Sensor();
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		Parar_Sensor();
		

		super.onPause();
	}

	private void Parar_Sensor() {
		// TODO Auto-generated method stub
		
		mSensorManager.unregisterListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION));
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub

		Ini_Sensor();

		super.onRestart();
	}

	private void Ini_Sensor() {
		// TODO Auto-generated method stub
		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onResume() {
		super.onResume();

		Ini_Sensor();

	}

}
