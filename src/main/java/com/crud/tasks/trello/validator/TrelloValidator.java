package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloFacade.class);

    public List<TrelloBoard> validateTrelloBoards(final List<TrelloBoard> trelloBoards) {
        LOGGER.info("Starting filtering boards...");
        List<TrelloBoard> filteredBoard = trelloBoards.stream()
                .filter(trelloBoard -> !trelloBoard.getName().equalsIgnoreCase("Test"))
                .collect(Collectors.toList());
        LOGGER.info("Board have been filtered. Current list size: " + filteredBoard.size());
        return filteredBoard;
    }

    public void validateCard(final TrelloCard trelloCard) {
        if(trelloCard.getName().contains("Test")) {
            LOGGER.info("Someone is testing my application!");
        }else {
            LOGGER.info("Seems that my application is used in proper way!");
        }
    }
}
