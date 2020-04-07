package ua.nure.vasilchenko.SummaryTask4.web.command.client;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import ua.nure.vasilchenko.SummaryTask4.Path;
import ua.nure.vasilchenko.SummaryTask4.db.DBManager;
import ua.nure.vasilchenko.SummaryTask4.db.entity.Card;
import ua.nure.vasilchenko.SummaryTask4.db.entity.Payment;
import ua.nure.vasilchenko.SummaryTask4.db.entity.User;
import ua.nure.vasilchenko.SummaryTask4.exception.AppException;
import ua.nure.vasilchenko.SummaryTask4.exception.Messages;
import ua.nure.vasilchenko.SummaryTask4.web.command.base.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Forward from command CHECK_PAYMENT.
 * IF controller go to this command,
 * replenish balance confirnimg.
 * Updated info in DB and return on page with cards.
 *
 * @author S. Vasilchenko
 */
public class ConfirmBalanceCmd extends Command {

    private static final long serialVersionUID = 78542177478505L;

    private static final Logger LOG = Logger.getLogger(AddBalanceCmd.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        User user = (User) request.getSession().getAttribute("user");
        LOG.trace("get attribute from session" + user);
        if (!user.getPassword().equals(DigestUtils.md5Hex(request.getParameter("password")))) {
            throw new AppException(Messages.YOUR_PASSWORD_DOES_NOT_MATCH);
        }
        String id = (String) request.getSession().getAttribute("card_id");
        String sum = (String) request.getSession().getAttribute("sum");
        if (id == null || sum == null || id.isEmpty() || sum.isEmpty()) {
            return Path.COMMAND_USER_CARDS + "&sorting=name&order=ascending&filter=all";
        }
        LOG.debug("get attributes from session");
        DBManager manager = DBManager.getInstance();
        Card card = manager.findCard(Long.parseLong(id));
        LOG.trace("Found in DB: card --> " + card);
        card.setMoney(card.getMoney() + Integer.parseInt(sum));
        Payment payment = new Payment();
        payment.setCardDestinationId(card.getId());
        payment.setMoney(Integer.parseInt(sum));
        payment.setBalance(card.getMoney());
        payment.setStatusId(1);
        try {
            manager.updateCard(card);
            LOG.trace("update in DB: card --> " + card);
            manager.insertPayment(payment);
            LOG.trace("insert in DB: payment --> " + payment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        session.setAttribute("card_id", null);
        session.setAttribute("sum", null);
        LOG.debug("Command finished");
        return Path.COMMAND_USER_CARDS + "&sorting=name&order=ascending&filter=all";
    }
}
