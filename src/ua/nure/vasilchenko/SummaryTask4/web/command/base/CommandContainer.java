package ua.nure.vasilchenko.SummaryTask4.web.command.base;

import org.apache.log4j.Logger;
import ua.nure.vasilchenko.SummaryTask4.web.command.admin.*;
import ua.nure.vasilchenko.SummaryTask4.web.command.client.*;
import ua.nure.vasilchenko.SummaryTask4.web.command.common.*;

import java.util.Map;
import java.util.TreeMap;

/**
 * Holder for all commands.
 *
 * @author S.Vasilchenko
 */
public class CommandContainer {

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<>();

    static {
        // common commands
        commands.put("login", new LoginCommand());
        commands.put("register", new RegisterCmd());
        commands.put("logout", new LogoutCommand());
        commands.put("noCommand", new NoCommand());
        commands.put("updateSettings", new UpdateSettingsCmd());

        // client commands
        commands.put("userShowCards", new UserShowCardCmd());
        commands.put("blockUserCard", new UserBlockCardCmd());
        commands.put("actionBlock", new ConfirmBlockCmd());
        commands.put("requestUserCardUnblock", new UserUnblockRequestCmd());
        commands.put("showClientRequest", new ClientRequestCmd());
        commands.put("showPayments", new ShowPaymentsCmd());
        commands.put("showIncome", new ShowIncomeCmd());
        commands.put("replenishBalance", new ReplenishBalanceCmd());
        commands.put("addBalance", new AddBalanceCmd());
        commands.put("confirmBalance", new ConfirmBalanceCmd());
        commands.put("sendPayment", new SendPaymentCmd());
        commands.put("checkPayment", new CheckPaymentCmd());
        commands.put("confirmPayment", new ConfirmPaymentCmd());
        commands.put("confirmDefer", new ConfirmDeferCmd());
        commands.put("createCard", new CreateCardCmd());
        commands.put("commandCard", new CardCmd());
        commands.put("deleteDefer", new DeleteDeferPaymentCmd());
        commands.put("getPdf", new GetPDFCmd());

        // admin commands
        commands.put("listUsers", new ListUsersCmd());
        commands.put("blockUser", new BlockUserCmd());
        commands.put("unblockUser", new UnblockUserCmd());
        commands.put("showCards", new ShowCardsCmd());
        commands.put("unblockCard", new UnblockCardCmd());
        commands.put("blockCard", new BlockCardCmd());
        commands.put("showRequests", new RequestCmd());
        commands.put("acceptRequest", new AcceptReqCmd());
        commands.put("rejectRequest", new RejectReqCmd());
        commands.put("searchUser", new SearchUserCmd());
        commands.put("createSelectionPayment", new CreateSelectionPayment());

        LOG.debug("Command container was successfully initialized");
        LOG.trace("Number of commands --> " + commands.size());
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }

}