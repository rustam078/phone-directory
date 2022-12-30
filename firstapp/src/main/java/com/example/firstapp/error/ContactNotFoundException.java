package com.example.firstapp.error;

public class ContactNotFoundException extends Exception {

    public ContactNotFoundException() {
        super();
    }

    public ContactNotFoundException(String message) {
        super(message);
    }

    public ContactNotFoundException(String message,Throwable cause) {
        super(message,cause);
    }

    public ContactNotFoundException(Throwable cause,String message) {
        super(message,cause);
    }


    public ContactNotFoundException(Throwable cause) {
        super(cause);
    }


}
