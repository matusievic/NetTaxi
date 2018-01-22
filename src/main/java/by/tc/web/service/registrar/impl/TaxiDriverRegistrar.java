package by.tc.web.service.registrar.impl;

import by.tc.web.dao.DAO;
import by.tc.web.dao.DAOFactory;
import by.tc.web.domain.user.impl.TaxiDriver;
import by.tc.web.service.registrar.AbstractRegistrar;

public class TaxiDriverRegistrar extends AbstractRegistrar {
    private static final DAO<TaxiDriver> dao = DAOFactory.getInstance().getTaxiDriverDAO();

    @Override
    protected DAO<TaxiDriver> getDAO() {
        return dao;
    }
}
