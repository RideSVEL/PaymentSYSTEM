package ua.nure.vasilchenko.SummaryTask4.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.vasilchenko.SummaryTask4.CardNumber;
import ua.nure.vasilchenko.SummaryTask4.Path;
import ua.nure.vasilchenko.SummaryTask4.db.DBManager;
import ua.nure.vasilchenko.SummaryTask4.db.entity.Card;
import ua.nure.vasilchenko.SummaryTask4.db.entity.User;
import ua.nure.vasilchenko.SummaryTask4.exception.AppException;
import ua.nure.vasilchenko.SummaryTask4.web.command.base.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CreateCardCmd extends Command {

    private static final long serialVersionUID = 3088745587456473L;

    private static final Logger LOG = Logger.getLogger(CreateCardCmd.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        String name = request.getParameter("name");
        if (name == null || name.isEmpty()) {
            throw new AppException("Fields cannot be empty");
        }
        DBManager manager = DBManager.getInstance();
        User user = (User) request.getSession().getAttribute("user");
        List<Card> cards = manager.getUserCards(user);
        for (Card card : cards) {
            if (name.equals(card.getName())) {
                throw new AppException("Card with this name is already exists");
            }
        }
        Card card = new Card();
        card.setName(name);
        card.setUserId(user.getId());
        card.setNumber(CardNumber.createCardNumber());
        try {
            manager.insertCard(card);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Path.COMMAND_USER_CARDS;

    }
}
