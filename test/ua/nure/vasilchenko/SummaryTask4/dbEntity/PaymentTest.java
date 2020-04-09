package ua.nure.vasilchenko.SummaryTask4.dbEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.nure.vasilchenko.SummaryTask4.db.entity.Payment;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    Payment payment;

    @BeforeEach
    void init() {
        payment = new Payment();
    }

    @Test
    void checkConstructor() {
        Payment payment = new Payment();
        assertNotNull(payment);
    }

    @Test
    void getBalance() {
        payment.setBalance(500);
        assertEquals(500, payment.getBalance());
    }

    @Test
    void getCardNumber() {
        payment.setCardNumber(1236656236);
        assertEquals(1236656236, payment.getCardNumber());
    }

    @Test
    void getCardDestinationNumber() {
        payment.setCardDestinationNumber(585585574);
        assertEquals(585585574, payment.getCardDestinationNumber());
    }

    @Test
    void getDate() {
        payment.setDate("12.15.16");
        assertEquals("12.15.16", payment.getDate());
    }

    @Test
    void getMoney() {
        payment.setMoney(500);
        assertEquals(500, payment.getMoney());
    }

    @Test
    void getCardId() {
        payment.setCardId(12);
        assertEquals(12, payment.getCardId());
    }

    @Test
    void getCardDestinationId() {
        payment.setCardDestinationId(15);
        assertEquals(15, payment.getCardDestinationId());
    }

    @Test
    void getStatusId() {
        payment.setStatusId(1);
        assertEquals(1, payment.getStatusId());
    }
}