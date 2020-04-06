package ua.nure.vasilchenko.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.vasilchenko.SummaryTask4.Path;
import ua.nure.vasilchenko.SummaryTask4.db.DBManager;
import ua.nure.vasilchenko.SummaryTask4.db.entity.Card;
import ua.nure.vasilchenko.SummaryTask4.exception.AppException;
import ua.nure.vasilchenko.SummaryTask4.web.command.base.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Gets from the page header.jspf.
 * Showing all BLOCKED users cards and with
 * status request = TRUE.
 *
 * @author S. Vasilchenko
 */
public class RequestCmd extends Command {

    private static final long serialVersionUID = 7732235467234L;

    private static final Logger LOG = Logger.getLogger(RequestCmd.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        // get user cards list
        DBManager manager = DBManager.getInstance();
        List<Card> cards = manager.getCardsWithRequest();

        LOG.trace("Found in DB: cardsList --> " + cards);

        request.setAttribute("cards", cards);

        LOG.trace("Set the request attribute: cards --> " + cards);

        int count = manager.countRequestAdmin();
        request.getSession().setAttribute("countAdmin", count);

        LOG.debug("Command finished");
        return Path.PAGE_REQUEST;
    }
}
