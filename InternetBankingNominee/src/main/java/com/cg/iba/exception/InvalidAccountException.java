package com.cg.iba.exception;

public class InvalidAccountException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidAccountException(String msg) {
        super(msg);
    }

}
