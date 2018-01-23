package by.tc.web.controller.filter;

import by.tc.web.controller.control.command.activity.ControllerActivity;

import java.util.EnumSet;
import java.util.Set;

public final class FilterConstants {
    public static final String ERROR = "error";
    public static final String SIGN_IN = "signin";
    public static final String USER_PARAM = "user";
    public static final String COMMAND_PARAM = "command";

    public static final Set<ControllerActivity> customerActivities;
    public static final Set<ControllerActivity> taxiDriverActivities;
    public static final Set<ControllerActivity> administratorActivities;
    public static final Set<ControllerActivity> accountActivities;
    public static final Set<ControllerActivity> unregisteredUserActivities;

    static {
        customerActivities = EnumSet.of(ControllerActivity.ORDER_TAXI,
                                        ControllerActivity.FIND_TAXIDRIVER,
                                        ControllerActivity.CANCEL_ORDER,
                                        ControllerActivity.RATE_ORDER,
                                        ControllerActivity.GET_ACTIVE_CUSTOMER_ORDER);

        taxiDriverActivities = EnumSet.of(ControllerActivity.GET_ACTIVE_TAXIDRIVER_ORDER,
                                          ControllerActivity.CHOOSE_ORDER,
                                          ControllerActivity.ACCEPT_ORDER,
                                          ControllerActivity.FINISH_ORDER);

        administratorActivities = EnumSet.of(ControllerActivity.DISPLAY_CUSTOMERS,
                                             ControllerActivity.DISPLAY_CUSTOMER,
                                             ControllerActivity.BLOCK_CUSTOMER,
                                             ControllerActivity.UNBLOCK_CUSTOMER,
                                             ControllerActivity.DISCOUNT_CUSTOMER,
                                             ControllerActivity.DISPLAY_TAXIDRIVERS,
                                             ControllerActivity.DISPLAY_TAXIDRIVER,
                                             ControllerActivity.REGISTER_TAXIDRIVER,
                                             ControllerActivity.BLOCK_TAXIDRIVER,
                                             ControllerActivity.UNBLOCK_TAXIDRIVER,
                                             ControllerActivity.DELETE_ORDER);

        accountActivities = EnumSet.of(ControllerActivity.AUTHENTICATE,
                                       ControllerActivity.LOCALIZE,
                                       ControllerActivity.REGISTER_CUSTOMER,
                                       ControllerActivity.UPDATE_ACCOUNT,
                                       ControllerActivity.DELETE_ACCOUNT,
                                       ControllerActivity.CLOSE_SESSION,
                                       ControllerActivity.DISPLAY_ORDER,
                                       ControllerActivity.DISPLAY_ORDERS);

        unregisteredUserActivities = EnumSet.of(ControllerActivity.AUTHENTICATE,
                                                ControllerActivity.LOCALIZE,
                                                ControllerActivity.REGISTER_CUSTOMER);
    }
}
