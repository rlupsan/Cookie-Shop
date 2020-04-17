package server.business;


import api.model.User;
import server.business.user.IdUserValidator;
import server.business.user.PasswordValidator;
import server.business.user.UsernameValidator;
import server.business.user.WalletAmountValidator;
import server.dao.DaoFactory;

import java.util.ArrayList;
import java.util.List;

public class UserBLL {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public UserBLL() {
        List<Validator<User>> validators = new ArrayList<>();
        validators.add(new PasswordValidator());
        validators.add(new UsernameValidator());
        validators.add(new IdUserValidator());
        validators.add(new WalletAmountValidator());
    }

    public User performLogin(String username, String password) {
        User fromDb = daoFactory.getUserDao().findByUsername(username);
        if (fromDb.getPassword().equals(password)) {
            return fromDb;
        }
        return null;
    }
}
