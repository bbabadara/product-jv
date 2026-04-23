package org.produit.valueobjects;

import org.produit.exception.InvalidCurrencyException;
import org.produit.exception.InvalidMoneyOperationException;

import java.math.BigDecimal;
import java.util.Set;

public record Money(BigDecimal amount, String currency) {
    private static final Set<String> ALLOWED = Set.of("FCFA", "EUR");

    public Money {
        if (amount == null) {
            throw new InvalidMoneyOperationException("Le montant ne peut pas être null");
        }
        if (amount.signum() < 0) {
            throw new InvalidMoneyOperationException("Le montant ne peut pas être négatif");
        }

        if (!ALLOWED.contains(currency) || currency == null) {
            throw new InvalidCurrencyException("La devise ne peut être que \"FCFA\" ou \"EUR\" : " + currency);
        }

    }

    public Money add(Money other) {
        if (other==null){
            throw new InvalidMoneyOperationException("L'opération ne peut pas être null");
        }

        if (!this.currency.equals(other.currency)) {
            throw new InvalidMoneyOperationException("Les devises ne sont pas les mêmes");
        }
        return new Money(this.amount.add(other.amount), this.currency);
    }

    public Money applyPercentageDiscount(BigDecimal percentage) {
        if (percentage == null ||
                percentage.compareTo(BigDecimal.valueOf(0.1)) < 0 ||
                percentage.compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new InvalidMoneyOperationException("le pourcentage est compris entre 0.1 and 100");
        }

        BigDecimal discountFactor = BigDecimal.ONE.subtract(
                percentage.divide(BigDecimal.valueOf(100))
        );

        BigDecimal newAmount = this.amount.multiply(discountFactor);

        return new Money(newAmount, this.currency);
    }

}
