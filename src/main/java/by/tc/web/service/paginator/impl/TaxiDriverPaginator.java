package by.tc.web.service.paginator.impl;

import by.tc.web.dao.DAO;
import by.tc.web.dao.DAOFactory;
import by.tc.web.dao.user.UserDAO;
import by.tc.web.service.paginator.AbstractPaginator;

public final class TaxiDriverPaginator extends AbstractPaginator {
    private static final DAO dao = (UserDAO) DAOFactory.getInstance().getTaxiDriverDAO();

    @Override
    protected DAO getDAO() {
        return dao;
    }
}
