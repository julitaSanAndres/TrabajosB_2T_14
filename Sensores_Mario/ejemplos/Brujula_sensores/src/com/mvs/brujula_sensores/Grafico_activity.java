package com.mvs.brujula_sensores;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class Grafico_activity extends Activity {

	private static SensorManager sensorService;
	private Brujula objBrujula;
	private Sensor sensor;	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		objBrujula = new Brujula(this);
		setContentView(objBrujula);
		
		
		sensorService = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

		sensor = sensorService.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		
		if (sensor != null) {
			sensorService.registerListener(mySensorEventListener, sensor,
					SensorManager.SENSOR_DELAY_NORMAL);

		} else {

			Toast.makeText(this, "SENSOR DE ORIENTACION no encontrado",
					Toast.LENGTH_LONG).show();
			finish();
		}
	}

	private SensorEventListener mySensorEventListener = new SensorEventListener() {

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			
			float azimuth = event.values[0];
			objBrujula.updateData(azimuth);
			
			String txt = "";
			txt = getDireccion(event.values[0]);	
		}
	};

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
	protected void onDestroy() {
		super.onDestroy();
		if (sensor != null) {
			sensorService.unregisterListener(mySensorEventListener);
		}
	}

}
