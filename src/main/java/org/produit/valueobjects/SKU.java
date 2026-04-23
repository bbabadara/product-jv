package org.produit.valueobjects;

import org.produit.exception.InvalidSKUException;

public record SKU(String value) {
    private static final String REGEX = "^[A-Z]{3}-\\d{4,6}$";
    public SKU {
        if (!value.matches(REGEX) || value == null) {
            throw new InvalidSKUException("Le format du SKU est invalide (Format attendu : AAA-1234 à AAA-123456) : " + value);
        }
    }
}
