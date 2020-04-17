package server;

import api.Request;
import server.business.CommandFactory;
import server.dao.impl.util.HibernateUtil;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Server {

    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static Server instance = null;
    private List<ServerObserver> serverObservers;

    private Server() {
        serverObservers = Collections.synchronizedList(new ArrayList<>());
    }

    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
            HibernateUtil.getSessionFactory();
        }
        return instance;
    }

    public static void main(String[] args) {
        //Server server = Server.getInstance();
        Server.getInstance().start();
    }

    public void start() {
        logger.log(Level.INFO, "Listening for connections");
        try (ServerSocket serverSocket = new ServerSocket(1997)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Thread thread = new Thread(() -> serveRequest(clientSocket));
                thread.start();
            }
        } catch (IOException e) {
            logger.log(Level.WARNING, "Problem in starting the server", e);
        }
    }

    public List<ServerObserver> getServerObservers() {
        return serverObservers;
    }

    private void serveRequest(Socket clientSocket) {
        logger.log(Level.INFO, "Serving Request");
        try {
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            //receive request data
            Request request = (Request) in.readObject();
            String command = request.getCommandName();
            if ("subscribeToNotification".equals(command)) { //special case
                String username = (String) request.getParameter("username");
                serverObservers.add(new ServerObserver(username, out));
                return; // avoids closing the socket
            }

            // process request
            Function<Map<String, Object>, Object> function = CommandFactory.create(command);
            Object result = function.apply(request.getParameters());

            //send response to client
            out.writeObject(result);

            //close
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.WARNING, "Problem in Serving Request", e);
        }
    }
}
