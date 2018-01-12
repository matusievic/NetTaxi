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
        commands.put(ControllerActivity.DRIVERS_DISPLAYING, new DriversDisplayingCommand());
    }

    public static ControllerCommand takeCommand(String name) {
        return commands.get(ControllerActivity.valueOf(name.toUpperCase()));
    }
}
