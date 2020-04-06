package ua.nure.vasilchenko.SummaryTask4.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.vasilchenko.SummaryTask4.Path;
import ua.nure.vasilchenko.SummaryTask4.db.DBManager;
import ua.nure.vasilchenko.SummaryTask4.db.Fields;
import ua.nure.vasilchenko.SummaryTask4.db.entity.Card;
import ua.nure.vasilchenko.SummaryTask4.exception.AppException;
import ua.nure.vasilchenko.SummaryTask4.exception.Messages;
import ua.nure.vasilchenko.SummaryTask4.web.command.base.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Checking payment on satisfying all condition payment.
 * Throws exception, if they will be upset.
 *
 * @author S. Vasilchenko
 */
public class CheckPaymentCmd extends Command {

    private static final long serialVersionUID = 7851333478505L;

    private static final Logger LOG = Logger.getLogger(CheckPaymentCmd.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        String id = request.getParameter("card_id");
        String sum = request.getParameter("sum");
        String destination = request.getParameter("destination");
        LOG.trace("get attribute from session");
        DBManager manager = DBManager.getInstance();
        if (sum == null || id == null || destination == null || id.isEmpty() || sum.isEmpty() || destination.isEmpty()) {
            throw new AppException(Messages.FIELDS_CANNOT_BE_EMPTY);
        }
        int sumPayment = Integer.parseInt(sum);
        if (sumPayment > Fields.PAYMENT_MAX_SUM) {
            throw new AppException(Messages.SUM_HIGHER_THAN_MAX);
        }
        if (destination.length() != Fields.CARDS_NUMBER) {
            throw new AppException(Messages.CARD_DESTINATION_IS_NOT_VALID);
        }
        Card card = manager.findCard(Integer.parseInt(id));
        LOG.trace("Found in DB: card --> " + card);
        if (card.getMoney() < sumPayment) {
            throw new AppException(Messages.SUM_HIGHER_THAN_MONEY);
        }
        Card destinationCard = manager.findCardByNumber(Long.parseLong(destination));
        LOG.trace("Found in DB: destinationCard --> " + destinationCard);
        if (destinationCard == null) {
            throw new AppException(Messages.DESTINATION_CARD_DOES_NOTE_EXISTS);
        }
        if (destinationCard.getActivityId() == 1) {
            throw new AppException(Messages.CARD_USER_WAS_BLOCKED);
        }

        HttpSession session = request.getSession();
        session.setAttribute("card_id", id);
        session.setAttribute("sum", sum);
        session.setAttribute("destination", destination);
        LOG.trace("setting attribute on session --> " + id + " " + sum + " " + destination);

        LOG.debug("Command finished");
        return Path.PAGE_USER_CONFIRM_PAYMENT;
    }
}
