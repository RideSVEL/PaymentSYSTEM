package ua.nure.vasilchenko.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.vasilchenko.SummaryTask4.web.command.base.Command;
import ua.nure.vasilchenko.SummaryTask4.Path;
import ua.nure.vasilchenko.SummaryTask4.db.DBManager;
import ua.nure.vasilchenko.SummaryTask4.db.entity.Card;
import ua.nure.vasilchenko.SummaryTask4.db.entity.User;
import ua.nure.vasilchenko.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Gets from listUser.jsp.
 * Show all cards for certain user
 * and forward on page with functions
 * blocking/unblocking user cards.
 *
 * @author S. Vasilchenko
 */
public class ShowCardsCmd extends Command {

    private static final long serialVersionUID = 7732235467234L;

    private static final Logger LOG = Logger.getLogger(ShowCardsCmd.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        String id = request.getParameter("user_id");
        DBManager manager = DBManager.getInstance();
        User user = manager.findUser(Integer.parseInt(id));
        LOG.trace("Found in DB: user --> " + user);
        // get user cards list
        List<Card> cards = manager.getUserCards(user);

        //  users.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        LOG.trace("Found in DB: cardsList --> " + cards);

        request.setAttribute("cards", cards);
        request.setAttribute("userLogin", user.getLogin());

        LOG.trace("Set the request attribute: cards --> " + cards);

        int count = manager.countRequestAdmin();
        request.getSession().setAttribute("countAdmin", count);

        LOG.debug("Command finished");
        return Path.PAGE_LIST_CARDS;
    }
}
