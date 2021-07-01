package com.alexgrig.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter
public class Card {

    private Long id;

    private String pin;
    private BigDecimal balance;

    private Client owner;
    private String cardNumber;
    private String cardSpecific;
    private String paymentSystem;
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;

    public Card(Client owner, String cardSpecific, String paymentSystem) {
        this.owner = owner;
        this.cardSpecific = cardSpecific;
        this.paymentSystem = paymentSystem;
        this.creationDate = LocalDateTime.now();
        this.expirationDate = LocalDateTime.now().plusYears(2);
    }
}
