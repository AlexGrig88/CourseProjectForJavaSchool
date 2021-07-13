package com.alexgrig.repositories;

import com.alexgrig.models.Card;


import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CardRepository {

    int save(Card card);
    int updateSetTrueForClosed(Card card);
    int deleteById(Long id);
    Optional<Card> findById(Long id);

    List<Card> findAll();
    List<String> findAllCardNumbers();

}
