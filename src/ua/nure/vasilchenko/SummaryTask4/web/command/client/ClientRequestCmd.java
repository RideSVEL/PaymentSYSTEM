package ua.nure.vasilchenko.SummaryTask4.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.vasilchenko.SummaryTask4.Path;
import ua.nure.vasilchenko.SummaryTask4.db.DBManager;
import ua.nure.vasilchenko.SummaryTask4.db.entity.Card;
import ua.nure.vasilchenko.SummaryTask4.db.entity.User;
import ua.nure.vasilchenko.SummaryTask4.db.enumeration.Request;
import ua.nure.vasilchenko.SummaryTask4.exception.AppException;
import ua.nure.vasilchenko.SummaryTask4.web.command.base.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Sending request for administration on invert
 * status card.
 *
 * @author S. Vasilchenko
 */
public class ClientRequestCmd extends Command {

    private static final long serialVersionUID = 7732123567234L;

    private static final Logger LOG = Logger.getLogger(ClientRequestCmd.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        User user = (User) request.getSession().getAttribute("user");
        LOG.trace("get attribute from session" + user);
        DBManager manager = DBManager.getInstance();
        // get user cards list
        List<Card> cards = manager.getUserCards(user);
        LOG.trace("Found in DB: cardsList --> " + cards);
        List<Card> resultCards = new ArrayList<>();
        LOG.trace("create : resultCards --> " + resultCards);
        Request requestEnum;


        for (Card card : cards) {
            requestEnum = Request.getRequest(card);
            if (requestEnum == Request.TRUE) {
                resultCards.add(card);
            }
        }
        request.setAttribute("cards", resultCards);
        LOG.trace("setting attribut in session " + resultCards);
        LOG.debug("Command finished");
        return Path.PAGE_USER_REQUESTS;
    }
}
