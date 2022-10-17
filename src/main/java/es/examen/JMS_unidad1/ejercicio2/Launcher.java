package es.examen.JMS_unidad1.ejercicio2;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Launcher
{
    /**
     * Logger of the class
     */
	private static final Logger logger = LogManager.getLogger();
	
	public static void main(String[] args)
	{
		logger.trace("I'm at trace level") ;
        logger.debug("I'm at debug level") ;
        logger.info("I'm at info level") ;
        logger.warn("I'm at warn level") ;
        logger.error("I'm at error level") ;
        logger.fatal("I'm at fatal level") ;
        
        logger.info("Generating random values: {} and {}", Launcher.getNumber(), Launcher.getNumber());

    }
	
	private static int getNumber()
	{
        return new Random().nextInt() ;
    }
}