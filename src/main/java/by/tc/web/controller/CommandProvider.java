package by.tc.web.controller;

import by.tc.web.controller.impl.AuthenticationCommand;
import by.tc.web.controller.impl.LocalizationCommand;
import by.tc.web.controller.impl.RegistrationCommand;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private static Map<CommandName, ControllerCommand> commands = new HashMap<>();

    static {
        commands.put(CommandName.AUTHENTICATE, new AuthenticationCommand());
        commands.put(CommandName.REGISTER, new RegistrationCommand());
        commands.put(CommandName.LOCALIZE, new LocalizationCommand());
    }

    public static ControllerCommand takeCommand(String name) {
        return commands.get(CommandName.valueOf(name));
    }
}
