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

public class ClientRequestCmd extends Command {

    private static final long serialVersionUID = 7732123567234L;

    private static final Logger LOG = Logger.getLogger(ClientRequestCmd.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        User user = (User) request.getSession().getAttribute("user");
        DBManager manager = DBManager.getInstance();
        // get user cards list
        List<Card> cards = manager.getUserCards(user);
        List<Card> resultCards = new ArrayList<>();

        Request requestEnum;


        for (Card card : cards) {
            requestEnum = Request.getRequest(card);
            if (requestEnum == Request.TRUE) {
                resultCards.add(card);
                //cards.remove(card);
            }
        }
        request.setAttribute("cards", resultCards);
        return Path.PAGE_USER_REQUESTS;
    }
}
