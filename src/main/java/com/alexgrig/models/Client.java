package com.alexgrig.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Client {

    private Long id;

    private String firstName;

    private String lastName;
    private String patronymic;

    private String phone;

    private String email;

    private LocalDate dateOfBirth;

    private List<Card> cards;

//    @OneToOne(mappedBy = "owner")
//    private Passport passport;




    public Client(Long id, String firstName, String lastName, String patronymic, String phone,
                  String email, LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.phone = phone;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
