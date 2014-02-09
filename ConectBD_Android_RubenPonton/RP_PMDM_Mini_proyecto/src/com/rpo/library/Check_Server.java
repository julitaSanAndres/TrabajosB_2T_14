package com.rpo.library;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


//http://developer.android.com/reference/java/net/HttpURLConnection.html

public class Check_Server {
	/**
	 * Comprueba si el servidor está en línea o caido
	 * @param url_to_check - url del servidor
	 * @return - verdadero o falso
	 */
	public static boolean check_it(String url_to_check) {
		boolean disponible = false;
		URL url = null;
		HttpURLConnection urlConnection = null;
		try {
			url = new URL(url_to_check);
			urlConnection = (HttpURLConnection) url.openConnection();

			if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				disponible = true;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			urlConnection.disconnect();
		}

		return disponible;
	}
}
