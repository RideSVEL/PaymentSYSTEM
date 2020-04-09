package ua.nure.vasilchenko.SummaryTask4;

/**
 * Path holder (jsp pages, controller commands).
 *
 * @author S.Vasilchenko
 */
public final class Path {

    private Path() {
        throw new IllegalStateException("ua.nure.vasilchenko.SummaryTask4.Util class");
    }

    // pages
    public static final String PAGE_LOGIN = "/WEB-INF/jsp/login.jsp";
    public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
    public static final String PAGE_LIST_USERS = "/WEB-INF/jsp/admin/list_users.jsp";
    public static final String PAGE_LIST_CARDS = "/WEB-INF/jsp/admin/user_cards.jsp";
    public static final String PAGE_REQUEST = "/WEB-INF/jsp/admin/requests.jsp";
    public static final String PAGE_USER_CARDS = "/WEB-INF/jsp/client/client_cards.jsp";
    public static final String PAGE_USER_PAYMENTS = "/WEB-INF/jsp/client/payments.jsp";
    public static final String PAGE_USER_INCOMES = "/WEB-INF/jsp/client/income.jsp";
    public static final String PAGE_USER_REQUESTS = "/WEB-INF/jsp/client/client_request.jsp";
    public static final String PAGE_USER_BLOCK_ACTION = "/WEB-INF/jsp/client/actionBlock.jsp";
    public static final String PAGE_USER_ACTION_ADD_BALANCE = "/WEB-INF/jsp/client/confirm_balance.jsp";
    public static final String PAGE_USER_ADD_BALANCE = "/WEB-INF/jsp/client/add_balance.jsp";
    public static final String PAGE_USER_SEND_PAYMENT = "/WEB-INF/jsp/client/send_payment.jsp";
    public static final String PAGE_USER_CONFIRM_PAYMENT = "/WEB-INF/jsp/client/confirm_payment.jsp";
    public static final String PAGE_USER_CREATE_CARD = "/WEB-INF/jsp/client/create_card.jsp";

    // commands
    public static final String COMMAND_LIST_USERS = "/controller?command=listUsers";
    public static final String COMMAND_LIST_CARDS = "/controller?command=showCards";
    public static final String COMMAND_REQUEST = "/controller?command=showRequests";
    public static final String COMMAND_USER_CARDS = "/controller?command=userShowCards";
    public static final String COMMAND_USER_PAYMENTS = "/controller?command=showPayments";
    public static final String COMMAND_VIEW_SETTINGS = "/settings";


}