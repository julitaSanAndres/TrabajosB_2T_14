package com.mvs.agitar_sensores;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity implements SensorEventListener {
	
	  private SensorManager sensorManager;
	  private boolean color = false;
	  private View view;
	  private long lastUpdate;  


	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	        WindowManager.LayoutParams.FLAG_FULLSCREEN);

	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    view = findViewById(R.id.textView_color);
	    view.setBackgroundColor(Color.GREEN);

	    sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	    lastUpdate = System.currentTimeMillis();
	  }

	  @Override
	  public void onSensorChanged(SensorEvent event) {
	    if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
	      getAccelerometer(event);
	    }

	  }

	  private void getAccelerometer(SensorEvent event) {
	    float[] values = event.values;
	    // Movimiento
	    float x = values[0];
	    float y = values[1];
	    float z = values[2];

	    float accelationSquareRoot = (x * x + y * y + z * z) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
	    long actualTime = System.currentTimeMillis();
	    if (accelationSquareRoot >= 2) //
	    {
	      if (actualTime - lastUpdate < 200) {
	        return;
	      }
	      lastUpdate = actualTime;
	      
	      
	      //argb--> alfa, rojo, verde y azul
	      //rgb--> red,green and blue	      
	      int[] colors = {Color.RED,Color.YELLOW,Color.BLUE,Color.argb(127, 0, 255, 0),Color.WHITE,Color.rgb(230, 100, 0)};
	      int colorRandom = (int)(6*Math.random());
	      
	      if (color) {
	        view.setBackgroundColor(colors[colorRandom]);

	      } else {
	        view.setBackgroundColor(colors[colorRandom]);
	      }
	      color = !color;
	    }
	  }

	  @Override
	  public void onAccuracyChanged(Sensor sensor, int accuracy) {

	  }

	  @Override
	  protected void onResume() {
	    super.onResume();
	    sensorManager.registerListener(this,
	        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
	        SensorManager.SENSOR_DELAY_NORMAL);
	  }

	  @Override
	  protected void onPause() {
	    super.onPause();
	    sensorManager.unregisterListener(this);
	  }
	} 