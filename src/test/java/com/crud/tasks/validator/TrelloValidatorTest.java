package com.crud.tasks.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloValidatorTest {

    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void testValidateTrelloBoards(){

        //Given
        List<TrelloBoard> trelloBoards=new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "test",new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("2", "test1",new ArrayList<>()));

        //When
        List<TrelloBoard> trelloBoardsAfterValidation = trelloValidator.validateTrelloBoards(trelloBoards);

        //Then
        assertEquals(1, trelloBoardsAfterValidation.size());
        assertEquals("test1", trelloBoardsAfterValidation.get(0).getName());
    }
}