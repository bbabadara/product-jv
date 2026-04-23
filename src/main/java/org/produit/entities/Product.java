package org.produit.entities;

import org.produit.exception.InvalidProductException;
import org.produit.valueobjects.Money;
import org.produit.valueobjects.SKU;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private final UUID id;
    private final SKU sku;
    private final String name;

    public SKU getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    private Money price;

    public Product(SKU sku, String name, Money price) {
        if (sku == null || name == null || price == null) {
            throw new InvalidProductException("Les paramètres ne peuvent pas être null");
        }
        if ( name.isBlank()) {
            throw new InvalidProductException("Le nom ne peut pas être vide");
        }
        if (price == null){
            throw new InvalidProductException("Le prix ne peut pas être null");
        }
        this.id = UUID.randomUUID();
        this.sku = sku;
        this.name = name;
        this.price = price;
    }
    public void applyDiscount(BigDecimal percentage) {
        this.price = this.price.applyPercentageDiscount(percentage);

    }
}
