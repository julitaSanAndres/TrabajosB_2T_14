package com.mvs.ejemplosencillo_v2;

import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView sensores;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sensores = (TextView) findViewById(R.id.sensores);
		SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		List<Sensor> listaSensores = sensorManager
				.getSensorList(Sensor.TYPE_ALL);
		for (Sensor sensor : listaSensores) {
			log(sensor.getName());
		}
	}

	private void log(String string) {
		sensores.append(string + "\n");
	}
}
