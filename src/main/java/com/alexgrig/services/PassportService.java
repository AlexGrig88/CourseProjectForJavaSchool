package com.alexgrig.services;

import com.alexgrig.models.Client;
import com.alexgrig.models.Passport;
import com.alexgrig.repositories.JpaPassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PassportService {
    private final JpaPassportRepository passportRepository;

    @Autowired
    public PassportService(JpaPassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

    public Long createPassport(int series, int numberOfPassport,
                               String dateOfIssue, Client owner) {
        Passport passport = new Passport(series, numberOfPassport, LocalDate.parse(dateOfIssue), owner);
        return passportRepository.save(passport).getId();
    }
}
