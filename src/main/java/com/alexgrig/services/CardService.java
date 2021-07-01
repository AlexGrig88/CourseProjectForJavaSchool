package com.alexgrig.services;

import com.alexgrig.models.Card;
import com.alexgrig.models.Client;
import com.alexgrig.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


@Service
public class CardService {

    private Set<String> uniqueCardNumbers;
    private final CardRepository cardRepository;

    @Autowired
    public CardService(@Qualifier("jdbcCardRepository")CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public int createCard(Client owner, String cardSpecific, String paymentSystem) {
        Card card = new Card(owner, cardSpecific, paymentSystem);
        card.setBalance(BigDecimal.valueOf(0.0));
        card.setPin("1234");
        card.setCardNumber(genericNewCardNumber());
        return cardRepository.save(card);
    }

    private String genericNewCardNumber() {
        uniqueCardNumbers = new HashSet<>(cardRepository.findAllCardNumbers());
        if (uniqueCardNumbers == null || uniqueCardNumbers.size() == 0) {
            return "4000123499995555";
        }
        Random random = new Random();

        //Bank Identification Number (BIN)
        String bin = "400000";
        //Customer account number
        String customerAccNum = "";
        for (int i = 0; i < 9; i++) {
            customerAccNum += Integer.toString(random.nextInt(10));
        }
        //checksum. It is used to validate the credit card number using the Luhn algorithm.
        String checkSum = Integer.toString(random.nextInt(10));

        String targetNumber = bin + customerAccNum + checkSum;

        int sizeBefore = uniqueCardNumbers.size();
        uniqueCardNumbers.add(targetNumber);
        int sizeAfter = uniqueCardNumbers.size();

        //проверяем, если такой номер уже существует генерируем заново
        while (sizeAfter == sizeBefore) {
            customerAccNum = "";
            for (int i = 0; i < 9; i++) {
                customerAccNum += Integer.toString(random.nextInt(10));
            }
            checkSum = Integer.toString(random.nextInt(10));
            targetNumber = bin + customerAccNum + checkSum;
            uniqueCardNumbers.add(targetNumber);
            sizeAfter = uniqueCardNumbers.size();
        }

        return targetNumber;

    }
}
