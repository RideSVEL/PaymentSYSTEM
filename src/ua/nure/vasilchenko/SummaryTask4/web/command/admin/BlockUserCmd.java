package ua.nure.vasilchenko.SummaryTask4.web.command.admin;

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

/**
 * Update status user on 'BLOCKED' in DB.
 *
 * @author S. Vasilchenko
 */
public class BlockUserCmd extends Command {

    private static final long serialVersionUID = 77234589478505L;

    private static final Logger LOG = Logger.getLogger(BlockUserCmd.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        DBManager manager = DBManager.getInstance();
        String id = request.getParameter("user_id");
        User user = manager.findUser(Integer.parseInt(id));
        LOG.trace("Found in DB: user --> " + user);
        user.setActivityId(1);
        try {
            manager.updateUser(user);
            LOG.trace("update in DB: user --> " + user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOG.debug("Command finished");
        return Path.COMMAND_LIST_USERS;
    }
}
