package com.robertobayon.aplastaelmal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class GameView extends SurfaceView {
	private SurfaceHolder holder;//El holder es el contenedor que contiene todo
	private GameLoopThread gameLoopThread;//hilo que gestiona el juego
	private HiloCronoJuego crono;//hilo cronometro para medir los segundos
	private List<Sprite> sprites = new ArrayList<Sprite>();//aqui guardaremos los sprites
	private long lastClick;//mediremos cuanto hace que dimos nuestro ultimo click
	private Bitmap bmpBlood;//aqui almacenaremos el sprite temporal
	private int malosRestantes;//numero de malos, sabemos que son 6
	private List<TempSprite> temps = new ArrayList<TempSprite>();//arraylist de sprites temporales
	private AlertDialog dialog;//ventana de alerta para victorias o derrotas
	private Bitmap[] fondos = {BitmapFactory.decodeResource(getResources(), R.drawable.fondo1),
			BitmapFactory.decodeResource(getResources(), R.drawable.fondo2),
			BitmapFactory.decodeResource(getResources(), R.drawable.fondo3),
			BitmapFactory.decodeResource(getResources(), R.drawable.fondo4)};//fondos de juego
	private int posicion;//posicion del array de fondos que calcularemos en el constructor
	
	public GameView(Context context) {
		super(context);
		//Hacemos un aleatorio de la longitud del array de sprites
		Random aleatorio = new Random();
		posicion = aleatorio.nextInt(fondos.length);
		//reproducimos la musica del juego
		GestionJuego.audioJuego.reproducirMusica();
		malosRestantes = 0;
		gameLoopThread = new GameLoopThread(this);
		holder = getHolder();
		holder.addCallback(new SurfaceHolder.Callback() {
			
			//Este metodo decide lo que se hace en caso de que la superficie se destruya,
			//para los hilos
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				boolean retry = true;
				gameLoopThread.setRunning(false);
				while (retry) {
					try {
						gameLoopThread.join();
						retry = false;
					} catch (InterruptedException e) {

					}
				}
			}

			//Al crear la surface añadimos los sprites e iniciamos los hilos,
			//empieza a contar el cronometro
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				createSprites();
				gameLoopThread.setRunning(true);
				gameLoopThread.start();
				crono = new HiloCronoJuego();
				crono.start();
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
			}
		});
		bmpBlood = BitmapFactory.decodeResource(getResources(),
				R.drawable.blood1);//inicializamos el sprite temporal
		
	}

	//En este metodo llenamos el array de sprites,tambien estipulamos que los malos son la mitad
	private void createSprites() {
		sprites.add(createSprite(R.drawable.bad1, true));
		sprites.add(createSprite(R.drawable.bad2, true));
		sprites.add(createSprite(R.drawable.bad3, true));
		sprites.add(createSprite(R.drawable.bad4, true));
		sprites.add(createSprite(R.drawable.bad5, true));
		sprites.add(createSprite(R.drawable.bad6, true));
		sprites.add(createSprite(R.drawable.good1, false));
		sprites.add(createSprite(R.drawable.good2, false));
		sprites.add(createSprite(R.drawable.good3, false));
		sprites.add(createSprite(R.drawable.good4, false));
		sprites.add(createSprite(R.drawable.good5, false));
		sprites.add(createSprite(R.drawable.good6, false));
		malosRestantes = sprites.size()/2;
	}

	//creamos los sprites usando como parametros el id del recurso y un boolean indicando
	//si son buenos
	private Sprite createSprite(int resouce, boolean alineacion) {
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), resouce);
		return new Sprite(this, bmp, alineacion);
	}

	//El metodo onDraw se encarga de dibujar,dibuja el fondo y los sprites en pantalla
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(fondos[posicion], 0, 0, null);
		for (int i = temps.size() - 1; i >= 0; i--) {
			temps.get(i).onDraw(canvas);
		}
		for (Sprite sprite : sprites) {
			sprite.onDraw(canvas);
		}
	}

	//Aqui controlamos los eventos derivados de tocar en pantalla, es decir, la eliminacion de buenos
	//o de malos y por lo tanto tambien las condiciones de victoria
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (System.currentTimeMillis() - lastClick > 300) {//controlamos que entre clicks no 
			                                               //haya menos de 300ms
			lastClick = System.currentTimeMillis();//vamos guardando el tiempo para controlar 
			                                       //que no haya menos de 300ms entre toques
			float x = event.getX();//eje x del toque en pantalla
			float y = event.getY();//eje y del toque en pantalla
			String hayRecord = "";
			int recordAnterior = 0;
			synchronized (getHolder()) {//sincronizamos el acceso al holder para que no haya 
				                        //colisiones

				for (int i = sprites.size() - 1; i >= 0; i--) {//recorremos el array de sprites
					Sprite sprite = sprites.get(i);
					if (sprite.isCollision(x, y)) {//comprobamos que haya impacto de nuestro 
												   //dedo en un personaje
						if (sprite.esMalo()) {//si es malo sigue la partida hasta que sean 0
							malosRestantes--;//un malo menos
							sprites.remove(sprite);//eliminamos el sprite correspondiente al malo
							temps.add(new TempSprite(temps, this, x, y,
									bmpBlood));//ponemos una mancha de sangre en su lugar
							GestionJuego.audioJuego.sonidoMaloMuerto();//reproducimos el sonido del 
							                                           //malo muerto
							if(malosRestantes == 0){//si eliminamos a todos los malos habremos ganado
								crono.parar();//paramos el crono
								if(crono.getSegundos() < GestionJuego.leeRecord()){//comprobamos que 
									                                               //haya record
									recordAnterior = GestionJuego.leeRecord();//guardamos el record 
									                                        //anterior para mostrarlo
									GestionJuego.grabaRecord(crono.getSegundos());//guardamos el 
									                                             //nuevo record
									hayRecord = "!Se ha batido el record!";//frase informativa
								}else{
									hayRecord = "No hay record";
									recordAnterior = GestionJuego.leeRecord();
								}
								AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
							    dialog = builder.setTitle("Victoria")
							        .setMessage("!Lo has conseguido!\nTu tiempo ha sido " + 
							    crono.getSegundos() + " segundos\n" + "El record era " + 
							    recordAnterior + " segundos\n" + hayRecord)
							        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog, int id) {
											GestionJuego.audioJuego.pararMusica();//paramos la musica
											Context con = getContext();//obtenemos el context
											((Activity) con).finish();//finalizamos el juego
						                   }
						               }).create();
							    dialog.show();//creamos la alerta de victoria
							    gameLoopThread.setRunning(false);//paramos los hilos
							}
							break;//salimos del bucle
						} else {//hemos perdido al eliminar a una buena
							sprites.remove(sprite);
							temps.add(new TempSprite(temps, this, x, y,
									bmpBlood));//eliminamos el sprite y ponemos sangre en su lugar
							GestionJuego.audioJuego.sonidoBuenaMuerta();//sonido de matar una buena
							AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
						    dialog = builder.setTitle("Game over")
						        .setMessage("!Has cometido un terrible error!")
						        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {			                 
										GestionJuego.audioJuego.pararMusica();
										Context con = getContext();
										((Activity) con).finish();
					                   }
					               }).create();
						    dialog.show();
						    gameLoopThread.setRunning(false);//esta parte es igual que la anterior 
						    								//pero hemos perdido
						}
					}
				}
			}
		}
		return true;
	}

	
	
}