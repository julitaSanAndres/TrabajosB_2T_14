package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author profesor
 */
public abstract class TCPServidor {

    private DataInputStream flujoI;
    private DataOutputStream flujoO;
    private ServerSocket socketServidor;

    public TCPServidor() {
        InputStream flujoDeEntrada;
        OutputStream flujoDeSalida;
        
        Socket comunicaConCliente;
        try {
            socketServidor = new ServerSocket(8000);
            System.out.println("Escuchando el puerto 8000");
			System.out.println("En Espera....");
            comunicaConCliente = socketServidor.accept();
//El metodo Accept interrumpe la ejecucion de la aplicacion
            System.out.println("Comunicacion establecida");
            flujoDeEntrada = comunicaConCliente.getInputStream();
            flujoI = new DataInputStream(flujoDeEntrada);

            flujoDeSalida = comunicaConCliente.getOutputStream();
            flujoO = new DataOutputStream(flujoDeSalida);

            transferencia(flujoI, flujoO);

            comunicaConCliente.close();
            socketServidor.close();

        } catch (IOException e) {
            System.out.println("Error en las comunicaciones");
            System.exit(0);
        } catch (SecurityException e) {
            System.out.println("Comunicacion no permitida por razones de seguridad");
            System.exit(0);
        }

    }
    

    

//    public DataInputStream getFlujoDeEntrada() {
//        return flujoI;
//    }
//
//    public DataOutputStream getFlujoDeSalida() {
//        return flujoO;
//    }
    public abstract void transferencia(DataInputStream flujoI, DataOutputStream flujoO);

}
