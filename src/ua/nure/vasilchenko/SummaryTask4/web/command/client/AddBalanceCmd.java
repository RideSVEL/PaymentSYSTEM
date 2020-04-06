package ua.nure.vasilchenko.SummaryTask4.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.vasilchenko.SummaryTask4.Path;
import ua.nure.vasilchenko.SummaryTask4.db.Fields;
import ua.nure.vasilchenko.SummaryTask4.exception.AppException;
import ua.nure.vasilchenko.SummaryTask4.exception.Messages;
import ua.nure.vasilchenko.SummaryTask4.web.command.base.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Gets from replanish.jsp.
 * Forward on page with confirming replanish.
 * Setting on session attribute for replanish.
 *
 * @author S. Vasilchenko
 */
public class AddBalanceCmd extends Command {

    private static final long serialVersionUID = 78542177478505L;

    private static final Logger LOG = Logger.getLogger(AddBalanceCmd.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        String id = request.getParameter("card_id");
        String sum = request.getParameter("sum");
        LOG.trace("get parameters from request " + id + " " + sum);
        if (sum == null || id == null || id.isEmpty() || sum.isEmpty()) {
            throw new AppException(Messages.FIELDS_CANNOT_BE_EMPTY);
        }
        if (Integer.parseInt(sum) > Fields.PAYMENT_MAX_SUM) {
            throw new AppException(Messages.SUM_HIGHER_THAN_MAX);
        }
        HttpSession session = request.getSession();
        session.setAttribute("card_id", id);
        session.setAttribute("sum", sum);

        LOG.debug("Command finished");
        return Path.PAGE_USER_ACTION_ADD_BALANCE;
    }
}
