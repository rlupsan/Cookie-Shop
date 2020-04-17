package server.dao;


import api.model.User;

import java.util.List;

public interface UserDao {
    User findById(long id);

    User findByUsername(String username);

    void delete(User objectToDelete);

    void update(User objectToUpdate);

    void insert(User objectToCreate);

    List<User> findAll();
}
