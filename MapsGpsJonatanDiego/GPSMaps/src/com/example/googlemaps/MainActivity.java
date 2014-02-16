package com.example.googlemaps;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;

import android.content.Context;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends android.support.v4.app.FragmentActivity {

	// Variables del mapa y del spinner
	private GoogleMap mapa;
	private Spinner spin;
	private Marker actual;

	// variables de tipo locationManager y locListener necesaria para obtener el
	// gps
	private LocationManager locationManager;
	private LocationListener locListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// inicilizamos los atributos
		mapa = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		// método para un click en el mapa
		mapa.setOnMapClickListener(new OnMapClickListener() {
			public void onMapClick(LatLng point) {

				Projection proj = mapa.getProjection();
				Point coord = proj.toScreenLocation(point);
				Toast.makeText(
						MainActivity.this,
						"Coordenadas de posición" + "Lat: " + point.latitude
								+ "\n" + "Lng: " + point.longitude + "\n"
								+ "X: " + coord.x + " - Y: " + coord.y,
						Toast.LENGTH_SHORT).show();
			}
		});

		// método para un click largo en el mapa
		mapa.setOnMapLongClickListener(new OnMapLongClickListener() {
			public void onMapLongClick(LatLng point) {

				Projection proj = mapa.getProjection();
				Point coord = proj.toScreenLocation(point);
				Toast.makeText(
						MainActivity.this,
						"Coordenadas de posicion" + "Lat: " + point.latitude
								+ "\n" + "Lng: " + point.longitude + "\n"
								+ "X: " + coord.x + " - Y: " + coord.y,
						Toast.LENGTH_SHORT).show();
			}
		});

		// método para indicar los datos del zoom del mapa
		mapa.setOnCameraChangeListener(new OnCameraChangeListener() {

			public void onCameraChange(CameraPosition position) {

				Toast.makeText(
						MainActivity.this,
						"Cambio Cámara\n" + "Lat: " + position.target.latitude
								+ "\n" + "Lng: " + position.target.longitude
								+ "\n" + "Zoom: " + position.zoom + "\n"
								+ "Orientación: " + position.bearing + "\n"
								+ "Ángulo: " + position.tilt,
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	// método para que cuandop pulsemos el boton y segun lo seleccionado en el
	// spinner nos lleve al item seleccionado
	public void activarGPS(View v) {
		// Control de errores según el estado del gps
		boolean enabled = locationManager
				.isProviderEnabled(LocationManager.GPS_PROVIDER);
		if (!enabled) {
			Toast.makeText(getBaseContext(), "Activa el gps", Toast.LENGTH_LONG).show();
		} else {
			// llamamos al método que obtiene la ubicación actual
			localizacion();
			Toast.makeText(getBaseContext(), "Buscando...", Toast.LENGTH_LONG).show();
		}
	}
	// cortas las actualizaciones del GPS
	public void pararGPS(View v){		
		locationManager.removeUpdates(locListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.Normal:
			mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			break;
		case R.id.Hibrido:
			mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			break;
		case R.id.Satelite:
			mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			break;
		case R.id.Terreno:
			mapa.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			break;

		}
		return true;

	}

	// método para ir a las corrdenadas de la localización actual
	// y ampliado el zoom del mapa a 15
	public void irActual(Location loc) {

		if (loc != null) {
			Double lat = loc.getLatitude();
			Double lon = loc.getLongitude();

			// creamos las coordenadas
			LatLng coordenadas = new LatLng(lat, lon);
			if (actual == null) {

				// creamos un marcador en la posicion
				actual = mapa.addMarker(new MarkerOptions()
						.position(coordenadas).title("ESTAS AQUI")
						.snippet(lat + "," + lon).draggable(true));

			} else {
				actual.setPosition(new LatLng(lat, lon));
			}

			CameraPosition posicion = new CameraPosition.Builder()
					.target(coordenadas).zoom(15).build();

			CameraUpdate camUpd = CameraUpdateFactory
					.newCameraPosition(posicion);
			mapa.animateCamera(camUpd);

		}

	}
	// método para que el gps detecte nuestra posición actual

	public void localizacion() {

		// Obtenemos la última posición conocida
		// Location loc =
		// locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		// Nos registramos para recibir actualizaciones de la posición
		locListener = new LocationListener() {
			public void onLocationChanged(Location location) {
				irActual(location);
			}

			public void onProviderDisabled(String provider) {
				Toast.makeText(getApplicationContext(), "Gps Desactivado",
						Toast.LENGTH_LONG).show();
			}

			public void onProviderEnabled(String provider) {
				Toast.makeText(getApplicationContext(), "GPS Activado",
						Toast.LENGTH_LONG).show();
			}

			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				Log.i("", "Provider Status: " + status);
				Toast.makeText(getApplicationContext(),
						"Provider Status: " + status, Toast.LENGTH_LONG).show();
			}

		};

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				3000, 0, locListener);

	}

}
