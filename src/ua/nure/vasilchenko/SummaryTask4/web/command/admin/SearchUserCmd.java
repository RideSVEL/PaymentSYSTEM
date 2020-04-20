package ua.nure.vasilchenko.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.vasilchenko.SummaryTask4.Path;
import ua.nure.vasilchenko.SummaryTask4.db.DBManager;
import ua.nure.vasilchenko.SummaryTask4.db.entity.User;
import ua.nure.vasilchenko.SummaryTask4.exception.AppException;
import ua.nure.vasilchenko.SummaryTask4.exception.Messages;
import ua.nure.vasilchenko.SummaryTask4.web.command.base.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Gets from the page header.jspf.
 * Find user in DB satisfying login in input field.
 *
 * @author S. Vasilchenko
 */
public class SearchUserCmd extends Command {

    private static final long serialVersionUID = 7732289478505L;

    private static final Logger LOG = Logger.getLogger(SearchUserCmd.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        DBManager manager = DBManager.getInstance();
        // get users list list
        List<User> searching;
        String search = request.getParameter("search");
        if (search == null || search.isEmpty()) {
            throw new AppException(Messages.NO_SUCH_USER_WITH_THIS_LOGIN);
        } else {
            searching = manager.findAllUsersClient();
            LOG.trace("Found in DB: usersList --> " + searching);
        }
        List<User> users = new ArrayList<>();
        for (User user : searching) {
            Pattern p = Pattern.compile(search);
            Matcher m = p.matcher(user.getLogin());
            if (m.find()) {
                users.add(user);
            }
        }
        if (users.size() == 0) {
            throw new AppException(Messages.NO_SUCH_USER_WITH_THIS_LOGIN);
        }
        request.setAttribute("users", users);
        request.setAttribute("searching", true);
        request.setAttribute("searchField", search);
        LOG.trace("Set the request attribute: users --> " + users);

        LOG.debug("Command finished");
        return Path.PAGE_LIST_USERS;
    }
}
