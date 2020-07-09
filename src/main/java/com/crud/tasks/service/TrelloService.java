package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrelloService {

//    public static final String SUBJECT = "new trello card";
//
//    @Autowired
//    private AdminConfig adminConfig;
//
//    @Autowired
//    private TrelloClient trelloClient;
//
//    @Autowired
//    private SimpleEmailService emailService;
//
//    public CreatedTrelloCard createdTrelloCard(final TrelloCardDto trelloCardDto){
//        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);
//
//        ofNullable(newCard).ifPresent(card-> emailService.send(new Mail(adminConfig.getAdminMail(),SUBJECT,
//                        "New card: "+card.getName() + "has been created on your Trello account")));
//
//        return newCard;
//    }
}
