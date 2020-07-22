package com.crud.tasks.facade;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TrelloMapperTest {

    private TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void testMapToBoards() {
        //given
        List<TrelloListDto> listDto1=new ArrayList<>();
        listDto1.add(new TrelloListDto("id1","list1",false));
        listDto1.add(new TrelloListDto("id2","list2",true));

        List<TrelloListDto> listDto2=new ArrayList<>();
        listDto2.add(new TrelloListDto("id3","list3",false));

        List<TrelloBoardDto> boardDto1 = new ArrayList<>();
        boardDto1.add(new TrelloBoardDto("id1", "name1", listDto1));
        boardDto1.add(new TrelloBoardDto("id2", "name2", listDto2));
        boardDto1.add(new TrelloBoardDto("id3", "name3", listDto2));

        //when
        List<TrelloBoard> boards = trelloMapper.mapToBoards(boardDto1);

        //then
        Assert.assertEquals(boards.get(0).getId(),"id1");
        Assert.assertEquals(boards.get(1).getId(),"id2");
        Assert.assertEquals(boards.get(2).getId(),"id3");
        Assert.assertEquals(boards.get(0).getName(),"name1");
        Assert.assertEquals(boards.get(1).getName(),"name2");
        Assert.assertEquals(boards.get(2).getName(),"name3");
        Assert.assertEquals(boards.get(0).getLists().get(1).getId(), trelloMapper.mapToList(listDto1).get(1).getId());
        Assert.assertEquals(boards.get(1).getLists().get(0).getName(), trelloMapper.mapToList(listDto2).get(0).getName());
    }

    @Test
    public void testMapToBoardsDto() {
        //given
        List<TrelloList> list1=new ArrayList<>();
        list1.add(new TrelloList("id1","list1",false));
        List<TrelloList> list2=new ArrayList<>();
        list2.add(new TrelloList("id2","list2",false));

        List<TrelloBoard> board1 = new ArrayList<>();
        board1.add(new TrelloBoard("id1", "board1", list1));
        board1.add(new TrelloBoard("id2", "board2", list2));

        //when
        List<TrelloBoardDto> boardDtos = trelloMapper.mapToBoardsDto(board1);

        //then
        Assert.assertEquals(boardDtos.get(0).getId(),"id1");
        Assert.assertEquals(boardDtos.get(1).getId(),"id2");
        Assert.assertEquals(boardDtos.get(0).getName(),"board1");
        Assert.assertEquals(boardDtos.get(1).getName(),"board2");
        Assert.assertEquals(boardDtos.get(0).getLists().get(0).getId(), "id1");
        Assert.assertEquals(boardDtos.get(1).getLists().get(0).getId(), "id2");
        Assert.assertEquals(boardDtos.get(0).getLists().get(0).getName(), "list1");
        Assert.assertEquals(boardDtos.get(1).getLists().get(0).getName(), "list2");
        Assert.assertEquals(boardDtos.get(1).getLists().get(0).isClosed(), false);
    }

    @Test
    public void testMapToList() {
        //given
        List<TrelloListDto> listDto1=new ArrayList<>();
        listDto1.add(new TrelloListDto("id1","list1",false));
        listDto1.add(new TrelloListDto("id2","list2",true));

        //when
        List<TrelloList> list = trelloMapper.mapToList(listDto1);

        //then
        Assert.assertEquals(list.get(0).getId(),"id1");
        Assert.assertEquals(list.get(0).getName(), "list1");
        Assert.assertEquals(list.get(0).isClosed(),false);
        Assert.assertEquals(list.get(1).getId(),"id2");
        Assert.assertEquals(list.get(1).getName(), "list2");
        Assert.assertEquals(list.get(1).isClosed(),true);
    }

    @Test
    public void testMapToListDto() {
        //given
        List<TrelloList> list1 = new ArrayList<>();
        list1.add(new TrelloList("id1","list1",false));
        list1.add(new TrelloList("id2","list2",true));

        //when
        List<TrelloListDto> listDto = trelloMapper.mapToListDto(list1);

        //then
        Assert.assertEquals(listDto.get(0).getId(),"id1");
        Assert.assertEquals(listDto.get(0).getName(), "list1");
        Assert.assertEquals(listDto.get(0).isClosed(),false);
        Assert.assertEquals(listDto.get(1).getId(),"id2");
        Assert.assertEquals(listDto.get(1).getName(), "list2");
        Assert.assertEquals(listDto.get(1).isClosed(),true);
    }

    @Test
    public void mapToCard() {
        //given
        TrelloCardDto cardDto1 = new TrelloCardDto("card1","description1","pos1","listId1");

        //when
        TrelloCard card = trelloMapper.mapToCard(cardDto1);

        //then
        Assert.assertEquals(card.getName(),"card1");
        Assert.assertEquals(card.getDescription(),"description1");
        Assert.assertEquals(card.getPos(),"pos1");
        Assert.assertEquals(card.getListId(),"listId1");
    }

    @Test
    public void testMapToCardDto(){
        //given
        TrelloCard card1 = new TrelloCard("card1","description1","pos1","listId1");

        //when
        TrelloCardDto cardDto = trelloMapper.mapToCardDto(card1);

        //then
        Assert.assertEquals(cardDto.getName(),"card1");
        Assert.assertEquals(cardDto.getDescription(),"description1");
        Assert.assertEquals(cardDto.getPos(),"pos1");
        Assert.assertEquals(cardDto.getListId(),"listId1");
    }
}
