package com.fran.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.Socket;
import java.net.UnknownHostException;

public abstract class TCPCliente {

    private DataInputStream flujoI;
    private DataOutputStream flujoO;

    public TCPCliente() {
        Socket socketCliente;
        InputStream flujoDeEntrada;
        OutputStream flujoDeSalida;
        try {
            socketCliente = new Socket("192.168.1.10" , 8000);

//El metodo Accept interrumpe la ejecucion de la aplicacion
            System.out.println("Comunicacion establecida");
            flujoDeEntrada = socketCliente.getInputStream();
            flujoI = new DataInputStream(flujoDeEntrada);

            flujoDeSalida = socketCliente.getOutputStream();
            flujoO = new DataOutputStream(flujoDeSalida);
            transferencia(flujoI, flujoO);

//            socketCliente.close();
        } catch (UnknownHostException e) {
        	
        } catch (SecurityException e) {
            System.out.println("Comunicacion no permitida por razones de seguridad");
            System.exit(0);
        } catch (IOException e) {
			
			e.printStackTrace();
		}

    }

    public abstract void transferencia(DataInputStream flujoI, DataOutputStream flujoO);

}
