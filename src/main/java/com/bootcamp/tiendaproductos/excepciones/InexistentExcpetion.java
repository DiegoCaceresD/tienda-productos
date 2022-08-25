package com.bootcamp.tiendaproductos.excepciones;

public class InexistentExcpetion extends Exception{
    public InexistentExcpetion() {
    }

    public InexistentExcpetion(String message) {
        super(message);
    }

    public InexistentExcpetion(String message, Throwable cause) {
        super(message, cause);
    }

    public InexistentExcpetion(Throwable cause) {
        super(cause);
    }

    public InexistentExcpetion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
