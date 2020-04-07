package ua.nure.vasilchenko.SummaryTask4.web.command.client;

import org.apache.log4j.Logger;
import ua.nure.vasilchenko.SummaryTask4.Path;
import ua.nure.vasilchenko.SummaryTask4.exception.AppException;
import ua.nure.vasilchenko.SummaryTask4.web.command.base.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command confirm block card user.
 *
 * @author S. Vasilchenko
 */
public class ConfirmBlockCmd extends Command {

    private static final long serialVersionUID = 7723435677478505L;

    private static final Logger LOG = Logger.getLogger(ConfirmBlockCmd.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String id = request.getParameter("card_id");
        LOG.trace("get parameter card_id" + id);

        request.setAttribute("card_id", id);
        LOG.trace("set attribute card_block" + id);

        LOG.debug("Command finished");
        return Path.PAGE_USER_BLOCK_ACTION;
    }
}
