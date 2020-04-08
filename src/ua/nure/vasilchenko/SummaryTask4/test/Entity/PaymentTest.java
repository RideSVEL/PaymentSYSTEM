package ua.nure.vasilchenko.SummaryTask4.test.Entity;

import org.junit.jupiter.api.Test;
import ua.nure.vasilchenko.SummaryTask4.db.entity.Payment;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    Payment payment;

    @Test
    void checkConstructor() {
        Payment payment = new Payment();
        assertNotNull(payment);
    }

    @Test
    void getBalance() {
        payment = new Payment();
        payment.setBalance(500);
        assertEquals(500, payment.getBalance());
    }

    @Test
    void getCardNumber() {
        payment = new Payment();
        payment.setCardNumber(1236656236);
        assertEquals(1236656236, payment.getCardNumber());
    }

    @Test
    void getCardDestinationNumber() {
        payment = new Payment();
        payment.setCardDestinationNumber(585585574);
        assertEquals(585585574, payment.getCardDestinationNumber());
    }

    @Test
    void getDate() {
        payment = new Payment();
        payment.setDate("12.15.16");
        assertEquals("12.15.16", payment.getDate());
    }

    @Test
    void getMoney() {
        payment = new Payment();
        payment.setMoney(500);
        assertEquals(500, payment.getMoney());
    }

    @Test
    void getCardId() {
        payment = new Payment();
        payment.setCardId(12);
        assertEquals(12, payment.getCardId());
    }

    @Test
    void getCardDestinationId() {
        payment = new Payment();
        payment.setCardDestinationId(15);
        assertEquals(15, payment.getCardDestinationId());
    }

    @Test
    void getStatusId() {
        payment = new Payment();
        payment.setStatusId(1);
        assertEquals(1, payment.getStatusId());
    }
}