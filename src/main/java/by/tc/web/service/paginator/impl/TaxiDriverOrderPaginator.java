package by.tc.web.service.paginator.impl;

import by.tc.web.dao.DAOFactory;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.dao.order.OrderDAO;
import by.tc.web.service.paginator.AbstractPaginator;

public class TaxiDriverOrderPaginator extends AbstractPaginator {
    private static final OrderDAO dao = (OrderDAO) DAOFactory.getInstance().getOrderDAO();

    @Override
    protected Object[] getData(int begin, int end, Object... params) {
        Object[] result = null;
        try {
            dao.readByTaxiDriverInRange(begin, end, (int) params[0]);
        } catch (DAOException e) {
            //TODO
        }
        return result;
    }

    @Override
    protected int getLength() {
        return 0;
    }
}
