package by.tc.web.controller.control.command;

import by.tc.web.controller.control.command.activity.ControllerActivity;
import by.tc.web.controller.control.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private static Map<ControllerActivity, ControllerCommand> commands = new HashMap<>();

    static {
        commands.put(ControllerActivity.AUTHENTICATE, new AuthenticationCommand());
        commands.put(ControllerActivity.REGISTER_CUSTOMER, new CustomerRegistrationCommand());
        commands.put(ControllerActivity.REGISTER_TAXIDRIVER, new TaxiDriverRegistrationCommand());
        commands.put(ControllerActivity.LOCALIZE, new LocalizationCommand());
        commands.put(ControllerActivity.DISPLAY_TAXIDRIVERS, new TaxiDriversDisplayingCommand());
        commands.put(ControllerActivity.DISPLAY_TAXIDRIVER, new TaxiDriverDisplayingCommand());
        commands.put(ControllerActivity.BLOCK_TAXIDRIVER, new TaxiDriverBlockingCommand());
        commands.put(ControllerActivity.UNBLOCK_TAXIDRIVER, new TaxiDriverUnblockingCommand());
        commands.put(ControllerActivity.DISPLAY_CUSTOMERS, new CustomersDisplayingCommand());
        commands.put(ControllerActivity.DISPLAY_CUSTOMER, new CustomerDisplayingCommand());
        commands.put(ControllerActivity.BLOCK_CUSTOMER, new CustomerBlockingCommand());
        commands.put(ControllerActivity.UNBLOCK_CUSTOMER, new CustomerUnblockingCommand());
        commands.put(ControllerActivity.DISCOUNT_CUSTOMER, new CustomerDiscountingCommand());
        commands.put(ControllerActivity.UPDATE_ADMINISTRATOR, new AdministratorUpdatingCommand());
        commands.put(ControllerActivity.DELETE_ADMINISTRATOR, new AdministratorDeletingCommand());
        commands.put(ControllerActivity.CLOSE_SESSION, new CloseSessionCommand());
    }

    public static ControllerCommand takeCommand(String name) {
        return commands.get(ControllerActivity.valueOf(name.toUpperCase()));
    }
}
