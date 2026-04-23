package org.produit.exception;

public class InvalidSKUException extends RuntimeException {
    public InvalidSKUException(String message) {
        super(message);
    }
}