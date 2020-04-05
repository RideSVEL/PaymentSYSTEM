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

public class SearchUserCmd extends Command {

    private static final long serialVersionUID = 7732289478505L;

    private static final Logger LOG = Logger.getLogger(SearchUserCmd.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        // get users list list
        User user;
        String search = request.getParameter("search");
        if (search == null || search.isEmpty()) {
            throw new AppException(Messages.NO_SUCH_USER_WITH_THIS_LOGIN + search);
        } else {
            user = DBManager.getInstance().findUserByLogin(search);
        }

        List<User> users = new ArrayList<>();
        if (user != null) {
            users.add(user);
        } else {
            throw new AppException(Messages.NO_SUCH_USER_WITH_THIS_LOGIN + search);
        }
        LOG.trace("Found in DB: usersList --> " + users);

        request.setAttribute("users", users);
        request.setAttribute("searching", true);
        LOG.trace("Set the request attribute: users --> " + users);

        LOG.debug("Command finished");
        return Path.PAGE_LIST_USERS;
    }
}
