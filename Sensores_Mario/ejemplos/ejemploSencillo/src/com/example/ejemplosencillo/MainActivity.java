package com.example.ejemplosencillo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorEventListener {

	private SensorManager mSensorManager ;
    private  Sensor sensor;
    
    private TextView datosSensor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
         sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);  
         
         datosSensor = (TextView) findViewById(R.id.textView1);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
		String datos = "DATOS SENSOR \nx: " + event.values[0]+"\n  y: " + event.values[1] +"\n  z: " + event.values[2];			
		
			
		datosSensor.setText(datos);
		
	}
	
    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    
    
    

    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


}
