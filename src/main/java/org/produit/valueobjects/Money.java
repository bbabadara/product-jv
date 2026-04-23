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

}
