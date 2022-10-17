package es.examen.JMS_unidad1.ejercicio3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Clase cliente
 */
public class Ejercicio3Cliente
{
    private static final Logger logger = LogManager.getLogger();
    private static  final String HOST = "localhost";

    public static void main(String[] args)
    {
        Socket conexionServidor = null;
        DataInputStream flujoEntrada = null;
        DataOutputStream dataOutputStream = null;
        Scanner sc = null;

        try
        {
            /**
             * Creamos el objetos:
             * Socket ->establecer conexion con el servidor
             * DataInputStream ->Para recibir respuestas del servidor
             * DataOutputStream ->para enviar respuestas al servidor
             * Scanner -> para capturar texto
             */
            conexionServidor = new Socket(HOST, 8080);
            flujoEntrada = new DataInputStream(conexionServidor.getInputStream());
            dataOutputStream = new DataOutputStream(conexionServidor.getOutputStream());
            sc = new Scanner(System.in);
            logger.info(flujoEntrada.readUTF()) ;

            /*Capturamos y enviamos una frase*/
            String frase ="";


            System.out.println("Introduzca frase:");

            frase = sc.nextLine();

            dataOutputStream.writeUTF(frase.toLowerCase());

            /* Recivimos la respuesta del server*/
            System.out.println(flujoEntrada.readUTF());

        }
        catch(InputMismatchException | IOException exception)
        {

            logger.error(exception);

        }
        finally
        {

            sc.close();


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
            if (flujoEntrada != null)
            {
                try
                {
                    flujoEntrada.close();
                }
                catch (IOException ioException)
                {
                    logger.error(ioException);
                }
            }

            if (conexionServidor != null)
            {
                try
                {
                    conexionServidor.close();
                }
                catch (IOException ioException)
                {
                    logger.error(ioException);
                }
            }
        }
    }
}
