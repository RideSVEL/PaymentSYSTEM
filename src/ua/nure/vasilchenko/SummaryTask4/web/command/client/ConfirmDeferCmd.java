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

public class ConfirmDeferCmd extends Command {

    private static final long serialVersionUID = 7854278452505L;

    private static final Logger LOG = Logger.getLogger(ConfirmDeferCmd.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        String id = request.getParameter("payment_id");
        DBManager manager = DBManager.getInstance();
        if (id == null || id.isEmpty()) {
            throw new AppException("fields cannot be empty ");
        }
        Payment payment = manager.findPayment(Long.parseLong(id));
        if (payment == null) {
            throw new AppException("Payment don`t created");
        }
        Card card = manager.findCard(payment.getCardId());
        if (card.getActivityId() == 1) {
            throw new AppException("Your card was blocked");
        }
        Card destinationCard = manager.findCard(payment.getCardDestinationId());
        if (destinationCard.getActivityId() == 1) {
            throw new AppException("Card destination user was blocked");
        }
        if (card.getMoney() < payment.getMoney()) {
            throw new AppException("Insufficient funds in the account.");
        }
        card.setMoney(card.getMoney() - payment.getMoney());
        destinationCard.setMoney(destinationCard.getMoney() + payment.getMoney());
        payment.setStatusId(1);
        payment.setBalance(card.getMoney());
        try {
            manager.updateCard(card);
            manager.updateCard(destinationCard);
            manager.updatePayment(payment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOG.debug("Command finished");
        return Path.COMMAND_USER_PAYMENTS;
    }
}
