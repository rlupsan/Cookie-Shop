package client.controller;

import api.model.Cookie;
import api.model.Order;
import api.model.User;
import client.communication.util.CommunicationUtil;
import client.view.CommentView;
import client.view.UserView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.List;

public class UserController {

    private final UserView view;
    private final User user;
    private Order currentOrder;

    public UserController(UserView view, User user) {
        this.view = view;
        this.user = user;
        refreshCookieTable();
        view.getAddFav().setOnAction(actionEvent -> addFavourites());
        view.getNewOrderButton().setOnAction(e -> createOrder());
        view.getAddToOrderButton().setOnAction(e -> addToOrder());
        view.getFinisOrderButton().setOnAction(e -> finishOrder());
        view.getCommentButton().setOnAction(e -> openCommentWindow());
    }

    private void openCommentWindow() {
        Cookie selectedItem = view.getCookieTableView().getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            return;
        }
        Stage commentWindow = new Stage();
        commentWindow.setTitle("Comments");
        CommentView commentView = new CommentView(selectedItem, user);
        commentWindow.setScene(new Scene(commentView));
        commentWindow.sizeToScene();
        commentWindow.showAndWait();
    }

    private void finishOrder() {
        if (currentOrder == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No order created!");
            alert.showAndWait();
            return;
        }
        String response = CommunicationUtil.finishOrder(currentOrder.getId());
        currentOrder = null;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(response);
        alert.showAndWait();
        refreshCookieTable();
    }

    private void addToOrder() {
        if (currentOrder == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No order created!");
            alert.showAndWait();
            return;
        }
        TableView<Cookie> cookieTableView = view.getCookieTableView();
        Cookie selectedItem = cookieTableView.getSelectionModel().getSelectedItem();
        String quantityText = view.getQuantityInput().getText();
        Integer quantity = Integer.parseInt(quantityText);
        String response;
        if (selectedItem == null) {
            response = "Please select cookie";
        } else {
            response = CommunicationUtil.addToOrder(currentOrder.getId(), selectedItem.getId(), quantity);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(response);
        alert.showAndWait();
    }

    private void createOrder() {
        currentOrder = CommunicationUtil.createOrder(user.getId());
        String response;
        if (currentOrder == null) {
            response = "Error in creating the order";
        }
        else {
            response = currentOrder.toString();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(response);
        alert.showAndWait();
    }

    private void addFavourites() {
        TableView<Cookie> cookieTableView = view.getCookieTableView();
        Cookie selectedItem = cookieTableView.getSelectionModel().getSelectedItem();
        String response;
        if (selectedItem == null) {
            response = "Please select cookie";
        } else {
            response = CommunicationUtil.addToFav(user.getId(), selectedItem.getId());
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(response);
        alert.showAndWait();
    }

    private void refreshCookieTable() {
        TableView<Cookie> cookieTableView = view.getCookieTableView();
        cookieTableView.getItems().removeAll();

        List<Cookie> cookies = CommunicationUtil.getAllCookies();

        ObservableList<Cookie> observableList = FXCollections.observableArrayList(cookies);
        cookieTableView.setItems(observableList);
        cookieTableView.refresh();
    }
}
