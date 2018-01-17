package by.tc.web.service.paginator.impl;

import by.tc.web.dao.DAO;
import by.tc.web.dao.DAOFactory;
import by.tc.web.dao.exception.DAOException;
import by.tc.web.service.paginator.AbstractPaginator;

public final class CustomerPaginator extends AbstractPaginator {
    private static final DAO dao = DAOFactory.getInstance().getCustomerDAO();

    @Override
    protected Object[] getData(int begin, int end, Object... params) {
        Object[] result = null;
        try {
            result = dao.readInRange(begin, end);
        } catch (DAOException e) {
            //TODO
        }
        return result;
    }

    @Override
    protected int getLength() {
        int result = 0;
        try {
            result = dao.readLength();
        } catch (DAOException e) {
            //TODO
        }
        return result;
    }
}
