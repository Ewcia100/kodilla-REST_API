package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("1", "list1", false));
        trelloListDto.add(new TrelloListDto("2", "list2", true));
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDto);
        //Then
        assertEquals(2, trelloLists.size());
        assertEquals(trelloListDto.get(0).getName(), trelloLists.get(0).getName());
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> trelloList = new ArrayList<>();
        trelloList.add(new TrelloList("1", "list1", false));
        trelloList.add(new TrelloList("2", "list2", true));
        //When
        List<TrelloListDto> trelloListDto = trelloMapper.mapToListDto(trelloList);
        //Then
        assertEquals(2, trelloListDto.size());
        assertEquals(trelloList.get(0).getName(), trelloListDto.get(0).getName());
    }

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.add(new TrelloBoardDto("1", "board1", new ArrayList<>()));
        //When
        List<TrelloBoard> trelloBoard = trelloMapper.mapToBoards(trelloBoardDto);
        //Then
        assertEquals(1, trelloBoard.size());
        assertEquals(trelloBoardDto.get(0).getName(), trelloBoard.get(0).getName());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloBoard> trelloBoard = new ArrayList<>();
        trelloBoard.add(new TrelloBoard("1", "board1", new ArrayList<>()));
        //When
        List<TrelloBoardDto> trelloBoardDto = trelloMapper.mapToBoardsDto(trelloBoard);
        //Then
        assertEquals(1, trelloBoardDto.size());
        assertEquals(trelloBoard.get(0).getName(), trelloBoardDto.get(0).getName());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card1", "desc card1", "tralala", "1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals(trelloCardDto.getName(), trelloCard.getName());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("card1", "desc card1", "tralala", "1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals(trelloCard.getName(), trelloCardDto.getName());
    }
}
