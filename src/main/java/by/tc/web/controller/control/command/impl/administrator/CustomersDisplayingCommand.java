package by.tc.web.controller.control.command.impl.administrator;

import by.tc.web.controller.control.command.ControllerCommand;
import by.tc.web.controller.control.command.constants.ControllerConstants;
import by.tc.web.controller.control.command.impl.AbstractCommand;
import by.tc.web.domain.pagination.Pagination;
import by.tc.web.service.converter.Converter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class CustomersDisplayingCommand extends AbstractCommand implements ControllerCommand {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentPageParam = req.getParameter(ControllerConstants.CURRENT_PAGE_PARAM);
        Optional<Integer> currentPage = Converter.parseInt(currentPageParam);

        Pagination pagination = customerPaginator.paginate(currentPage.orElse(1), ControllerConstants.ITEMS_PER_PAGE);

        req.setAttribute("customers", pagination.getData());
        req.setAttribute("currentPage", pagination.getCurrentPage());
        req.setAttribute("lasPage", pagination.getLastPage());

        req.getRequestDispatcher("/administrator/customers").forward(req, resp);
    }
}
