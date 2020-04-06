package ua.nure.vasilchenko.SummaryTask4.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.vasilchenko.SummaryTask4.Path;
import ua.nure.vasilchenko.SummaryTask4.db.DBManager;
import ua.nure.vasilchenko.SummaryTask4.db.entity.Payment;
import ua.nure.vasilchenko.SummaryTask4.db.entity.User;
import ua.nure.vasilchenko.SummaryTask4.exception.AppException;
import ua.nure.vasilchenko.SummaryTask4.web.command.base.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Command showing payments with parameters sorting.
 *
 * @author S. Vasilchenko
 */
public class ShowPaymentsCmd extends Command {

    private static final long serialVersionUID = 7732123567234L;

    private static final Logger LOG = Logger.getLogger(ShowPaymentsCmd.class);
    /**
     * Return list payments, only with choosing filter.
     *
     * @param payments - list all payments for filtering.
     * @param filter - for choosing filter.
     * @return - list payments, only with choosing filter.
     */
    private List<Payment> filterPayments(List<Payment> payments, String filter) {
        List<Payment> result = new ArrayList<>();
        switch (filter) {
            case "all":
                LOG.trace("getting all payments" + payments);
                return payments;
            case "send":
                for (Payment payment : payments) {
                    if (payment.getStatusId() == 1) {
                        result.add(payment);
                    }
                }
                LOG.trace("getting send payments" + result);
                return result;
            case "prepared":
                for (Payment payment : payments) {
                    if (payment.getStatusId() == 0) {
                        result.add(payment);
                    }
                }
                LOG.trace("getting prepared payments" + result);
                return result;
        }
        return payments;
    }
    /**
     * Return list payments, sorted by special parameters.
     *
     * @param payments list all Cards for sorting.
     * @param sorting - type sorting.
     * @param order - destination sorting.
     * @return - sorted list cards by type and destination.
     */
    private List<Payment> sortingPayments(List<Payment> payments, String sorting, String order) {
        switch (sorting) {
            case "date":
                if ("descending".equals(order)) {
                    Collections.reverse(payments);
                }
                LOG.trace("sorting by date" + payments);
                break;
            case "number":
                payments.sort(Comparator.comparingLong(Payment::getId));
                if ("descending".equals(order)) {
                    Collections.reverse(payments);
                }
                LOG.trace("sorting by number" + payments);
                break;
        }
        return payments;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        User user = (User) request.getSession().getAttribute("user");
        LOG.trace("get attribute from session" + user);
        DBManager manager = DBManager.getInstance();
        List<Payment> payments = manager.getUserPayments(user);
        for (Payment payment : payments) {
            payment.setCardNumber(manager.findCard(payment.getCardId()).getNumber());
            payment.setCardDestinationNumber(manager.findCard(payment.getCardDestinationId()).getNumber());
        }
        LOG.trace("setting cards numbers into payments " + payments);
        String sorting = request.getParameter("sorting");
        String order = request.getParameter("order");
        String filter = request.getParameter("filter");
        if (sorting == null || order == null || filter == null
                || sorting.isEmpty() || order.isEmpty() || filter.isEmpty()) {
            sorting = (String) request.getSession().getAttribute("sorting");
            order = (String) request.getSession().getAttribute("order");
            filter = (String) request.getSession().getAttribute("filter");
            if (sorting == null || order == null || filter == null
                    || sorting.isEmpty() || order.isEmpty() || filter.isEmpty()) {
                LOG.trace("fields was empty ");
            } else {
                payments = sortingPayments(filterPayments(payments, filter), sorting, order);
                LOG.trace("sorting payments" + payments);
            }
        } else {
            request.getSession().setAttribute("sorting", sorting);
            request.getSession().setAttribute("order", order);
            request.getSession().setAttribute("filter", filter);
            payments = sortingPayments(filterPayments(payments, filter), sorting, order);
            LOG.debug("setting sorting parameters into session");
        }
        request.setAttribute("payments", payments);
        LOG.trace("Set the request attribute: payments --> " + payments);
        LOG.debug("Command finished");
        return Path.PAGE_USER_PAYMENTS;
    }
}
