package server.business;

import api.model.Comment;
import api.model.Cookie;
import api.model.Order;
import api.model.OrderPart;
import api.model.User;
import server.Server;
import server.ServerObserver;
import server.dao.CookieDao;
import server.dao.DaoFactory;
import server.dao.OrderDao;
import server.dao.UserDao;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public final class CommandFactory {

    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static DaoFactory daoFactory = DaoFactory.getInstance();

    private CommandFactory() {
        //empty constructor because it is a utility class
    }

    public static Function<Map<String, Object>, Object> create(String command) {
        switch (command) {
            case "login":
                return params1 -> doLogin(params1);
            case "getAllCookies":
                return t -> getAllCookies();
            case "getAllUsers":
                return t -> getAllUsers();
            case "insertCookie":
                return param -> insertCookie(param);
            case "updateCookie":
                return param -> editCookie(param);
            case "insertUser":
                return param -> insertUser(param);
            case "updateUser":
                return param -> editUser(param);
            case "addToFav":
                return params1 -> addToFav(params1);
            case "addToCart":
                return params -> addToCart(params);
            case "createOrder":
                return params -> createOrder(params);
            case "finishOrder":
                return params -> finishOrder(params);
            case "postComment":
                return params -> postComment(params);
            default:
                return (params) -> null;
        }
    }

    private static Cookie postComment(Map<String, Object> params) {
        Long cookieId = (Long) params.get("cookieId");
        Long userId = (Long) params.get("userId");
        String commentText = (String) params.get("commentText");

        CookieDao cookieDao = daoFactory.getCookieDao();
        UserDao userDao = daoFactory.getUserDao();

        User user = userDao.findById(userId);
        Cookie cookie = cookieDao.findById(cookieId);

        Comment comment = new Comment();
        comment.setCookie(cookie);
        comment.setText(commentText);
        comment.setAuthor(user);

        cookie.getComments().add(comment);
        user.getComments().add(comment);

        cookieDao.update(cookie);

        return cookie;
    }

    private static Object finishOrder(Map<String, Object> params) {
        OrderDao orderDao = daoFactory.getOrderDao();
        CookieDao cookieDao = daoFactory.getCookieDao();

        Long orderId = (Long) params.get("orderId");
        Order order = orderDao.findById(orderId);
        order.setFinished(true);

        int orderTotal = 0;
        Set<OrderPart> cookies = order.getCookies();
        for (OrderPart part : cookies) {
            Cookie cookie = part.getCookie();
            int prevQty = cookie.getQuantityOnStock();
            int newQty = prevQty - part.getQuantity();
            orderTotal += part.getQuantity();
            cookie.setQuantityOnStock(newQty);
            cookieDao.update(cookie);
        }
        orderDao.update(order);

        User user = order.getUser();
        double walletAmount = user.getWalletAmount();
        user.setWalletAmount(walletAmount - orderTotal);
        daoFactory.getUserDao().update(user);

        return "Order finished";
    }

    private static Object createOrder(Map<String, Object> params) {
        UserDao userDao = daoFactory.getUserDao();
        Long userId = (Long) params.get("userId");
        User user = userDao.findById(userId);

        OrderDao orderDao = daoFactory.getOrderDao();
        Order order = new Order();
        order.setUser(user);
        order.setDate(new Date());
        orderDao.save(order);

        return order;
    }

    private static Object addToCart(Map<String, Object> params) {
        CookieDao cookieDao = daoFactory.getCookieDao();
        OrderDao orderDao = daoFactory.getOrderDao();

        Integer quantity = (Integer) params.get("quantity");

        Long cookieId = (Long) params.get("cookieId");
        Cookie cookie = cookieDao.findById(cookieId);

        Long orderId = (Long) params.get("orderId");
        Order order = orderDao.findById(orderId);

        int prevQty = cookie.getQuantityOnStock();
        if (prevQty < quantity) {
            return "Not enough quantity";
        }

        OrderPart orderPart = new OrderPart();
        orderPart.setCookie(cookie);
        orderPart.setQuantity(quantity);
        orderPart.setOrder(order);

        order.getCookies().add(orderPart);
        orderDao.update(order);

        return "added to cart";
    }

    private static Object addToFav(Map<String, Object> params) {
        UserDao userDao = daoFactory.getUserDao();
        CookieDao cookieDao = daoFactory.getCookieDao();

        Long userId = (Long) params.get("userId");
        User user = userDao.findById(userId);

        Long cookieId = (Long) params.get("cookieId");
        Cookie cookie = cookieDao.findById(cookieId);

        user.getFavorites().add(cookie);
        userDao.update(user);

        return "added to fav";
    }

    private static Object editUser(Map<String, Object> param) {
        UserDao userDao = daoFactory.getUserDao();
        User userToEdit = (User) param.get("user");
        userDao.update(userToEdit);
        return "user edited";
    }

    private static Object insertUser(Map<String, Object> param) {
        UserDao userDao = daoFactory.getUserDao();
        User userToInsert = (User) param.get("user");
        userDao.insert(userToInsert);
        return "user inserted";
    }

    private static Object editCookie(Map<String, Object> param) {
        CookieDao cookieDao = daoFactory.getCookieDao();
        Cookie cookieToEdit = (Cookie) param.get("cookie");
        cookieDao.update(cookieToEdit);
        notifyObservers(cookieToEdit.getId());
        return "cookie edited";
    }

    private static void notifyObservers(long cookieId) {
        Cookie cookie = daoFactory.getCookieDao().findById(cookieId);
        Set<User> users = cookie.getUsers();
        Set<String> usernames = users.stream().map(User::getUsername).collect(Collectors.toSet());
        List<ServerObserver> observers = Server.getInstance().getServerObservers();
        for (ServerObserver observer : observers) {
            if (usernames.contains(observer.getUsername())) {
                sendNotification(observer, cookie);
            }
        }

    }

    private static void sendNotification(ServerObserver observer, Cookie cookie) {
        try {
            ObjectOutputStream out = observer.getOut();
            out.writeObject(cookie.toString());
        } catch (IOException e) {
            logger.log(Level.ALL, "Problem in sending notification!", e);
        }
    }

    private static Object insertCookie(Map<String, Object> param) {
        CookieDao cookieDao = daoFactory.getCookieDao();
        Cookie cookieToInsert = (Cookie) param.get("cookie");
        cookieDao.insert(cookieToInsert);
        return "cookie inserted";
    }

    private static Object getAllUsers() {
        return daoFactory.getUserDao().findAll();
    }

    private static Object getAllCookies() {
        CookieDao cookieDao = daoFactory.getCookieDao();
        //List<Cookie> all = cookieDao.findAll();
        return cookieDao.findAll();
    }

    private static Object doLogin(Map<String, Object> params) {
        UserBLL userBLL = new UserBLL();
        return userBLL.performLogin((String) params.get("username"), (String) params.get("password"));
        //return target;
    }

    public static void setDaoFactory(DaoFactory daoFactory) {
        CommandFactory.daoFactory = daoFactory;
    }
}
