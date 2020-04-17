package client.communication;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NotificationListener implements Runnable {


    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private ObjectInputStream input;

    public NotificationListener(ObjectInputStream in) {
        input = in;
    }

    @Override
    public void run() {

        while (true) {
            try {
                String message = (String) input.readObject();
                Platform.runLater(() -> showAlert(message));
            } catch (IOException | ClassNotFoundException e) {
                logger.log(Level.ALL, "Problem in notification!", e);
            }
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Favorite Cookie Updated");
        alert.setHeaderText("Check out the changes!");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
