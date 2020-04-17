package client.controller;


import api.model.User;
import client.communication.util.CommunicationUtil;
import client.view.AdminView;
import client.view.LoginView;
import client.view.UserView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

public class LoginController {

    private final LoginView view;

    public LoginController(LoginView view) {
        this.view = view;
        view.getLoginButton().setOnAction(actionEvent -> callLogin());
    }

    private void callLogin() {
        User user = CommunicationUtil.login(view.getUsername(), view.getPassword());
        if (user == null) {
            showErrorMessage("Invalid username/password");
        }

        switch (user.getType()) {
            case ADMIN_USER:
                showAdminView();
                break;
            case REGULAR_USER:
                showUserView(user);
                break;
            default:
                showErrorMessage("Invalid username/password");
                break;
        }
    }

    private void showAdminView() {
        Scene scene = view.getScene();
        scene.setRoot(new AdminView());
        scene.getWindow().sizeToScene();
    }

    private void showUserView(User user) {
        Scene scene1 = view.getScene();
        scene1.setRoot(new UserView(user));
        scene1.getWindow().sizeToScene();
        CommunicationUtil.subscribeToNotification(user);
    }

    public void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Login");
        alert.setHeaderText("Your credentials are invalid!");
        alert.setContentText(message);
        alert.showAndWait();
    }

}
