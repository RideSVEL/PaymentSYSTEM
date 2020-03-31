package ua.nure.vasilchenko.SummaryTask4.db;

/**
 * Holder for fields names of DB tables and beans.
 *
 * @author S. Vasilchenko
 */
public final class Fields {

    // entities
    public static final String ENTITY_ID = "id";

    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_ROLE_ID = "role_id";
    public static final String USER_ACTIVITY_ID = "activity_id";

    public static final String CARD_NAME = "name";
    public static final String CARD_USER_ID = "user_id";
    public static final String CARD_NUMBER = "number";
    public static final String CARD_MONEY = "money";
    public static final String CARD_ACTIVITY_ID = "activity_id";
    public static final String CARD_REQUEST_ID = "request_id";

    public static final String PAYMENT_CARD_ID = "card_id";
    public static final String PAYMENT_CARD_DESTINATION_ID = "card_destination_id";
    public static final String PAYMENT_DATE = "date";
    public static final String PAYMENT_MONEY = "money";
    public static final String PAYMENT_STATUS_ID = "status_id";
    public static final String PAYMENT_BALANCE = "balance";

    public static final int PAYMENT_MAX_SUM = 5000;
    public static final int CARDS_NUMBER = 12;
}
