package ua.nure.vasilchenko.SummaryTask4.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.vasilchenko.SummaryTask4.Path;
import ua.nure.vasilchenko.SummaryTask4.db.DBManager;
import ua.nure.vasilchenko.SummaryTask4.db.entity.User;
import ua.nure.vasilchenko.SummaryTask4.exception.AppException;
import ua.nure.vasilchenko.SummaryTask4.web.command.base.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

/**
 * Updated settings.
 * Update firstName and lastName.
 * Update localization interface.
 *
 * @author S. Vasilchenko
 */
public class UpdateSettingsCmd extends Command {

    private static final String ENGLISH = "English";
    private static final String RUSSIAN = "Russian";
    private static final String UKRAINIAN = "Ukrainian";

    private static final long serialVersionUID = -3071587547692473L;

    private static final Logger LOG = Logger.getLogger(UpdateSettingsCmd.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        DBManager manager = DBManager.getInstance();
        User user = (User) request.getSession().getAttribute("user");
        LOG.trace("Get user from session: " + user);
        String language = request.getParameter("locale");
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        LOG.trace("Get parameters: locale, name, lastname");
        if (name == null || lastName == null || name.isEmpty() || lastName.isEmpty()) {
            LOG.debug("fields was empty");
        } else {
            user.setLastName(lastName);
            user.setFirstName(name);
            try {
                LOG.trace("Updating user" + user);
                manager.updateUser(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Locale loc;
        switch (language) {
            default:
            case ENGLISH:
                loc = new Locale("en");
                request.setAttribute("locale", "en");
                LOG.debug("Set locale " + ENGLISH);
                break;
            case RUSSIAN:
                loc = new Locale("ru");
                request.setAttribute("locale", "ru");
                LOG.debug("Set locale " + RUSSIAN);
                break;
            case UKRAINIAN:
                loc = new Locale("uk");
                request.setAttribute("locale", "uk");
                LOG.debug("Set locale " + UKRAINIAN);
                break;
        }
        Locale.setDefault(loc);
        LOG.debug("Set locale " + loc);

        LOG.debug("Command finished");
        return Path.COMMAND_VIEW_SETTINGS;
    }
}
