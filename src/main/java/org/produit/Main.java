package org.produit;

import org.produit.entities.Product;
import org.produit.exception.InvalidCurrencyException;
import org.produit.exception.InvalidMoneyOperationException;
import org.produit.exception.InvalidProductException;
import org.produit.exception.InvalidSKUException;
import org.produit.valueobjects.Money;
import org.produit.valueobjects.SKU;

import java.math.BigDecimal;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("=== DEMO: Tests de validation du catalogue E-commerce ===\n");
        // 1. Test création VALIDE
        try {
            Money prix = new Money(new BigDecimal("10000"), "FCFA");
            SKU sku = new SKU("TEC-999999");
            Product produit = new Product(sku, "Ordinateur Portable", prix);
            System.out.println("✓ Produit créé: " + produit.getName() + " [" + produit.getSku().value() + "] - " + produit.getPrice());
        } catch (Exception e) {
            System.out.println("✗ Erreur création valide: " + e.getMessage());
        }
        // 2. Test: SKU invalide (format regex)
        System.out.println("\n--- Test: SKU invalide ---");
        try {
            new SKU("abc-123");
        } catch (InvalidSKUException e) {
            System.out.println("✓ Exception capturée: " + e.getMessage());
        }
        // 3. Test: Nom vide
        System.out.println("\n--- Test: Nom vide ---");
        try {
            new Product(new SKU("ABC-1234"), "", new Money(BigDecimal.TEN, "EUR"));
        } catch (InvalidProductException e) {
            System.out.println("✓ Exception capturée: " + e.getMessage());
        }
        // 4. Test: Montant négatif
        System.out.println("\n--- Test: Montant négatif ---");
        try {
            new Money(new BigDecimal("-100"), "FCFA");
        } catch (InvalidMoneyOperationException e) {
            System.out.println("✓ Exception capturée: " + e.getMessage());
        }
        // 5. Test: Devise invalide
        System.out.println("\n--- Test: Devise invalide ---");
        try {
            new Money(BigDecimal.TEN, "USD");
        } catch (InvalidCurrencyException e) {
            System.out.println("✓ Exception capturée: " + e.getMessage());
        }
        // 6. Test: Addition devises mixing
        System.out.println("\n--- Test: Addition devises mixing ---");
        try {
            Money m1 = new Money(new BigDecimal("100"), "FCFA");
            Money m2 = new Money(new BigDecimal("50"), "EUR");
            m1.add(m2);
        } catch (InvalidMoneyOperationException e) {
            System.out.println("✓ Exception capturée: " + e.getMessage());
        }
        // 7. Test: applyDiscount hors plage
        System.out.println("\n--- Test: Discount invalide (trop grand) ---");
        try {
            Money prix = new Money(new BigDecimal("10000"), "FCFA");
            Product p = new Product(new SKU("PRO-1234"), "Test", prix);
            p.applyDiscount(new BigDecimal("150")); // > 100
        } catch (InvalidMoneyOperationException e) {
            System.out.println("✓ Exception capturée: " + e.getMessage());
        }
        System.out.println("\n=== Fin des tests ===");
    }
}