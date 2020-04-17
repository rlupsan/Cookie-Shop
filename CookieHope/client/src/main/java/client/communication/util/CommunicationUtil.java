package client.communication.util;

import api.Request;
import api.model.Cookie;
import api.model.Order;
import api.model.User;
import client.communication.NotificationListener;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class CommunicationUtil {

    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private CommunicationUtil() {
        //private constructor to prevent instantiation because it is a utility class
    }

    public static User login(String username, String password) {
        try (Socket socket = new Socket(InetAddress.getLoopbackAddress().getHostAddress(), 1997);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            Request request = new Request();
            request.setCommandName("login");
            request.putParameter("username", username);
            request.putParameter("password", password);

            out.writeObject(request);
            //Object response = in.readObject();
            return (User) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.WARNING, "Problem in user login!", e);
            return null;
        }
    }


    public static List<Cookie> getAllCookies() {
        try (Socket socket = new Socket(InetAddress.getLoopbackAddress().getHostAddress(), 1997);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            Request request = new Request();
            request.setCommandName("getAllCookies");

            out.writeObject(request);
            Object response = in.readObject();
            if (response == null) {
                return new ArrayList<>();
            }
            return (List<Cookie>) response;

        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.WARNING, "Problem in getting all the cookies!", e);
            return null;
        }
    }

    public static List<User> getAllUsers() {
        try (Socket socket = new Socket(InetAddress.getLoopbackAddress().getHostAddress(), 1997);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            Request request = new Request();
            request.setCommandName("getAllUsers");

            out.writeObject(request);
            Object response = in.readObject();
            if (response == null) {
                return new ArrayList<>();
            }
            return (List<User>) response;

        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.WARNING, "Problem in getting all the users!", e);
            return null;
        }
    }

    public static void insertCookie(Cookie cookie) {
        try (Socket socket = new Socket(InetAddress.getLoopbackAddress().getHostAddress(), 1997);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            Request request = new Request();
            request.setCommandName("insertCookie");
            request.putParameter("cookie", cookie);

            out.writeObject(request);
            Object response = in.readObject();
            logger.log(Level.INFO, (String) response);

        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.WARNING, "Problem in inserting cookies!", e);
        }
    }

    public static void updateCookie(Cookie cookie) {
        try (Socket socket = new Socket(InetAddress.getLoopbackAddress().getHostAddress(), 1997);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            Request request = new Request();
            request.setCommandName("updateCookie");
            request.putParameter("cookie", cookie);

            out.writeObject(request);
            Object response = in.readObject();
            logger.log(Level.INFO, (String) response);

        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.WARNING, "Problem in updating cookie!", e);
        }
    }

    public static void deleteCookie(Long cookieId) {
        try (Socket socket = new Socket(InetAddress.getLoopbackAddress().getHostAddress(), 1997);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            Request request = new Request();
            request.setCommandName("deleteCookie");
            request.putParameter("cookieId", cookieId);

            out.writeObject(request);
            Object response = in.readObject();
            logger.log(Level.INFO, (String) response);

        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.WARNING, "Problem in deleting cookie.", e);
        }
    }

    public static User findUserById(long id) {
        try (Socket socket = new Socket(InetAddress.getLoopbackAddress().getHostAddress(), 1997);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            Request request = new Request();
            request.setCommandName("findUserById");
            request.putParameter("idUser", id);

            out.writeObject(request);
            Object response = in.readObject();
            logger.log(Level.INFO, (String) response);
            return (User) response;

        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.WARNING, "Problem in finding user by id.", e);
            return null;
        }
    }

    public static void deleteUser(User user) {
        try (Socket socket = new Socket(InetAddress.getLoopbackAddress().getHostAddress(), 1997);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            Request request = new Request();
            request.setCommandName("deleteUser");
            request.putParameter("user", user);

            out.writeObject(request);
            Object response = in.readObject();
            logger.log(Level.INFO, (String) response);

        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.WARNING, "Problem in deleting user.", e);
        }
    }

    public static void insertUser(User user) {
        try (Socket socket = new Socket(InetAddress.getLoopbackAddress().getHostAddress(), 1997);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            Request request = new Request();
            request.setCommandName("insertUser");
            request.putParameter("user", user);

            out.writeObject(request);
            Object response = in.readObject();
            logger.log(Level.INFO, (String) response);

        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.WARNING, "Problem in inserting user.", e);
        }
    }

    public static void updateUser(User user) {
        try (Socket socket = new Socket(InetAddress.getLoopbackAddress().getHostAddress(), 1997);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            Request request = new Request();
            request.setCommandName("updateUser");
            request.putParameter("user", user);

            out.writeObject(request);
            Object response = in.readObject();
            logger.log(Level.INFO, (String) response);

        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.WARNING, "Problem in updating user.", e);
        }
    }

    public static String addToFav(Long userId, Long cookieId) {
        try (Socket socket = new Socket(InetAddress.getLoopbackAddress().getHostAddress(), 1997);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            Request request = new Request();
            request.setCommandName("addToFav");
            request.putParameter("userId", userId);
            request.putParameter("cookieId", cookieId);

            out.writeObject(request);
            //Object response = in.readObject();
            return (String) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.WARNING, "Problem in adding cookie to favourites", e);
            return "error";
        }
    }

    public static void subscribeToNotification(User user) {
        try {
            Socket socket = new Socket(InetAddress.getLoopbackAddress().getHostAddress(), 1997);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Request request = new Request();
            request.setCommandName("subscribeToNotification");
            request.putParameter("username", user.getUsername());

            out.writeObject(request);
            new Thread(new NotificationListener(in)).start();

        } catch (IOException e) {
            logger.log(Level.WARNING, "Problem in subscribing to notification", e);
        }
    }

    public static Order createOrder(long id) {
        try {
            Socket socket = new Socket(InetAddress.getLoopbackAddress().getHostAddress(), 1997);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Request request = new Request();
            request.setCommandName("createOrder");
            request.putParameter("userId", id);

            out.writeObject(request);
            return (Order) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.WARNING, "Problem in creating order", e);
        }
        return null;
    }

    public static String addToOrder(long orderId, long cookieId, int quantity) {
        try (Socket socket = new Socket(InetAddress.getLoopbackAddress().getHostAddress(), 1997);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            Request request = new Request();
            request.setCommandName("addToCart");
            request.putParameter("orderId", orderId);
            request.putParameter("cookieId", cookieId);
            request.putParameter("quantity", quantity);

            out.writeObject(request);
            //Object response = in.readObject();
            return (String) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.WARNING, "Problem in adding order", e);
            return "error";
        }
    }

    public static String finishOrder(long id) {
        try {
            Socket socket = new Socket(InetAddress.getLoopbackAddress().getHostAddress(), 1997);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Request request = new Request();
            request.setCommandName("finishOrder");
            request.putParameter("orderId", id);

            out.writeObject(request);
            return (String) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.WARNING, "Problem in finishing order", e);
        }
        return null;
    }

    public static Cookie postComment(String commentText, long id, long userId) {
        try {
            Socket socket = new Socket(InetAddress.getLoopbackAddress().getHostAddress(), 1997);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Request request = new Request();
            request.setCommandName("postComment");
            request.putParameter("cookieId", id);
            request.putParameter("userId", userId);
            request.putParameter("commentText", commentText);

            out.writeObject(request);
            return (Cookie) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.WARNING, "Problem in posting comment", e);
        }
        return null;
    }
}
