package es.examen.JMS_unidad1.ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Clase servidor
 */
public class Ejercicio2Servidor
{
    public static final int PORT = 8080;

    public static void main(String[] args) {
        ServerSocket socketServidor = null;
        Socket conexionCliente = null;
        DataOutputStream dataOutputStream = null;
        DataInputStream flujoEntrada = null;

        try
        {
            /**
             * Creamos el objetos:
             * Socket ->establecer conexion con el servidor
             * DataInputStream ->Para recibir respuestas del servidor
             * DataOutputStream ->para enviar respuestas al servidor
             */
            socketServidor = new ServerSocket(PORT);

            conexionCliente = socketServidor.accept();

            dataOutputStream = new DataOutputStream(conexionCliente.getOutputStream());
            //Mandamos mensaje para ver que la conexion es correcta
            dataOutputStream.writeUTF("Hola cliente, Conexión recibida");

            //Recibimos numeros
            flujoEntrada = new DataInputStream(conexionCliente.getInputStream());

            //Guardamos los numero en una lista
            List<Integer> listaNumeros = new ArrayList<>();
            int numero = -1;

            while (numero != 0) {

                numero = flujoEntrada.readInt();

                if (numero != 0) {
                    listaNumeros.add(numero);
                }

                dataOutputStream.writeUTF("Recibido");

            }

            //Gestionamos la lista, y enviamos al cliente la solución
            String resultado = "Su lista ordenada: " + ordenaNumeros(listaNumeros);

            dataOutputStream.writeUTF(resultado);

        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
        finally
        {

            if (dataOutputStream != null)
            {
                try
                {
                    dataOutputStream.close();
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }

            if (conexionCliente != null)
            {
                try
                {
                    conexionCliente.close();
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }

            if (socketServidor != null)
            {
                try
                {
                    socketServidor.close();
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }
        }
    }

    /**
     * Metodo para ordenar los numero de la lista
     * @param listaNumeros lista de numeros
     * @return cadena de texto con los numeros ordenados
     */
    public static String ordenaNumeros(List<Integer>listaNumeros)
    {
        listaNumeros.sort(Comparator.naturalOrder());
        String resultado="";

        for(int i : listaNumeros)
        {
            resultado += i +" ";
        }

        return resultado;
    }
}
