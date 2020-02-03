package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    private List<TrelloList> createTrelloList() {
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("231", "test1", true));
        trelloLists.add(new TrelloList("hgh654", "test2", false));
        trelloLists.add(new TrelloList("45/12", "test3", false));
        trelloLists.add(new TrelloList("2020/01/10", "test4", false));
        return trelloLists;
    }

    private List<TrelloListDto> createTrelloListDto() {
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("3214", "test101", true));
        trelloListDto.add(new TrelloListDto("2/2", "test102", true));
        trelloListDto.add(new TrelloListDto("fff/ddd", "test103", false));
        trelloListDto.add(new TrelloListDto("7", "test104", true));
        return trelloListDto;
    }

    @Test
    public void mapToBoardTest() {
        //Given
        List<TrelloBoardDto> testTrelloBoardsDtoList = new ArrayList<>();
        testTrelloBoardsDtoList.add(new TrelloBoardDto("432/2", "test1", createTrelloListDto()));
        //When
        List<TrelloBoard> testTrelloBoardsList = trelloMapper.mapToBoard(testTrelloBoardsDtoList);
        //Then
        assertEquals(1, testTrelloBoardsList.size());
        assertEquals(testTrelloBoardsDtoList.get(0).getId(), testTrelloBoardsList.get(0).getId());
        assertEquals(testTrelloBoardsDtoList.get(0).getName(), testTrelloBoardsList.get(0).getName());
        assertEquals(testTrelloBoardsDtoList.get(0).getLists().size(), testTrelloBoardsList.get(0).getLists().size());
        assertEquals(testTrelloBoardsDtoList.get(0).getLists().get(1).getName(), testTrelloBoardsList.get(0).getLists().get(1).getName());
    }

    @Test
    public void mapToBoardDtoTest() {
        //Given
        List<TrelloBoard> testTrelloBoardsList = new ArrayList<>();
        testTrelloBoardsList.add(new TrelloBoard("fdsg/343/v", "test1500", createTrelloList()));
        //When
        List<TrelloBoardDto> testTrelloBoardsDtoList = trelloMapper.mapToBoardsDto(testTrelloBoardsList);
        //Then
        assertEquals(1, testTrelloBoardsList.size());
        assertEquals(testTrelloBoardsDtoList.get(0).getId(), testTrelloBoardsList.get(0).getId());
        assertEquals(testTrelloBoardsDtoList.get(0).getName(), testTrelloBoardsList.get(0).getName());
        assertEquals(testTrelloBoardsDtoList.get(0).getLists().size(), testTrelloBoardsList.get(0).getLists().size());
        assertEquals(testTrelloBoardsDtoList.get(0).getLists().get(2).getName(), testTrelloBoardsList.get(0).getLists().get(2).getName());
    }

    @Test
    public void mapToListTest() {
        //Given
            List<TrelloListDto> trelloListDtoTest = createTrelloListDto();
        //When
            List<TrelloList> trelloListsTest = trelloMapper.mapToList(trelloListDtoTest);
        //Then
            Assert.assertTrue(trelloListsTest.size() > 0);
            Assert.assertTrue(trelloListDtoTest.size() == trelloListsTest.size());
            assertEquals(trelloListsTest.get(2).getId(), trelloListDtoTest.get(2).getId());
            assertEquals(trelloListsTest.get(0).isClosed(), trelloListDtoTest.get(0).isClosed());
            assertEquals(trelloListsTest.get(3).getName(), trelloListDtoTest.get(3).getName());
    }

    @Test
    public void mapToListDtoTest() {
        //Given
        List<TrelloList> trelloListTest = createTrelloList();
        //When
        List<TrelloListDto> trelloListsDtoTest = trelloMapper.mapToListDto(trelloListTest);
        //Then
        Assert.assertTrue(trelloListsDtoTest.size() > 0);
        Assert.assertTrue(trelloListsDtoTest.size() == trelloListTest.size());
        assertEquals(trelloListTest.get(1).getId(), trelloListsDtoTest.get(1).getId());
        assertEquals(trelloListTest.get(2).isClosed(), trelloListsDtoTest.get(2).isClosed());
        assertEquals(trelloListTest.get(0).getName(), trelloListsDtoTest.get(0).getName());
    }

    @Test
    public void mapToCardTest() {
        //Given
        TrelloCardDto testTrelloCardDto = new TrelloCardDto("test1", "test2", "test3", "test4");
        //When
        TrelloCard testTrelloCard = trelloMapper.mapToCard(testTrelloCardDto);
        //Then
        assertEquals(testTrelloCard.getDescription(), testTrelloCardDto.getDescription());
        assertEquals(testTrelloCard.getListId(), testTrelloCardDto.getListId());
        assertEquals(testTrelloCard.getName(), testTrelloCardDto.getName());
        assertEquals(testTrelloCard.getPos(), testTrelloCardDto.getPos());
    }

    @Test
    public void mapToCardDtoTest() {
        //Given
        TrelloCard testTrelloCard = new TrelloCard("test10", "test20", "test33", "test44");
        //When
        TrelloCardDto testTrelloCardDto = trelloMapper.mapToCardDto(testTrelloCard);
        //Then
        assertEquals(testTrelloCard.getDescription(), testTrelloCardDto.getDescription());
        assertEquals(testTrelloCard.getListId(), testTrelloCardDto.getListId());
        assertEquals(testTrelloCard.getName(), testTrelloCardDto.getName());
        assertEquals(testTrelloCard.getPos(), testTrelloCardDto.getPos());
    }
}
