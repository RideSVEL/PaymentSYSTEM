package ua.nure.vasilchenko.SummaryTask4.dbEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.nure.vasilchenko.SummaryTask4.db.entity.Card;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    Card card;

    @BeforeEach
    void init() {
        card = new Card();
    }


    @Test
    void checkConstructor() {
        Card card = new Card();
        assertNotNull(card);
    }

    @Test
    void getRequestId() {
        card.setRequestId(1);
        assertEquals(1, card.getRequestId());
    }

    @Test
    void getUserId() {
        card.setUserId(10);
        assertEquals(10, card.getUserId());
    }

    @Test
    void getName() {
        card.setName("privet");
        assertEquals("privet", card.getName());
    }

    @Test
    void getNumber() {
        card.setNumber(1213457846);
        assertEquals(1213457846, card.getNumber());
    }

    @Test
    void getMoney() {
        card.setMoney(15);
        assertEquals(15, card.getMoney());
    }

    @Test
    void getActivityId() {
        card.setActivityId(1);
        assertEquals(1, card.getActivityId());
    }
}