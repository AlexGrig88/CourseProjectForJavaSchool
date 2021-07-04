package com.alexgrig.repositories;


import com.alexgrig.models.Card;
import com.alexgrig.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcCardRepository implements CardRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("jdbcClientRepository")
    private ClientRepository clientRepository;


    @Override
    public int save(Card card) {
        return jdbcTemplate.update(
                "INSERT INTO cards (card_number, pin, card_specific, payment_system, balance, creation_date, expiration_date, client_id)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)", card.getCardNumber(), card.getPin(), card.getCardSpecific(),
                card.getPaymentSystem(), card.getBalance(), card.getCreationDate(), card.getExpirationDate(),
                card.getOwner().getId());
    }

    @Override
    public int update(Card card) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM cards WHERE id = ?", id);
    }

    @Override
    public List<Card> findAll() {
        return jdbcTemplate.query(
                "SELECT *  FROM cards",
                (rs, riwNum) ->
                        new Card(
                                rs.getLong("id"),
                                rs.getString("pin"),
                                rs.getBigDecimal("balance"),
                                rs.getString("card_number"),
                                rs.getString("card_specific"),
                                rs.getString("payment_system"),
                                rs.getTimestamp("creation_date").toLocalDateTime(),
                                rs.getTimestamp("expiration_date").toLocalDateTime(),
                                clientRepository.findById(rs.getLong("client_id")).get()
                        )
        );
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
