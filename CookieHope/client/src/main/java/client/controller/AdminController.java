package client.controller;

import api.model.Cookie;
import api.model.CookieType;
import api.model.User;
import api.model.UserType;
import client.communication.util.CommunicationUtil;
import client.view.AdminView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminController {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final AdminView view;

    public AdminController(AdminView view) {
        this.view = view;
        refreshCookieTable();
        refreshUserTable();
        view.getAddCookieButton().setOnAction(actionEvent -> addCookie());
        view.getEditCookieButton().setOnAction(actionEvent -> editCookie());
        view.getDeleteCookieButton().setOnAction(actionEvent -> deleteCookie());
        view.getAddUserButton().setOnAction(actionEvent -> addUser());
        view.getEditUserButton().setOnAction(actionEvent -> editUser());
        view.getDeleteUserButton().setOnAction(actionEvent -> deleteUser());
    }

    private void refreshUserTable() {
        TableView<User> userTableView = view.getUserTableView();
        userTableView.getItems().clear();

        List<User> users = CommunicationUtil.getAllUsers();

        ObservableList<User> observableList = FXCollections.observableList(users);
        userTableView.setItems(observableList);
        userTableView.refresh();
    }


    private void refreshCookieTable() {
        TableView<Cookie> cookieTableView = view.getCookieTableView();
        cookieTableView.getItems().clear();

        List<Cookie> cookies = CommunicationUtil.getAllCookies();

        ObservableList<Cookie> observableList = FXCollections.observableArrayList(cookies);
        cookieTableView.setItems(observableList);
        cookieTableView.refresh();
    }

    private void clearAllFields() {
        view.getIdCookieInput().clear();
        view.getNameInput().clear();
        view.getTypeInputCB().getSelectionModel().clearSelection();
        view.getPriceInput().clear();
        view.getQuantityOnStock().clear();
        view.getQuantityOfSweeteners().clear();
        view.getIdUserInput().clear();
        view.getUsernameInput().clear();
        view.getUserTypeInput().getSelectionModel().clearSelection();
        view.getPasswordInput().clear();
        view.getWalletAmountInput().clear();
    }

    private void addCookie() {
        try {
            Cookie cookie = new Cookie(
                    Integer.parseInt(view.getIdCookieInput().getText()),
                    view.getNameInput().getText(),
                    CookieType.valueOf(view.getTypeInputCB().getValue()),
                    Double.parseDouble(view.getPriceInput().getText()),
                    Integer.parseInt(view.getQuantityOfSweeteners().getText()),
                    Integer.parseInt(view.getQuantityOnStock().getText())
            );
            CommunicationUtil.insertCookie(cookie);
            refreshCookieTable();
            clearAllFields();
        } catch (Exception e1) {
            logger.log(Level.ALL, "Problem in inserting cookie!", e1);
        }
    }

    private void editCookie() {
        try {
            Cookie cookie = new Cookie(
                    Integer.parseInt(view.getIdCookieInput().getText()),
                    view.getNameInput().getText(),
                    CookieType.valueOf(view.getTypeInputCB().getValue()),
                    Double.parseDouble(view.getPriceInput().getText()),
                    Integer.parseInt(view.getQuantityOfSweeteners().getText()),
                    Integer.parseInt(view.getQuantityOnStock().getText())
            );
            CommunicationUtil.updateCookie(cookie);
            refreshCookieTable();
            clearAllFields();
        } catch (Exception e1) {
            logger.log(Level.ALL, "Problem in editing cookie!", e1);
        }
    }

    private void deleteCookie() {
        try {
            Cookie selectedItem = view.getCookieTableView().getSelectionModel().getSelectedItem();
            long idSelected = selectedItem.getId();
            CommunicationUtil.deleteCookie(idSelected);
            refreshCookieTable();
            clearAllFields();
        } catch (Exception e1) {
            logger.log(Level.ALL, "Problem in deleting cookie!", e1);
        }
    }

    private void deleteUser() {
        try {
            User selectedItem = view.getUserTableView().getSelectionModel().getSelectedItem();
            long idSelected = selectedItem.getId();
            User user = CommunicationUtil.findUserById(idSelected);
            CommunicationUtil.deleteUser(user);
            refreshUserTable();
        } catch (Exception e1) {
            logger.log(Level.ALL, "Problem in deleting user!", e1);
        }
    }

    private void editUser() {
        try {
            User user = new User(
                    Integer.parseInt(view.getIdUserInput().getText()),
                    view.getUsernameInput().getText(),
                    view.getPasswordInput().getText(),
                    Double.parseDouble(view.getWalletAmountInput().getText()),
                    UserType.valueOf(view.getUserTypeInput().getValue()));
            CommunicationUtil.updateUser(user);
            refreshUserTable();
            clearAllFields();
        } catch (Exception e1) {
            logger.log(Level.ALL, "Problem in editing user!", e1);
        }
    }

    private void addUser() {
        try {
            User user = new User(
                    Integer.parseInt(view.getIdUserInput().getText()),
                    view.getUsernameInput().getText(),
                    view.getPasswordInput().getText(),
                    Double.parseDouble(view.getWalletAmountInput().getText()),
                    UserType.valueOf(view.getUserTypeInput().getValue()));
            CommunicationUtil.insertUser(user);
            refreshUserTable();
            clearAllFields();
        } catch (Exception e1) {
            logger.log(Level.ALL, "Problem in inserting user!", e1);
        }
    }
}
