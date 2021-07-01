package com.alexgrig;

import com.alexgrig.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class ApplicationCard implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationCard.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //создание таблиц в бд, если таковой нет
        jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS clients " + "(" +
                        "id SERIAL PRIMARY KEY, " +
                        "first_name VARCHAR(255)," +
                        "last_name VARCHAR(255), " +
                        "patronymic VARCHAR(255), " +
                        "phone VARCHAR(15) UNIQUE CHECK(phone !=''), " +
                        "email VARCHAR(50) UNIQUE CHECK(email !=''), " +
                        "dateOfBirth DATE" + ")"
        );

        jdbcTemplate.execute(
                "CREATE TABLE IF NOT EXISTS cards " + "(" +
                        "id SERIAL PRIMARY KEY, " +
                        "card_number VARCHAR(20) UNIQUE, " +
                        "pin CHAR(4), " +
                        "card_specific VARCHAR(25), " +
                        "balance NUMERIC(13,2), " +
                        "creation_date TIMESTAMP, " +
                        "expiration_date TIMESTAMP, " +
                        "client_id INTEGER REFERENCES clients (id) ON DELETE CASCADE" +
                        ")"
        );

    }
}
