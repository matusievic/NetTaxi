package by.tc.web.controller.control.command.activity;

public enum ControllerActivity {
    // Unregistered user activities
    AUTHENTICATE,
    LOCALIZE,
    REGISTER_CUSTOMER,


    // Account related activities
    UPDATE_ACCOUNT,
    DELETE_ACCOUNT,
    CLOSE_SESSION,


    // Common activities
    DISPLAY_ORDER,
    DISPLAY_ORDERS,


    // Customer activities
    ORDER_TAXI,
    FIND_TAXIDRIVER,
    CANCEL_ORDER,
    RATE_ORDER,
    GET_ACTIVE_CUSTOMER_ORDER,


    // Driver activities
    GET_ACTIVE_TAXIDRIVER_ORDER,
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
    DELETE_ORDER
}
