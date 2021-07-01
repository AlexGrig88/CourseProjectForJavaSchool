package com.alexgrig.controllers.dto;

import com.alexgrig.models.Client;

public class ReactClientDTO {

    private String firstName;
    private String lastName;
    private String patronymic;
    private String phone;
    private String email;
    private int seriesOfPassport;
    private int numberOfPassport;
    private String cardSpicific;
    private String dateOfBirth;
    private String dateOfIssueOfPassport;
    private boolean termsOfService;
    private String fullNameOnTheCard;
    private String paymentSystem;

    public ReactClientDTO(String firstName, String lastName, String patronymic, String phone,
                            String email, int seriesOfPassport, int numberOfPassport,
                            String cardSpicific, String dateOfBirth, String dateOfIssueOfPassport,
                            boolean termsOfService, String fullNameOnTheCard, String paymentSystem) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.phone = phone;
        this.email = email;
        this.seriesOfPassport = seriesOfPassport;
        this.numberOfPassport = numberOfPassport;
        this.cardSpicific = cardSpicific;
        this.dateOfBirth = dateOfBirth;
        this.dateOfIssueOfPassport = dateOfIssueOfPassport;
        this.termsOfService = termsOfService;
        this.fullNameOnTheCard = fullNameOnTheCard;
        this.paymentSystem = paymentSystem;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSeriesOfPassport() {
        return seriesOfPassport;
    }

    public void setSeriesOfPassport(int seriesOfPassport) {
        this.seriesOfPassport = seriesOfPassport;
    }

    public int getNumberOfPassport() {
        return numberOfPassport;
    }

    public void setNumberOfPassport(int numberOfPassport) {
        this.numberOfPassport = numberOfPassport;
    }

    public String getCardSpicific() {
        return cardSpicific;
    }

    public void setCardSpicific(String cardSpicific) {
        this.cardSpicific = cardSpicific;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfIssueOfPassport() {
        return dateOfIssueOfPassport;
    }

    public void setDateOfIssueOfPassport(String dateOfIssueOfPassport) {
        this.dateOfIssueOfPassport = dateOfIssueOfPassport;
    }

    public boolean isTermsOfService() {
        return termsOfService;
    }

    public void setTermsOfService(boolean termsOfService) {
        this.termsOfService = termsOfService;
    }

    public String getFullNameOnTheCard() {
        return fullNameOnTheCard;
    }

    public void setFullNameOnTheCard(String fullNameOnTheCard) {
        this.fullNameOnTheCard = fullNameOnTheCard;
    }

    public String getPaymentSystem() {
        return paymentSystem;
    }

    public void setPaymentSystem(String paymentSystem) {
        this.paymentSystem = paymentSystem;
    }

    public Client parseClient() {
        return new Client();
    }

    @Override
    public String toString() {
        return "ClientRequestDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", seriesOfPassport=" + seriesOfPassport +
                ", numberOfPassport=" + numberOfPassport +
                ", cardSpicific='" + cardSpicific + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", dateOfIssueOfPassport='" + dateOfIssueOfPassport + '\'' +
                ", termsOfService=" + termsOfService +
                ", fullNameOnTheCard='" + fullNameOnTheCard + '\'' +
                ", paymentSystem='" + paymentSystem + '\'' +
                '}';
    }
}
