package org.produit.exception;

public class InvalidMoneyOperationException extends RuntimeException {
    public InvalidMoneyOperationException(String message) {
        super(message);
    }
}
