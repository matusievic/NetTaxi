package by.tc.web.service.user.impl;

import by.tc.web.dao.DAO;
import by.tc.web.dao.DAOFactory;
import by.tc.web.domain.user.impl.Administrator;
import by.tc.web.service.user.AbstractUserService;

public class AdministratorService extends AbstractUserService {
    private static final DAO<Administrator> dao = DAOFactory.getInstance().getAdministratorDAO();

    @Override
    protected DAO<Administrator> getDAO() {
        return dao;
    }
}
