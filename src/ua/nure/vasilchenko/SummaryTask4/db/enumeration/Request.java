package ua.nure.vasilchenko.SummaryTask4.db.enumeration;

import ua.nure.vasilchenko.SummaryTask4.db.entity.Card;

public enum Request {
    FALSE, TRUE;

    public static Request getRequest(Card card) {
        int requestId = card.getRequestId();
        return Request.values()[requestId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
