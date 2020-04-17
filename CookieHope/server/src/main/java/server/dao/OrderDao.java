package server.dao;


import api.model.Order;

public interface OrderDao {

    void save(Order objectToCreate);

    void update(Order order);

    Order findById(long id);
}
