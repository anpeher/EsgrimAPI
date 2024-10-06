package com.example.esgrimAPI.Excepciones;
/**
 * excepcion personalizada que se utiliza al enviar un SMS.
 */
public class SmsException extends RuntimeException{
    /**
     * Constructor de la excepcion con un string, que es el mensaje de la excepcion.
     *
     * @param message El mensaje detallado de la excepcion.
     */
    public SmsException(String message) {
        super(message);
    }
}
