package com.crud.tasks.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.trello.validator.TrelloValidator;
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
public class ValidatorTestSuite {

    @Autowired
    TrelloValidator trelloValidator;

    private List<TrelloList> createTrelloList() {
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("231", "test1", true));
        trelloLists.add(new TrelloList("hgh654", "test2", false));
        trelloLists.add(new TrelloList("45/12", "test3", false));
        trelloLists.add(new TrelloList("2020/01/10", "test4", false));
        return trelloLists;
    }

    @Test
    public void validatorTrelloBoardTest() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1/1", "aaa", createTrelloList()));
        trelloBoards.add(new TrelloBoard("1/2", "Test", createTrelloList()));
        //When
        List<TrelloBoard> trelloBoardAfterValidation = trelloValidator.validateTrelloBoards(trelloBoards);
        int sizeTrelloBoardAfterValidation = trelloBoardAfterValidation.size();
        //Then
        assertEquals(1, sizeTrelloBoardAfterValidation);
    }
}
