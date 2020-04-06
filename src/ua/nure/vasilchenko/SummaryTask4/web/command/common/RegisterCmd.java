package ua.nure.vasilchenko.SummaryTask4.web.command.common;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import ua.nure.vasilchenko.SummaryTask4.CardNumber;
import ua.nure.vasilchenko.SummaryTask4.Path;
import ua.nure.vasilchenko.SummaryTask4.db.DBManager;
import ua.nure.vasilchenko.SummaryTask4.db.entity.Card;
import ua.nure.vasilchenko.SummaryTask4.db.entity.User;
import ua.nure.vasilchenko.SummaryTask4.exception.AppException;
import ua.nure.vasilchenko.SummaryTask4.exception.Messages;
import ua.nure.vasilchenko.SummaryTask4.web.command.base.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
/**
 * Register command.
 *
 * @author S. Vasilchenko
 */
public class RegisterCmd extends Command {

    private static final long serialVersionUID = 3081536593568942473L;

    private static final Logger LOG = Logger.getLogger(RegisterCmd.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        LOG.debug("Command starts");
        DBManager manager = DBManager.getInstance();
        String login = request.getParameter("login");
        LOG.trace("Request parameter: login --> " + login);
        String password = request.getParameter("password");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        if (login == null || password == null || firstName == null || lastName == null ||
                login.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            throw new AppException(Messages.FIELDS_CANNOT_BE_EMPTY);
        }
        User user = manager.findUserByLogin(login);
        LOG.trace("Found in DB: user --> " + user);

        if (user != null) {
            throw new AppException(Messages.THIS_USER_ALREADY_EXTIST);
        }

        String md5Hex = DigestUtils.md5Hex(password);
        LOG.trace("encryption password -> " + md5Hex);

        user = new User();
        user.setLogin(login);
        user.setPassword(md5Hex);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        String forward = Path.PAGE_LOGIN;

        try {
            LOG.debug("Inserted user into db");
            manager.insertUser(user);
        } catch (SQLException e) {
            LOG.debug("Error insert user");
            e.printStackTrace();
        }

        user = manager.findUserByLogin(user.getLogin());

        Card card = new Card();
        card.setNumber(CardNumber.createCardNumber());
        card.setUserId(user.getId());
        card.setName(user.getLogin());
        try {
            manager.insertCard(card);
            LOG.debug("Insert card in DB" + card);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOG.debug("Command finished");

        return forward;
    }
}
