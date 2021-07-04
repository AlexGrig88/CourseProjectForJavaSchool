package com.alexgrig.controllers;

import com.alexgrig.controllers.dto.ReactClientDTO;
import com.alexgrig.models.Client;
import com.alexgrig.services.CardService;
import com.alexgrig.services.ClientService;
import com.alexgrig.services.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/")
public class ReactClientController {

    private final ClientService clientService;

    private final CardService cardService;
    private final PassportService passportService;


    public ReactClientController(@Autowired ClientService clientService,
                                 @Autowired CardService cardService,
                                 @Autowired PassportService passportService) {
        this.clientService = clientService;
        this.cardService = cardService;
        this.passportService = passportService;

    }

    @GetMapping("/hello")
    public String helloSpring() {
        return "Hello, Spring";
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

        cardService.createCard(client, reactClient.getCardSpicific(), reactClient.getPaymentSystem());
        passportService.createPassport(reactClient.getSeriesOfPassport(), reactClient.getNumberOfPassport(),
                reactClient.getDateOfIssueOfPassport(), client);

        return String.format("Поздравляем вас, %s %s, с успешным созданием карты!", client.getLastName(), client.getFirstName());

    }

    @GetMapping("/delete")
    public String clientDelete(@RequestParam(value = "id", required = false) Long id) {
        System.out.println("---delete----");
        clientService.deleteClientById(id);
        //cardService.deleteCardById(id);
        return "Client with id = " + id + " deleted!";
    }
}
