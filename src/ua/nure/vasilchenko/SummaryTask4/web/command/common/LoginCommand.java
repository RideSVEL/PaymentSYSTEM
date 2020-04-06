package ua.nure.vasilchenko.SummaryTask4.web.command.common;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import ua.nure.vasilchenko.SummaryTask4.Path;
import ua.nure.vasilchenko.SummaryTask4.db.DBManager;
import ua.nure.vasilchenko.SummaryTask4.db.entity.User;
import ua.nure.vasilchenko.SummaryTask4.db.enumeration.Activity;
import ua.nure.vasilchenko.SummaryTask4.db.enumeration.Role;
import ua.nure.vasilchenko.SummaryTask4.exception.AppException;
import ua.nure.vasilchenko.SummaryTask4.exception.Messages;
import ua.nure.vasilchenko.SummaryTask4.web.command.base.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Login command.
 *
 * @author S. Vasilchenko
 */
public class LoginCommand extends Command {

    private static final long serialVersionUID = -3071536593627692473L;

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        // obtain login and password from a request
        DBManager manager = DBManager.getInstance();
        String login = request.getParameter("login");
        LOG.trace("Request parameter: login --> " + login);

        String password = request.getParameter("password");
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            throw new AppException(Messages.LOGINPASSWORD_CANNOT_BE_EMPTY);
        }

        User user = manager.findUserByLogin(login);
        LOG.trace("Found in DB: user --> " + user);

        String md5Hex = DigestUtils.md5Hex(password);

        if (user == null || !md5Hex.equals(user.getPassword())) {
            throw new AppException(Messages.CANNOT_FIND_USER_WITH_SUCH_LOGIN);
        }

        Activity activity = Activity.getActivity(user);
        LOG.trace("userActivity --> " + activity);
        if (activity == Activity.BLOCKED) {
            throw new AppException(Messages.YOUR_ACCOUNT_WAS_BLOCKED);
        }

        Role userRole = Role.getRole(user);
        LOG.trace("userRole --> " + userRole);

        String forward = Path.PAGE_ERROR_PAGE;

        if (userRole == Role.ADMIN) {
            forward = Path.COMMAND_LIST_USERS;
        }

        if (userRole == Role.CLIENT) {
            forward = Path.COMMAND_USER_CARDS;
        }

        session.setAttribute("user", user);
        LOG.trace("Set the session attribute: user --> " + user);

        session.setAttribute("userRole", userRole);
        LOG.trace("Set the session attribute: userRole --> " + userRole);

        LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());

        LOG.debug("Command finished");
        return forward;
    }

}
