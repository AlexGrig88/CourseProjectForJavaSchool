package com.alexgrig.services;

import com.alexgrig.models.Card;
import com.alexgrig.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Component
public class ScheduledClientNotificationService {

    private LocalDateTime fastChangingTime;
    private final EmailSenderService emailSenderService;
    private final CardRepository cardRepository;

    @Autowired
    public ScheduledClientNotificationService(EmailSenderService emailSenderService,
                                              @Qualifier("jdbcCardRepository") CardRepository cardRepository) {
        this.emailSenderService = emailSenderService;
        this.cardRepository = cardRepository;

        this.fastChangingTime = LocalDateTime.now();
    }


    @Scheduled(initialDelay = 4000L, fixedDelay = 500L)
    public void sendEmail() {

        List<Card> list = cardRepository.findAll();

        //карта выдаётся на 12 месяцев
        //моделирую быстро текущее время
        fastChangingTime = fastChangingTime.plusMonths(1);

        for (Card card : list) {
            Period period = Period.between(fastChangingTime.toLocalDate(), card.getExpirationDate().toLocalDate());
            int remainderOfMonths = period.getYears() * 12 + period.getMonths();
            //если остался 1 месяц до истечения срока действия карты отправляем на почту сообщение
            if (remainderOfMonths == 1) {
                String messageBody = String.format("Добрый день %s %s! Сообщаем вам, что срок действия вашей карты истекает.\n%s",
                        card.getOwner().getLastName(), card.getOwner().getFirstName(), fastChangingTime.toString());

                emailSenderService.sendSimpleEmail(
                        card.getOwner().getEmail(),
                        messageBody,
                        "Сообщение от банка GreenBank");
                System.out.println("Письмо отправлено " + card.getOwner().getLastName());
            }
            if (remainderOfMonths == 0) {
                System.out.println("Карта закрыта");
                cardRepository.updateSetTrueForClosed(card);
            }
        }

        System.out.println("Время летит: " + fastChangingTime);
    }
}
