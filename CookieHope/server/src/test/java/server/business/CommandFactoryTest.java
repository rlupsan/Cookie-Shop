package server.business;

import api.model.*;
import org.junit.Test;
import org.mockito.Mockito;
import server.dao.CookieDao;
import server.dao.DaoFactory;
import server.dao.OrderDao;
import server.dao.UserDao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class CommandFactoryTest {

    @Test
    public void testAddToCart() {
        DaoFactory mockDaoFactory = Mockito.mock(DaoFactory.class);
        CookieDao cookieDao = Mockito.mock(CookieDao.class);
        OrderDao orderDao = Mockito.mock(OrderDao.class);
        Mockito.when(mockDaoFactory.getCookieDao()).thenReturn(cookieDao);
        Mockito.when(mockDaoFactory.getOrderDao()).thenReturn(orderDao);

        Order theOrder = new Order();
        Mockito.when(orderDao.findById(1)).thenReturn(theOrder);
        Cookie theCookie = new Cookie();
        theCookie.setQuantityOnStock(10);
        Mockito.when(cookieDao.findById(1)).thenReturn(theCookie);

        CommandFactory.setDaoFactory(mockDaoFactory);

        Function<Map<String, Object>, Object> function = CommandFactory.create("addToCart");
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", 1L);
        params.put("cookieId", 1L);
        params.put("quantity", 7);

        Object result = function.apply(params);
        assertEquals("The first String should match the result", "added to cart", result);
        Mockito.verify(orderDao).update(theOrder);
        assertEquals("Size should be 1",1, theOrder.getCookies().size());
    }

    @Test
    public void testCreateOrder() {
        DaoFactory mockDaoFactory = Mockito.mock(DaoFactory.class);

        OrderDao orderDao = Mockito.mock(OrderDao.class);
        UserDao userDao = Mockito.mock(UserDao.class);
        Mockito.when(mockDaoFactory.getOrderDao()).thenReturn(orderDao);
        Mockito.when(mockDaoFactory.getUserDao()).thenReturn(userDao);

        User user = new User();
        Mockito.when(userDao.findById(2)).thenReturn(user);

        Order order = new Order();
        order.setUser(user);
        order.setDate(new Date());

        CommandFactory.setDaoFactory(mockDaoFactory);

        Function<Map<String, Object>, Object> function = CommandFactory.create("createOrder");
        Map<String, Object> params = new HashMap<>();
        params.put("userId", 2L);

        Object result = function.apply(params);
        assertEquals("The order should match the result", order, result);

        Mockito.verify(orderDao).save(order);
    }


    @Test
    public void testFinishOrder() {
        DaoFactory mockDaoFactory = Mockito.mock(DaoFactory.class);
        CookieDao cookieDao = Mockito.mock(CookieDao.class);
        OrderDao orderDao = Mockito.mock(OrderDao.class);
        UserDao userDao = Mockito.mock(UserDao.class);
        Mockito.when(mockDaoFactory.getCookieDao()).thenReturn(cookieDao);
        Mockito.when(mockDaoFactory.getOrderDao()).thenReturn(orderDao);
        Mockito.when(mockDaoFactory.getUserDao()).thenReturn(userDao);

        Order theOrder = new Order();
        Mockito.when(orderDao.findById(1)).thenReturn(theOrder);
        theOrder.setFinished(true);

        Cookie cookie = new Cookie();
        CommandFactory.setDaoFactory(mockDaoFactory);

        Function<Map<String, Object>, Object> function = CommandFactory.create("finishOrder");
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", 1L);

        Object result = function.apply(params);
        assertEquals("The first String should match the result", "Order finished", result);

        Mockito.verify(cookieDao).update(cookie);
        Mockito.verify(orderDao).update(theOrder);
        User user = theOrder.getUser();
        Mockito.verify(userDao).update(user);

    }

    @Test
    public void testPostComment() {
        DaoFactory mockDaoFactory = Mockito.mock(DaoFactory.class);
        CookieDao cookieDao = Mockito.mock(CookieDao.class);
        Mockito.when(mockDaoFactory.getCookieDao()).thenReturn(cookieDao);
        UserDao userDao = Mockito.mock(UserDao.class);
        Mockito.when(mockDaoFactory.getUserDao()).thenReturn(userDao);

        Cookie cookie = new Cookie();
        Mockito.when(cookieDao.findById(1)).thenReturn(cookie);
        User user = new User();
        Mockito.when(userDao.findById(2)).thenReturn(user);

        CommandFactory.setDaoFactory(mockDaoFactory);

        Function<Map<String, Object>, Object> function = CommandFactory.create("postComment");
        Map<String, Object> params = new HashMap<>();
        params.put("cookieId", 1L);
        params.put("userId", 2L);
        params.put("commentText", "Delicioussss");

        Object result = function.apply(params);
        assertEquals("the result should be cookie",cookie, result);

        Mockito.verify(cookieDao).update(cookie);

        assertEquals("1 should match the result", 1, cookie.getComments().size());
        assertEquals("1 should match the result", 1, user.getComments().size());

    }

    @Test
    public void testAddToFav() {
        DaoFactory mockDaoFactory = Mockito.mock(DaoFactory.class);
        CookieDao cookieDao = Mockito.mock(CookieDao.class);
        UserDao userDao = Mockito.mock(UserDao.class);
        Mockito.when(mockDaoFactory.getCookieDao()).thenReturn(cookieDao);
        Mockito.when(mockDaoFactory.getUserDao()).thenReturn(userDao);

        Cookie cookie = new Cookie();
        Mockito.when(cookieDao.findById(1)).thenReturn(cookie);

        User user = new User();
        Mockito.when(userDao.findById(2)).thenReturn(user);

        CommandFactory.setDaoFactory(mockDaoFactory);

        Function<Map<String, Object>, Object> function = CommandFactory.create("addToFav");
        Map<String, Object> params = new HashMap<>();
        params.put("userId", 2L);
        params.put("cookieId", 1L);

        Object result = function.apply(params);
        assertEquals("The first String should match the result", "added to fav", result);
        Mockito.verify(userDao).update(user);
        assertEquals("1 should match the user.getFav().size",1, user.getFavorites().size());
    }

    @Test
    public void testEditUser() {
        DaoFactory mockDaoFactory = Mockito.mock(DaoFactory.class);
        UserDao userDao = Mockito.mock(UserDao.class);
        Mockito.when(mockDaoFactory.getUserDao()).thenReturn(userDao);
        User user = new User();
        Mockito.when(userDao.findById(3)).thenReturn(user);
        CommandFactory.setDaoFactory(mockDaoFactory);
        user.setUsername("Roxi");
        user.setPassword("minge*M9");
        user.setWalletAmount(400.0);
        user.setType(UserType.REGULAR_USER);
        Function<Map<String, Object>, Object> function = CommandFactory.create("editUser");
        Map<String, Object> params = new HashMap<>();
        params.put("user", user);

        Object result = function.apply(params);
        assertEquals("The result should be the edited user", "user edited", result);
        Mockito.verify(userDao).update(user);
    }


    @Test
    public void testEditCookie() {
        DaoFactory mockDaoFactory = Mockito.mock(DaoFactory.class);
        CookieDao cookieDao = Mockito.mock(CookieDao.class);
        Mockito.when(mockDaoFactory.getCookieDao()).thenReturn(cookieDao);
        Cookie cookie = new Cookie();
        Mockito.when(cookieDao.findById(1)).thenReturn(cookie);
        CommandFactory.setDaoFactory(mockDaoFactory);
        cookie.setName("Milka");
        cookie.setType(CookieType.APRICOT_PISTACHIO);
        cookie.setPrice(7.0);
        cookie.setQuantityOnStock(5);
        cookie.setQuantityOfSweeteners(7);
        Function<Map<String, Object>, Object> function = CommandFactory.create("editCookie");
        Map<String, Object> params = new HashMap<>();
        params.put("cookie", cookie);

        Object result = function.apply(params);
        assertEquals("The result should be the edited cookie", "cookie edited", result);
        Mockito.verify(cookieDao).update(cookie);
    }


    @Test
    public void testInsertCookie() {
        DaoFactory mockDaoFactory = Mockito.mock(DaoFactory.class);
        CookieDao cookieDao = Mockito.mock(CookieDao.class);
        Mockito.when(mockDaoFactory.getCookieDao()).thenReturn(cookieDao);
        Cookie cookie = new Cookie();
        CommandFactory.setDaoFactory(mockDaoFactory);
        cookie.setId(1L);
        cookie.setName("Milka");
        cookie.setType(CookieType.APRICOT_PISTACHIO);
        cookie.setPrice(7.0);
        cookie.setQuantityOnStock(5);
        cookie.setQuantityOfSweeteners(7);
        Function<Map<String, Object>, Object> function = CommandFactory.create("editCookie");
        Map<String, Object> params = new HashMap<>();
        params.put("cookie", cookie);

        Object result = function.apply(params);
        assertEquals("The result should be the new inserted cookie", "cookie inserted", result);
        Mockito.verify(cookieDao).update(cookie);
    }

    @Test
    public void testInsertUser() {
        DaoFactory mockDaoFactory = Mockito.mock(DaoFactory.class);
        UserDao userDao = Mockito.mock(UserDao.class);
        Mockito.when(mockDaoFactory.getUserDao()).thenReturn(userDao);
        User user = new User();
        CommandFactory.setDaoFactory(mockDaoFactory);
        user.setId(4L);
        user.setUsername("Flavia");
        user.setPassword("salut6#7");
        user.setWalletAmount(90.0);
        user.setType(UserType.ADMIN_USER);
        Function<Map<String, Object>, Object> function = CommandFactory.create("insertUser");
        Map<String, Object> params = new HashMap<>();
        params.put("user", user);

        Object result = function.apply(params);
        assertEquals("The result should be the new inserted user", "user inserted", result);
        Mockito.verify(userDao).update(user);
    }


    @Test
    public void testGetAllUsers() {
        DaoFactory mockDaoFactory = Mockito.mock(DaoFactory.class);
        UserDao userDao = Mockito.mock(UserDao.class);
        Mockito.when(mockDaoFactory.getUserDao()).thenReturn(userDao);

        assertEquals("They should both match", userDao.findAll(), userDao.findAll());
    }

    @Test
    public void testGetAllCookie() {
        DaoFactory mockDaoFactory = Mockito.mock(DaoFactory.class);
        CookieDao cookieDao = Mockito.mock(CookieDao.class);
        Mockito.when(mockDaoFactory.getCookieDao()).thenReturn(cookieDao);

        assertEquals("They should both match", cookieDao.findAll(), cookieDao.findAll());
    }


}