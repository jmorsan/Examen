package es.examen.JMS_unidad1.ejercicio3;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;


/**
 * Clase servidor
 */
public class Ejercicio3Servidor
{
    private static final Logger logger = LogManager.getLogger();
    public static final int PORT = 8080;

    public static void main(String[] args)
    {

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
            dataOutputStream.writeUTF("Hola cliente, Conexión recibida");


            flujoEntrada = new DataInputStream(conexionCliente.getInputStream());

            //Recibimos palabras y la guardamos en un variable
            String frase= flujoEntrada.readUTF();

            //Resolvemos lo que nos pide y lo concatenamos guardandolo en un String
            String resultado = "Palabras: "+contarPalabras(frase)+" ,Vocales: "+ contarVocales(frase)+
                    " ,Consonantes: "+contarConsonantes(frase);


            //Enviamos el resultado de la gestion que ha hecho el server del problema
            dataOutputStream.writeUTF(resultado);


        }
        catch(IOException ioException)
        {
            logger.error(ioException);
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
                    logger.error(ioException);
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
                    logger.error(ioException);
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
                    logger.error(ioException);
                }
            }
        }
    }

    /**
     * para contar vocales de un String
     * @param frase frase recibida por el servidor
     * @return numero de vocales
     */
    public static int contarVocales(String frase)
    {
        int contvocales=0;
        for(int i=0;i<frase.length();i++)
        {
            if ((frase.charAt(i)=='a') || (frase.charAt(i)=='e') || (frase.charAt(i)=='i') ||
                    (frase.charAt(i)=='o') || (frase.charAt(i)=='u'))
            {
                contvocales++;
            }
        }


        return contvocales;

    }

    /**
     * Metodo para contar consonantes de un String
     * @param frase frase recibida por el servidor
     * @return numero de consonantes
     */
    public static int contarConsonantes(String frase)
    {
        int contconsonantes=0;
        for(int i=0;i<frase.length();i++) {
            if ((frase.charAt(i)!='a') && (frase.charAt(i)!='e') && (frase.charAt(i)!='i') &&
                    (frase.charAt(i)!='o') && (frase.charAt(i)!='u') && (frase.charAt(i)!=' '))
            {
                contconsonantes++;
            }
        }



        return contconsonantes;

    }

    /**
     * Metodo para contar palabras de una frase
     * @param frase frase recibida por el servidor
     * @return numero de palabras
     */
    public static int contarPalabras(String frase)
    {
        StringTokenizer st = null;
        int contpalabras=0;

        st = new StringTokenizer(frase);

        while(st.hasMoreTokens())
        {
            contpalabras++;
            st.nextToken();
        }

        return contpalabras;
    }
}
