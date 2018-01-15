package by.tc.web.service.paginator.impl;

import by.tc.web.dao.DAO;
import by.tc.web.dao.DAOFactory;
import by.tc.web.service.paginator.AbstractPaginator;

public class OrderPaginator extends AbstractPaginator {
    private static final DAO dao = DAOFactory.getInstance().getOrderDAO();

    @Override
    protected DAO getDAO() {
        return dao;
    }
}
