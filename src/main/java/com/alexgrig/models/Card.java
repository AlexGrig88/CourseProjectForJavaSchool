package com.alexgrig.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="cards")
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pin;
    private BigDecimal balance;
    @Column(name="card_number", unique = true)
    private String cardNumber;
    @Column(name="card_specific")
    private String cardSpecific;
    @Column(name="payment_system")
    private String paymentSystem;
    @Column(name="creation_date")
    private LocalDateTime creationDate;
    @Column(name="expiration_date")
    private LocalDateTime expirationDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client owner;

    public Card(Client owner, String cardSpecific, String paymentSystem) {
        this.owner = owner;
        this.cardSpecific = cardSpecific;
        this.paymentSystem = paymentSystem;
        this.creationDate = LocalDateTime.now();
        this.expirationDate = LocalDateTime.now().plusYears(1);
    }

    public Card(Long id, String pin, BigDecimal balance, String cardNumber,
                String cardSpecific, String paymentSystem, LocalDateTime creationDate,
                LocalDateTime expirationDate, Client owner) {
        this.id = id;
        this.pin = pin;
        this.balance = balance;
        this.cardNumber = cardNumber;
        this.cardSpecific = cardSpecific;
        this.paymentSystem = paymentSystem;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", pin='" + pin + '\'' +
                ", balance=" + balance +
                ", owner=" + owner +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardSpecific='" + cardSpecific + '\'' +
                ", paymentSystem='" + paymentSystem + '\'' +
                ", creationDate=" + creationDate +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
