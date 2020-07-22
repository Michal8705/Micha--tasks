package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private TrelloBadges trelloBadges;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Test
    public void shouldfetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("1", "name1", false));

        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.add(new TrelloBoardDto("1", "name1", trelloListsDto));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDto);
        //When
        List<TrelloBoardDto> list = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(1, list.size());

    }

    @Test
    public void shouldcreatedTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name1", "description1",
                "pos1", "listId1");

        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(
                "1", "name1", "http://test.com",  new TrelloBadges(1,trelloBadges.getAttachmentsByType()));

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        //When
        CreatedTrelloCardDto card = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertEquals("1", card.getId());
        assertEquals("name1", card.getName());
        assertEquals("http://test.com", card.getShortUrl());
        assertEquals(1, card.getBadges().getVotes());
    }
}