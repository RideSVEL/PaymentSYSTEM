package ua.nure.vasilchenko.SummaryTask4.test.Entity;

import org.junit.jupiter.api.Test;
import ua.nure.vasilchenko.SummaryTask4.db.entity.Card;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    Card card;

    @Test
    void checkConstructor() {
        Card card = new Card();
        assertNotNull(card);
    }

    @Test
    void getRequestId() {
        card = new Card();
        card.setRequestId(1);
        assertEquals(1, card.getRequestId());
    }

    @Test
    void getUserId() {
        card = new Card();
        card.setUserId(10);
        assertEquals(10, card.getUserId());
    }

    @Test
    void getName() {
        card = new Card();
        card.setName("privet");
        assertEquals("privet", card.getName());
    }

    @Test
    void getNumber() {
        card = new Card();
        card.setNumber(1213457846);
        assertEquals(1213457846, card.getNumber());
    }

    @Test
    void getMoney() {
        card = new Card();
        card.setMoney(15);
        assertEquals(15, card.getMoney());
    }

    @Test
    void getActivityId() {
        card = new Card();
        card.setActivityId(1);
        assertEquals(1, card.getActivityId());
    }
}