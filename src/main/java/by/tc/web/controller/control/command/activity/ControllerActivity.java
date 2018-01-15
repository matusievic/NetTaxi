package by.tc.web.controller.control.command.activity;

public enum ControllerActivity {
    // Common activities
    AUTHENTICATE,
    LOCALIZE,
    CLOSE_SESSION,

    UPDATE_ACCOUNT,
    DELETE_ACCOUNT,

    DISPLAY_ORDERS,


    // Customer activities
    REGISTER_CUSTOMER,
    ORDER_TAXI,
    CANCEL_ORDER,
    RATE_TAXIDRIVER,


    // Driver activities
    CHECK_ORDERS,
    CHOOSE_ORDER,
    ACCEPT_ORDER,
    FINISH_ORDER,


    // Administrator activities
    DISPLAY_CUSTOMERS,
    DISPLAY_CUSTOMER,
    BLOCK_CUSTOMER,
    UNBLOCK_CUSTOMER,
    DISCOUNT_CUSTOMER,

    DISPLAY_TAXIDRIVERS,
    DISPLAY_TAXIDRIVER,
    REGISTER_TAXIDRIVER,
    BLOCK_TAXIDRIVER,
    UNBLOCK_TAXIDRIVER,

    DISPLAY_ORDER,
    DELETE_ORDER
}
