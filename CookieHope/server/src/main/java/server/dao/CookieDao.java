package server.dao;


import api.model.Cookie;
import api.model.CookieType;

import java.util.List;

public interface CookieDao {
    List<Cookie> findAll();

    Cookie findById(long id);

    Cookie findByType(CookieType type);

    Cookie findByPrice(double price);

    Cookie findByQuantityOfSweeteners(double quantityOfSweeteners);

    void delete(Cookie objectToDelete);

    void update(Cookie objectToUpdate);

    void insert(Cookie objectToCreate);
}
