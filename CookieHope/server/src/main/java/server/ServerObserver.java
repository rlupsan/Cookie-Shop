package server;

import java.io.ObjectOutputStream;

public class ServerObserver {

    private final String username;

    private final ObjectOutputStream out;

    public ServerObserver(String username, ObjectOutputStream out) {
        this.username = username;
        this.out = out;
    }

    public String getUsername() {
        return username;
    }

    public ObjectOutputStream getOut() {
        return out;
    }
}
