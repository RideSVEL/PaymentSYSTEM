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
import java.sql.SQLException;

/**
 * Showing on page all list users in system.
 *
 * @author S. Vasilchenko
 */
public class RejectReqCmd extends Command {

    private static final long serialVersionUID = 77287894555558505L;

    private static final Logger LOG = Logger.getLogger(RejectReqCmd.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        DBManager manager = DBManager.getInstance();
        String id = request.getParameter("card_id");
        Card card = manager.findCard(Integer.parseInt(id));
        LOG.trace("Found in DB: card --> " + card);
        card.setRequestId(0);
        try {
            manager.updateCard(card);
            LOG.trace("update in DB: card --> " + card);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int count = manager.countRequestAdmin();
        request.getSession().setAttribute("countAdmin", count);
        LOG.debug("Command finished");
        return Path.COMMAND_REQUEST;
    }
}
