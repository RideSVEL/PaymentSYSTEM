package ua.nure.vasilchenko.SummaryTask4;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.nure.vasilchenko.SummaryTask4.db.DBManager;
import ua.nure.vasilchenko.SummaryTask4.db.entity.Card;
import ua.nure.vasilchenko.SummaryTask4.db.entity.Payment;
import ua.nure.vasilchenko.SummaryTask4.db.entity.User;
import ua.nure.vasilchenko.SummaryTask4.exception.DBException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * For run this test, comment constructor in DBManager
 * and replace method 'getConnection()' on other.
 * This needs, because standard method use pool servers,
 * but in test Tomcat always sleeps, so we used method without pool.
 */
class DBManagerTest {

    private static final Logger LOG = Logger.getLogger(DBManagerTest.class);

    DBManager manager;

    @BeforeEach
    void setUp() {
        System.out.println("Starting!");
        try {
            manager = DBManager.getInstance();
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        System.out.println("Finished");
    }

    @Test
    void getInstance() {
        DBManager manager = null;
        try {
            manager = DBManager.getInstance();
        } catch (DBException e) {
            e.printStackTrace();
        }
        assertNotNull(manager);
    }

    @Test
    void getConnection() {
        Connection con = null;
        try {
            con = manager.getConnection();
        } catch (DBException | SQLException e) {
            e.printStackTrace();
        }
        assertNotNull(con);
    }


    @Test
    void findAllUsersClient() throws DBException {
        List<User> users = manager.findAllUsersClient();
        assertNotNull(users);
        for (User user : users) {
            assertEquals(1, user.getRoleId());
        }
    }

    @Test
    void findUser() throws DBException {
        User user = manager.findUser(1);
        System.out.println(user);
        assertNotNull(user);
    }

    @Test
    void findCard() throws DBException {
        Card card = manager.findCard(10);
        System.out.println(card);
        assertNotNull(card);
    }

    @Test
    void findCardByNumber() throws DBException {
        Card card = manager.findCardByNumber(404967920208L);
        System.out.println(card);
        assertNotNull(card);
    }

    @Test
    void findPayment() throws DBException {
        Payment payment = manager.findPayment(3);
        System.out.println(payment);
        assertNotNull(payment);
    }

    @Test
    void findUserByCardId() throws DBException {
        Card card = manager.findCardByNumber(404967920208L);
        System.out.println(card);
        assertNotNull(card);
        User user = manager.findUserByCardId(card.getId());
        System.out.println(user);
        assertNotNull(user);
    }

    @Test
    void getUserCards() throws DBException {
        User user = manager.findUser(3);
        System.out.println(user);
        assertNotNull(user);
        List<Card> cards = manager.getUserCards(user);
        for (Card card : cards) {
            assertEquals(user.getId(), card.getUserId());
        }
    }

    @Test
    void getUserPayments() throws DBException {
        User user = manager.findUser(3);
        System.out.println(user);
        assertNotNull(user);
        List<Payment> payments = manager.getUserPayments(user);
        for (Payment payment : payments) {
            assertEquals(user.getId(), manager.findUserByCardId(payment.getCardId()).getId());
            System.out.println(payment);
        }
    }

    @Test
    void getUserIncome() throws DBException {
        User user = manager.findUser(3);
        System.out.println(user);
        assertNotNull(user);
        List<Payment> payments = manager.getUserIncome(user);
        for (Payment payment : payments) {
            assertEquals(user.getId(), manager.findUserByCardId(payment.getCardDestinationId()).getId());
            System.out.println(payment);
        }
    }

    @Test
    void getCardsWithRequest() throws DBException {
        List<Card> cards = manager.getCardsWithRequest();
        for (Card card : cards) {
            assertEquals(1, card.getRequestId());
        }
    }

    @Test
    void findUserByLogin() throws DBException {
        String login = "kuku";
        User user = manager.findUserByLogin(login);
        System.out.println(user);
        assertNotNull(user);
        assertEquals(login, user.getLogin());
    }

    @Test
    void countRequest() throws DBException {
        User user = manager.findUser(3);
        System.out.println(user);
        assertNotNull(user);
        int count = manager.countRequest(user);
        System.out.println(count);
    }

    @Test
    void countRequestAdmin() throws DBException {
        int count = manager.countRequestAdmin();
        System.out.println(count);
    }

}