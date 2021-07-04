package com.alexgrig.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "passports")
public class Passport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "series")
    private int seriesOfPassport;
    @Column(name = "number")
    private int numberOfPassport;
    @Column(name = "date_of_issue")
    private LocalDate dateOfIssueOfPassport;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
//            optional = false)
//    @JoinColumn(name = "client_id")
//    private Client owner;

    public Passport(int seriesOfPassport, int numberOfPassport, LocalDate dateOfIssueOfPassport, Client owner) {
        this.seriesOfPassport = seriesOfPassport;
        this.numberOfPassport = numberOfPassport;
        this.dateOfIssueOfPassport = dateOfIssueOfPassport;
        //this.owner = owner;
    }
}
