package by.tc.web.controller.control.command.constants;

import by.tc.web.service.order.OrderService;
import by.tc.web.service.order.OrderServiceFactory;
import by.tc.web.service.paginator.Paginator;
import by.tc.web.service.paginator.PaginatorFactory;
import by.tc.web.service.user.UserService;
import by.tc.web.service.user.UserServiceFactory;

public final class ControllerConstants {
    public static final String PREVIOUS_PAGE_PARAM = "from";
    public static final String LOCALE_PARAM = "locale";
    public static final String ID_PARAM = "id";
    public static final String DISCOUNT_PARAM = "discount";
    public static final String CURRENT_PAGE_PARAM = "page";
    public static final int ITEMS_PER_PAGE = 5;
    public static final String PHONE_PARAM = "phone";
    public static final String NAME_PARAM = "name";
    public static final String SURNAME_PARAM = "surname";
    public static final String FIRST_PASSWORD_PARAM = "first_password";
    public static final String SECOND_PASSWORD_PARAM = "second_password";
    public static final String CAR_NUMBER_PARAM = "car_number";
    public static final String CAR_MODEL_PARAM = "car_model";
    public static final String PASSWORD_PARAM = "password";
    public static final String OLD_PASSWORD_PARAM = "old_password";
    public static final String USER_PARAM = "user";
    public static final String ERROR = "error";
    public static final String ORDER_PARAM = "order";


    public static final UserService customerService = UserServiceFactory.getInstance().getCustomerService();
    public static final UserService taxiDriverService = UserServiceFactory.getInstance().getTaxiDriverService();
    public static final UserService administratorService = UserServiceFactory.getInstance().getAdministratorService();
    public static final OrderService orderService = OrderServiceFactory.getInstance().getOrderService();

    public static final Paginator customerPaginator = PaginatorFactory.getInstance().getCustomerPaginator();
    public static final Paginator taxiDriverPaginator = PaginatorFactory.getInstance().getTaxiDriverPaginator();
    public static final Paginator orderPaginator = PaginatorFactory.getInstance().getOrderPaginator();
}
