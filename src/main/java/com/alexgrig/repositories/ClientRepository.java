package com.alexgrig.repositories;

import com.alexgrig.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    int count();
    int save(Client client);
    int update(Client client);
    int deleteById(Long id);

    List<Client> findAll();
    Optional<Client> findById(Long id);
    Optional<Client> findByPhone(String phone);

}
