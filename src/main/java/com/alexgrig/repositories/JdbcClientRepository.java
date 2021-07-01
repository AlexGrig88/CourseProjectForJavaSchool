package com.alexgrig.repositories;

import com.alexgrig.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcClientRepository implements ClientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM clients", Integer.class);
    }

    @Override
    public int save(Client client) {
        int saved = 0;
        try {
            saved = jdbcTemplate.update(
                    "INSERT INTO clients (first_name, last_name, patronymic, phone, email, dateOfBirth)" +
                            "VALUES (?, ?, ?, ?, ?, ?)", client.getFirstName(), client.getLastName(),
                    client.getPatronymic(), client.getPhone(), client.getEmail(), client.getDateOfBirth());
        } catch (org.springframework.dao.DuplicateKeyException ex) {
            System.out.println("========================");
            System.out.println(ex.getMessage());
            saved = 0;
        }
        return saved;
    }
    @Override
    public Optional<Client> findByPhone(String phone) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM clients WHERE phone = ?",
                ((rs, rowNum) -> Optional.of(new Client(
                        rs.getLong("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("patronymic")
                ))
                ),
                phone);
    }


    @Override
    public int update(Client client) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public List<Client> findAll() {
        return null;
    }

    @Override
    public Optional<Client> findById(Long id) {
        return Optional.empty();
    }

}
