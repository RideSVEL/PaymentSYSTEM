package ua.nure.vasilchenko.SummaryTask4.web.command.client;

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
import java.sql.SQLException;

/**
 * Command for sending for admin request
 * to unblocking certain card.
 *
 * @author S. Vasilchenko
 */
public class UserUnblockRequestCmd extends Command {

    private static final long serialVersionUID = 77212365478505L;

    private static final Logger LOG = Logger.getLogger(UserUnblockRequestCmd.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        DBManager manager = DBManager.getInstance();
        String id = request.getParameter("card_id");
        LOG.trace("Get parameter card_id from request" + id);
        LOG.debug("Parsing card_id to int");
        Card card = manager.findCard(Integer.parseInt(id));
        card.setRequestId(1);
        try {
            LOG.trace("Updting card in db" + card);
            manager.updateCard(card);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOG.debug("Command finished");
        return Path.COMMAND_USER_CARDS;
    }
}
