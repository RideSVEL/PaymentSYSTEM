package ua.nure.vasilchenko.SummaryTask4.Util;

import org.junit.jupiter.api.Test;
import ua.nure.vasilchenko.SummaryTask4.CardNumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardNumberTest {

    @Test
    void createCardNumber() {
        for (int i = 0; i < 100; i++) {
            long cardNumber = CardNumber.createCardNumber();
            // function calculating digit in number
            assertEquals(12, Math.ceil(Math.log10(cardNumber)));
        }
    }

    @Test
    void getPrefix() {
        for (int i = 0; i < 50; i++) {
            long cardNumber = CardNumber.createCardNumber();
            String number = String.valueOf(cardNumber);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 4; j++) {
                sb.append(number.charAt(j));
            }
            assertEquals("4049", sb.toString());
        }
    }
}