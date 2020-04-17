package server.dao;

import api.model.User;
import api.model.UserType;
import org.junit.Assert;
import org.junit.Test;


public class UserDaoTest {
    @Test
    public void testFindById() {
        User user = DaoFactory.getInstance().getUserDao().findById(2);
        Assert.assertEquals(user.getUsername(), "user");
        Assert.assertEquals(user.getPassword(), "user");
        Assert.assertEquals(user.getType(), UserType.REGULAR_USER);
    }

    @Test
    public void testFindByUsername() {
        User user = DaoFactory.getInstance().getUserDao().findByUsername("admin");
        Assert.assertEquals(user.getPassword(), "admin");
        Assert.assertEquals(user.getType(), UserType.ADMIN_USER);
    }
}
