package server.dao;

import api.model.Cookie;
import api.model.CookieType;
import org.junit.Assert;
import org.junit.Test;

public class CookieDaoTest {
    @Test
    public void testFindById() {
        Cookie cookie = DaoFactory.getInstance().getCookieDao().findById(1);
        Assert.assertEquals(cookie.getName(), "OREO");
        Assert.assertEquals(cookie.getType(), CookieType.CHOCOLATE_CHIP_COOKIE);
    }

}
