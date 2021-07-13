package com.alexgrig.repositories;

import com.alexgrig.models.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPassportRepository extends JpaRepository<Passport, Long> {
}
