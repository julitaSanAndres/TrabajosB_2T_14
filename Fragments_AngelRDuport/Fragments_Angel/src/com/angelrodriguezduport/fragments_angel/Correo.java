package com.angelrodriguezduport.fragments_angel;

import java.io.Serializable;

public class Correo implements Serializable{
	
	private String remitente;
	private String asunto;
	private String contenido;

	public Correo(String remitente, String asunto, String contenido) {
		this.remitente = remitente;
		this.asunto = asunto;
		this.contenido = contenido;
	}

	public String getRemitente() {
		return remitente;
	}

	public String getAsunto() {
		return asunto;
	}

	public String getContenido() {
		return contenido;
	}
}
