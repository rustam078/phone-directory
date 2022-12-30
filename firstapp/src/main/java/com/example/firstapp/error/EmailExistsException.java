package com.example.firstapp.error;


public class EmailExistsException extends Exception {

    public EmailExistsException() {
        super();
    }

    public EmailExistsException(String message) {
        super(message);
    }

    public EmailExistsException(String message,Throwable cause) {
        super(message,cause);
    }

    public EmailExistsException(Throwable cause,String message) {
        super(message,cause);
    }


    public EmailExistsException(Throwable cause) {
        super(cause);
    }

    // HttpStatus.ALREADY_REPORTED


}
