package com.robertobayon.aplastaelmal;

import com.robertobayon.interfaces.Music;
import com.robertobayon.interfaces.Sound;

public class AudioJuego {
	private static AndroidAudio audio;
	private static Music musicaJuego;
	private static Sound maloMuerto;
	private static Sound buenaMuerta;
	
	public AudioJuego(AndroidAudio audio){
		this.audio = audio;
		this.musicaJuego = audio.newMusic("guile__s_theme.mid");
		this.maloMuerto = audio.newSound("WilhelmScream_64kb.mp3");
		this.buenaMuerta = audio.newSound("Synth_cnl_64_wav.aax_0000.wav");
	}
	
	public static void reproducirMusica(){
		musicaJuego.setVolume(10);
		musicaJuego.play();
	}
	
	public static void pararMusica(){
		musicaJuego.stop();
	}
	
	public static void sonidoMaloMuerto(){
		maloMuerto.play(10);
	}
	
	public static void sonidoBuenaMuerta(){
		buenaMuerta.play(10);
	}
}
