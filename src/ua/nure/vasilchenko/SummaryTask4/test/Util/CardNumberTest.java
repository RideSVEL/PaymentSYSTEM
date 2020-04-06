package ua.nure.vasilchenko.SummaryTask4.test.Util;

import org.junit.jupiter.api.Test;
import ua.nure.vasilchenko.SummaryTask4.CardNumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardNumberTest {

    @Test
    void createCardNumber() {
        long cardNumber = CardNumber.createCardNumber();
        // function calculating digit in number
        assertEquals(12, Math.ceil(Math.log10(cardNumber)));
    }

    @Test
    void getPrefix() {
        long cardNumber = CardNumber.createCardNumber();
        String number = String.valueOf(cardNumber);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(number.charAt(i));
        }
        assertEquals("4049", sb.toString());
    }
}