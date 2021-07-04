package com.alexgrig.services;

import com.alexgrig.models.Client;
import com.alexgrig.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ClientService {

    private long countClients;
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(@Qualifier("jdbcClientRepository")ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client createClient(String fN, String lN, String patronymic, String phone, String email, String dob) {
        countClients = this.clientRepository.count();
        Client client = new Client(++countClients, fN, lN, patronymic, phone, email, LocalDate.parse(dob));
        int clientSaved = clientRepository.save(client);
        //если клиент успешно добавился вернём его в контроллер, для этого нам нужен реальный id из бд
        //т.к. на него будет ссылаться карта по внешнему ключу
        if (clientSaved == 1) {
            return clientRepository.findByPhone(phone).get();
        }
        //если клиент не сохранился
        return null;
    }

    public int deleteClientById(Long id) {
        return clientRepository.deleteById(id);
    }
}
