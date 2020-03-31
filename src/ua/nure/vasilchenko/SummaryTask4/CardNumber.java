package ua.nure.vasilchenko.SummaryTask4;

import java.security.SecureRandom;

public final class CardNumber {

    private static final int head = 4049;

    private CardNumber() {
    }

    private static String getRandom() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(plus());
        }
        return sb.toString();
    }

    private static int plus() {
        return new SecureRandom().nextInt(10);
    }

    public static long createCardNumber() {
        StringBuilder sb = new StringBuilder();
        sb.append(head).append(getRandom()).append(getRandom());
        return Long.parseLong(sb.toString());
    }

}
