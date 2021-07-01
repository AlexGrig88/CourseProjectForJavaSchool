package com.alexgrig.controllers;

import com.alexgrig.controllers.dto.ReactClientDTO;
import com.alexgrig.models.Client;
import com.alexgrig.services.CardService;
import com.alexgrig.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/")
public class ReactClientController {

    private final ClientService clientService;

    private final CardService cardService;


    public ReactClientController(@Autowired ClientService clientService, @Autowired CardService cardService) {
        this.clientService = clientService;
        this.cardService = cardService;

    }

    @GetMapping("/hello")
    public String helloSpring() {
        return "Hello Spring";
    }

    // create employee rest api
    @PostMapping("/clients")
    public String createClient(@RequestBody ReactClientDTO reactClient) {
        System.out.println("===========================================");
        System.out.println(reactClient.getFirstName() + "\n" + reactClient.getDateOfBirth());

        Client client = clientService.createClient(reactClient.getFirstName(), reactClient.getLastName(), reactClient.getPatronymic(),
              reactClient.getPhone(), reactClient.getEmail(), reactClient.getDateOfBirth());
        //если клиент уже есть в базе, вернём ему сообщение, что карта уже оформлена
        if (client == null) {
            return "Карта на клиента " + reactClient.getFirstName() + " " + reactClient.getLastName() + " уже оформлена.";
        }
        int cardCreated = cardService.createCard(client, reactClient.getCardSpicific(), reactClient.getPaymentSystem());

        if (cardCreated == 1) {
            return String.format("Поздравляем вас, %s %s, с успешным созданием карты!", client.getLastName(), client.getFirstName());
        }
        return "Карта не зоздана";
    }
}
