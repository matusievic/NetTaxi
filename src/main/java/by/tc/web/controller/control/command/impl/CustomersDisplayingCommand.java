package by.tc.web.controller.control.command.impl;

import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.domain.pagination.Pagination;
import by.tc.web.service.paginator.Paginator;
import by.tc.web.service.paginator.PaginatorFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomersDisplayingCommand implements ControllerCommand {
    private static final String CURRENT_PAGE_PARAM = "page";
    private static final int ITEMS_PER_PAGE = 30;
    private static final Paginator paginator = PaginatorFactory.getInstance().getCustomerPaginator();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentPageParam = req.getParameter(CURRENT_PAGE_PARAM);

        int currentPage = 1, lastPage = 1;
        try {
            currentPage = Integer.parseInt(currentPageParam);
        } catch (NumberFormatException e) {
            //TODO
        }

        Pagination pagination = paginator.paginate(currentPage, ITEMS_PER_PAGE);

        req.setAttribute("customers", pagination.getData());
        req.setAttribute("currentPage", pagination.getCurrentPage());
        req.setAttribute("lasPage", pagination.getLastPage());

        req.getRequestDispatcher("/administrator/customers").forward(req, resp);
    }
}
