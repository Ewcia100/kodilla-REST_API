package com.crud.tasks.service;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.AdminConfig;
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
public class TrelloServiceTestSuite {
    @InjectMocks
    private TrelloService trelloService;
    @Mock
    private AdminConfig adminConfig;
    @Mock
    private TrelloClient trelloClient;
    @Mock
    private SimpleEmailService emailService;

    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("1", "list1", true));
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("1", "board1", trelloListDto));
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtos);

        //When
        List<TrelloBoardDto> trelloBoardDtosFinding = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(1, trelloBoardDtosFinding.size());
        assertEquals("1", trelloBoardDtosFinding.get(0).getLists().get(0).getId());
        assertEquals("board1", trelloBoardDtosFinding.get(0).getName());
    }

    @Test
    public void testCreateTrelloCard(){
        //Given
        TrelloCardDto trelloCardDto=new TrelloCardDto("card1", "cardDesc", "cardPos", "1");
        Trello trello=new Trello (1,1);
        AttachmentsByType attachmentsByType=new AttachmentsByType(trello);
        Badges badges=new Badges(1,attachmentsByType);
        CreatedTrelloCardDto createdTrelloCardDto=new CreatedTrelloCardDto("1","card1","trello/card1",
                badges);

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        //When
        CreatedTrelloCardDto createdTrelloCardDtoFinding=trelloService.createTrelloCard(trelloCardDto);

        assertEquals("1", createdTrelloCardDtoFinding.getId());
        assertEquals(1, createdTrelloCardDtoFinding.getBadges().getVotes());
        assertEquals(1, createdTrelloCardDtoFinding.getBadges().getAttachment().getTrello().getBoard());
        assertEquals(1, createdTrelloCardDtoFinding.getBadges().getAttachment().getTrello().getCard());

    }


}
