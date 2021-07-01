package com.alexgrig.repositories;


import com.alexgrig.models.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class JdbcCardRepository implements CardRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public int save(Card card) {
        return jdbcTemplate.update(
                "INSERT INTO cards (card_number, pin, card_specific, balance, creation_date, expiration_date, client_id)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)", card.getCardNumber(), card.getPin(), card.getCardSpecific(),
                                card.getBalance(), card.getCreationDate(), card.getExpirationDate(),
                                card.getOwner().getId());
    }

    @Override
    public int update(Card card) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public List<Card> findAll() {
        return null;
    }

    @Override
    public List<String> findAllCardNumbers() {
        return jdbcTemplate.queryForList("SELECT card_number FROM cards", String.class);
    }

    @Override
    public Optional<Card> findById(Long id) {
        return Optional.empty();
    }
}
