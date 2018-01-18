package by.tc.web.controller.control.command;

import by.tc.web.controller.control.command.activity.ControllerActivity;
import by.tc.web.controller.control.command.impl.common.*;
import by.tc.web.controller.control.command.impl.administrator.*;
import by.tc.web.controller.control.command.impl.customer.CustomerActiveOrderReceivingCommand;
import by.tc.web.controller.control.command.impl.customer.CustomerRegistrationCommand;
import by.tc.web.controller.control.command.impl.customer.OrderTaxiPageDisplayingCommand;
import by.tc.web.controller.control.command.impl.customer.TaxiDriverFindingCommand;
import by.tc.web.controller.control.command.impl.driver.TaxiDriverActiveOrderReceivingCommand;

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
        commands.put(ControllerActivity.UPDATE_ACCOUNT, new AccountUpdatingCommand());
        commands.put(ControllerActivity.DELETE_ACCOUNT, new AccountDeletingCommand());
        commands.put(ControllerActivity.CLOSE_SESSION, new SessionClosingCommand());
        commands.put(ControllerActivity.DISPLAY_ORDERS, new OrdersDisplayingCommand());
        commands.put(ControllerActivity.DISPLAY_ORDER, new OrderDisplayingCommand());
        commands.put(ControllerActivity.DELETE_ORDER, new OrderDeletingCommand());
        commands.put(ControllerActivity.DISPLAY_ORDER_TAXI_PAGE, new OrderTaxiPageDisplayingCommand());
        commands.put(ControllerActivity.FIND_TAXIDRIVER, new TaxiDriverFindingCommand());
        commands.put(ControllerActivity.GET_ACTIVE_TAXIDRIVER_ORDER, new TaxiDriverActiveOrderReceivingCommand());
        commands.put(ControllerActivity.GET_ACTIVE_CUSTOMER_ORDER, new CustomerActiveOrderReceivingCommand());
    }

    public static ControllerCommand takeCommand(String name) {
        return commands.get(ControllerActivity.valueOf(name.toUpperCase()));
    }
}
