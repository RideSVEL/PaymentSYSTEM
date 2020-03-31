package ua.nure.vasilchenko.SummaryTask4.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.vasilchenko.SummaryTask4.Path;
import ua.nure.vasilchenko.SummaryTask4.db.DBManager;
import ua.nure.vasilchenko.SummaryTask4.db.entity.Card;
import ua.nure.vasilchenko.SummaryTask4.db.entity.User;
import ua.nure.vasilchenko.SummaryTask4.exception.AppException;
import ua.nure.vasilchenko.SummaryTask4.web.command.admin.ShowCardsCmd;
import ua.nure.vasilchenko.SummaryTask4.web.command.base.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserShowCardCmd extends Command {

    private static final long serialVersionUID = 7732123567234L;

    private static final Logger LOG = Logger.getLogger(ShowCardsCmd.class);

    private List<Card> filterCards(List<Card> cards, String filter) {
        List<Card> result = new ArrayList<>();
        switch (filter) {
            case "all":
                return cards;
            case "active":
                for (Card card : cards) {
                    if (card.getActivityId() == 0) {
                        result.add(card);
                    }
                }
                return result;
            case "block":
                for (Card card : cards) {
                    if (card.getActivityId() == 1) {
                        result.add(card);
                    }
                }
                return result;
        }
        return cards;
    }

    private List<Card> sortingCards(List<Card> cards, String sorting, String order) {
        switch (sorting) {
            case "name":
                cards.sort(Comparator.comparing(Card::getName));
                if ("descending".equals(order)) {
                    Collections.reverse(cards);
                }
                break;
            case "number":
                cards.sort(Comparator.comparingLong(Card::getNumber));
                if ("descending".equals(order)) {
                    Collections.reverse(cards);
                }
                break;
            case "money":
                cards.sort(Comparator.comparingInt(Card::getMoney));
                if ("descending".equals(order)) {
                    Collections.reverse(cards);
                }
                break;
        }
        return cards;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        User user = (User) request.getSession().getAttribute("user");
        DBManager manager = DBManager.getInstance();
        // get user cards list
        List<Card> cards = manager.getUserCards(user);
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

            } else {
                cards = sortingCards(filterCards(cards, filter), sorting, order);
            }
        } else {
            request.getSession().setAttribute("sorting", sorting);
            request.getSession().setAttribute("order", order);
            request.getSession().setAttribute("filter", filter);
            cards = sortingCards(filterCards(cards, filter), sorting, order);
        }
        LOG.trace("Found in DB: cardsList --> " + cards);

        request.setAttribute("cards", cards);

        int count = manager.countRequest(user);
        request.getSession().setAttribute("count", count);

        LOG.trace("Set the request attribute: cards --> " + cards);

        LOG.debug("Command finished");
        return Path.PAGE_USER_CARDS;
    }
}