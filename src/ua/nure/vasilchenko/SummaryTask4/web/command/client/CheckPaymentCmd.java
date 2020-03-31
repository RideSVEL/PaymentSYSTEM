package ua.nure.vasilchenko.SummaryTask4.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.vasilchenko.SummaryTask4.Path;
import ua.nure.vasilchenko.SummaryTask4.db.DBManager;
import ua.nure.vasilchenko.SummaryTask4.db.Fields;
import ua.nure.vasilchenko.SummaryTask4.db.entity.Card;
import ua.nure.vasilchenko.SummaryTask4.exception.AppException;
import ua.nure.vasilchenko.SummaryTask4.web.command.base.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckPaymentCmd extends Command {

    private static final long serialVersionUID = 7851333478505L;

    private static final Logger LOG = Logger.getLogger(CheckPaymentCmd.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        String id = request.getParameter("card_id");
        String sum = request.getParameter("sum");
        String destination = request.getParameter("destination");
        DBManager manager = DBManager.getInstance();
        if (sum == null || id == null || destination == null || id.isEmpty() || sum.isEmpty() || destination.isEmpty()) {
            throw new AppException("fields cannot be empty ");
        }
        int sumPayment = Integer.parseInt(sum);
        if (sumPayment > Fields.PAYMENT_MAX_SUM) {
            throw new AppException("Sum higher than max payment sum");
        }
        if (destination.length() != Fields.CARDS_NUMBER) {
            throw new AppException("Card destination is not valid");
        }
        Card card = manager.findCard(Integer.parseInt(id));
        if (card.getMoney() < sumPayment) {
            throw new AppException("Sum higher than number money on card");
        }
        Card destinationCard = manager.findCardByNumber(Long.parseLong(destination));
        if (destinationCard == null) {
            throw new AppException("Destination card does not exists");
        }
        if (destinationCard.getActivityId() == 1) {
            throw new AppException("Card user was blocked");
        }

        HttpSession session = request.getSession();
        session.setAttribute("card_id", id);
        session.setAttribute("sum", sum);
        session.setAttribute("destination", destination);

        LOG.debug("Command finished");
        return Path.PAGE_USER_CONFIRM_PAYMENT;
    }
}
