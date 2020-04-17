package client.view;


import client.controller.LoginController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginView extends Application {
    private TextField usernameInput;
    private PasswordField passwordInput;
    private Scene scene;
    private Button loginButton;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cookie Shop");
        GridPane gridPaneLogin = new GridPane();
        gridPaneLogin.setPadding(new Insets(30, 30, 30, 30));
        gridPaneLogin.setVgap(8);
        gridPaneLogin.setHgap(10);

        Label label1 = new Label("Username");
        GridPane.setConstraints(label1, 0, 1);

        usernameInput = new TextField();
        GridPane.setConstraints(usernameInput, 1, 1);

        Label label2 = new Label("Password");
        GridPane.setConstraints(label2, 0, 2);

        passwordInput = new PasswordField();
        GridPane.setConstraints(passwordInput, 1, 2);

        loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1, 4);

        gridPaneLogin.getChildren().addAll(label1, label2, usernameInput, passwordInput, loginButton);
        gridPaneLogin.setAlignment(Pos.CENTER);

        scene = new Scene(gridPaneLogin);
        primaryStage.setTitle("Cookie Shop");
        primaryStage.setScene(scene);
        primaryStage.show();
        new LoginController(this);
    }

    public String getUsername() {
        return usernameInput.getText();
    }

    public String getPassword() {
        return passwordInput.getText();
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public Scene getScene() {
        return scene;
    }
}
