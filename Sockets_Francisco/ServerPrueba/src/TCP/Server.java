package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TCPServidor servidor = new TCPServidor() {
			
			@Override
			public void transferencia(DataInputStream flujoI,
					DataOutputStream flujoO) {
				 String textoRecibido = "";
	                int i = 1;
	                do {
	                    try {
	                        textoRecibido = flujoI.readUTF();
	                        //Proceso los datos leidos
	                        System.out.println(textoRecibido);
	                        flujoO.writeUTF("Longitud de la cadena: " + String.valueOf(textoRecibido.length()));
	                        i++;
	                    } catch (IOException ex) {
	                      ex.printStackTrace(); 
	                    }
	                } while (!textoRecibido.equalsIgnoreCase("fin"));
			}
				// TODO Auto-generated method stub
				
			
        };

	}

}
