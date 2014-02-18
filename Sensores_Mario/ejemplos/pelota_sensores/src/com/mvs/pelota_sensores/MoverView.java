package com.mvs.pelota_sensores;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.view.View;

public class MoverView extends View {

	private Pelota mMarble;
	private Activity mActivity;

	// el canvas en el que vamos a pintar
	private Canvas mCanvas;

	private Paint mPaint;
	private Typeface mFont = Typeface
			.create(Typeface.SANS_SERIF, Typeface.BOLD);

	// dimensiones de la pantalla
	private int mCanvasWidth = 0;
	private int mCanvasHeight = 0;

	// distintos estados de la aplicacion
	private final static int NULL_STATE = -1;
	private final static int GAME_INIT = 0;
	private final static int GAME_RUNNING = 1;
	// current state of the game
	private static int mCurState = NULL_STATE;

	// inicializamossensor manager usado para el control del acelerometro.
	private SensorManager mSensorManager;
	// valores del accelerometer sensor .
	private float mAccelX = 0;
	private float mAccelY = 0;

	// accelerometer buffer, con esto podemos decidir que tipo de movimiento
	// queremos que mueva la bola,si esta a 0
	// permitiremos que se mueva con el mas leve movimiento
	private float mSensorBuffer = 0;

	// http://code.google.com/android/reference/android/hardware/SensorManager.html#SENSOR_ACCELEROMETER
	// for an explanation on the values reported by SENSOR_ACCELEROMETER.
	private final SensorListener mSensorAccelerometer = new SensorListener() {

		// Se llama a este metodo cada vez que hay una modificacion en el
		// sensor.
		public void onSensorChanged(int sensor, float[] values) {
			// grab the values required to respond to user movement.
			mAccelX = values[0];
			mAccelY = values[1];

		}

		public void onAccuracyChanged(int sensor, int accuracy) {
			// no lo usaremos
		}
	};

	public MoverView(Context context, Activity activity) {
		super(context);

		mActivity = activity;

		// pintado inicial y haremos que se vea mejor con el antialias,(en este
		// caso para una bola no añade mucho
		// pero en el futuro puede ser de utilidad

		mPaint = new Paint();
		mPaint.setTextSize(14);
		mPaint.setTypeface(mFont);
		mPaint.setAntiAlias(true);

		// inicializamos el acelerometro sensor manager.
		mSensorManager = (SensorManager) activity
				.getSystemService(Context.SENSOR_SERVICE);

		// registramos el accelerometer par que reciba valores
		mSensorManager.registerListener(mSensorAccelerometer,
				SensorManager.SENSOR_ACCELEROMETER,
				SensorManager.SENSOR_DELAY_GAME);

		// setup our marble.

		mMarble = new Pelota(this);

		switchGameState(GAME_INIT);

	}

	public void gameTick() {
		switch (mCurState) {
		case GAME_INIT:
			// preparamos la bolita para el inicio de la aplicacion.
			mMarble.init();

		case GAME_RUNNING:
			// actualizamos la pelota.

			pintarPelota();
			break;
		}

		// redibujar la pantalla cuando acaba el case.
		invalidate();
	}

	public void pintarPelota() {
		// Aqui es donde entra el buffer de antes,podemos modificarlo para
		// decidir cuando el movil esta plano

		if (mAccelX > mSensorBuffer || mAccelX < -mSensorBuffer)
			mMarble.updateX(mAccelX);
		if (mAccelY > mSensorBuffer || mAccelY < -mSensorBuffer)
			mMarble.updateY(mAccelY);
	}

	public void onDraw(Canvas canvas) {
		// update our canvas reference.
		mCanvas = canvas;

		// vaciamos la pantalla.
		mPaint.setColor(Color.WHITE);
		mCanvas.drawRect(0, 0, mCanvasWidth, mCanvasHeight, mPaint);

		mMarble.draw(mCanvas, mPaint);

		gameTick();
	}

	public void registerListener() {
		mSensorManager.registerListener(mSensorAccelerometer,
				SensorManager.SENSOR_ACCELEROMETER,
				SensorManager.SENSOR_DELAY_GAME);
	}

	/**
	 * Unregister the accelerometer sensor otherwise it will continue to operate
	 * and report values.
	 */
	public void unregisterListener() {
		mSensorManager.unregisterListener(mSensorAccelerometer);
	}

	public void switchGameState(int newState) {
		mCurState = newState;
	}
}