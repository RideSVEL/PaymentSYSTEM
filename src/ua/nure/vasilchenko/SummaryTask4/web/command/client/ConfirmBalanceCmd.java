package ua.nure.vasilchenko.SummaryTask4.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.vasilchenko.SummaryTask4.Path;
import ua.nure.vasilchenko.SummaryTask4.db.DBManager;
import ua.nure.vasilchenko.SummaryTask4.db.entity.Card;
import ua.nure.vasilchenko.SummaryTask4.db.entity.Payment;
import ua.nure.vasilchenko.SummaryTask4.exception.AppException;
import ua.nure.vasilchenko.SummaryTask4.web.command.base.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ConfirmBalanceCmd extends Command {

    private static final long serialVersionUID = 78542177478505L;

    private static final Logger LOG = Logger.getLogger(AddBalanceCmd.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        String id = (String) request.getSession().getAttribute("card_id");
        String sum = (String) request.getSession().getAttribute("sum");
        DBManager manager = DBManager.getInstance();
        Card card = manager.findCard(Long.parseLong(id));
        card.setMoney(card.getMoney() + Integer.parseInt(sum));
        Payment payment = new Payment();
        payment.setCardDestinationId(card.getId());
        payment.setMoney(Integer.parseInt(sum));
        payment.setBalance(card.getMoney());
        payment.setStatusId(1);
        try {
            manager.updateCard(card);
            manager.insertPayment(payment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Path.COMMAND_USER_CARDS + "&sorting=name&order=ascending&filter=all";
    }
}
