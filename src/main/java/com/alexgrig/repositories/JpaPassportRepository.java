package com.alexgrig.repositories;

import com.alexgrig.models.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPassportRepository extends JpaRepository<Passport, Long> {
}
