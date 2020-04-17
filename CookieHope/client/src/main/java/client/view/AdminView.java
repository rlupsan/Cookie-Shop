package client.view;


import api.model.Cookie;
import api.model.CookieType;
import api.model.User;
import api.model.UserType;
import client.controller.AdminController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class AdminView extends VBox {
    private TableView<Cookie> cookieTableView;
    private TableView<User> userTableView;
    private TextField idCookieInput;
    private TextField nameInput;
    private TextField priceInput;
    private TextField quantityOfSweeteners;
    private TextField quantityOnStock;
    private TextField idUserInput;
    private TextField usernameInput;
    private TextField passwordInput;
    private TextField walletAmountInput;
    private TextField noDaysInput;
    private ComboBox<String> userTypeInput;
    private ComboBox<String> fileTypeCB;
    private ComboBox<String> typeInputCB;
    private Button addUserButton;
    private Button editUserButton;
    private Button deleteUserButton;
    private Button addCookieButton;
    private Button editCookieButton;
    private Button deleteCookieButton;

    public AdminView() {

        setFillWidth(true);
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(10);
        gridPane.setVgap(8);

        cookieTableView = new TableView<>();
        Cookie cookie = new Cookie(-1, "Cookie1", CookieType.APRICOT_PISTACHIO, 23, 50, 10);
        TableGenerator<Cookie> cookieTableGenerator = new TableGenerator<>();
        cookieTableView = cookieTableGenerator.generateTable(cookie);
        cookieTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        idCookieInput = new TextField();
        idCookieInput.setPromptText("id");
        idCookieInput.setEditable(true);
        idCookieInput.setPrefWidth(70);

        nameInput = new TextField();
        nameInput.setPromptText("name");
        nameInput.setEditable(true);
        nameInput.setPrefWidth(150);

        typeInputCB = new ComboBox<>();
        typeInputCB.setPromptText("TYPE");
        typeInputCB.getItems().addAll("CHOCOLATE_CHIP_COOKIE", "BAVARIAN_CARAMEL", "CINNAMON_ROLL",
                "APRICOT_PISTACHIO", "PEANUT_BUTTER_JELLY");
        typeInputCB.setPrefWidth(200);

        priceInput = new TextField();
        priceInput.setPromptText("price");
        priceInput.setEditable(true);
        priceInput.setPrefWidth(100);

        quantityOfSweeteners = new TextField();
        quantityOfSweeteners.setPromptText("quantity of sweeteners");
        quantityOfSweeteners.setEditable(true);
        quantityOfSweeteners.setPrefWidth(150);

        quantityOnStock = new TextField();
        quantityOnStock.setPromptText("quantity on stock");
        quantityOnStock.setEditable(true);
        quantityOnStock.setPrefWidth(100);

        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(idCookieInput, nameInput, typeInputCB, priceInput, quantityOfSweeteners, quantityOnStock);

        addCookieButton = new Button("Add");
        editCookieButton = new Button("Edit");
        deleteCookieButton = new Button("Delete");
        deleteCookieButton.setDisable(true);

        HBox hBox1 = new HBox(10);
        hBox1.getChildren().addAll(addCookieButton, editCookieButton, deleteCookieButton);

        idUserInput = new TextField();
        idUserInput.setPromptText("id");
        idUserInput.setEditable(true);
        idUserInput.setPrefWidth(70);

        usernameInput = new TextField();
        usernameInput.setPromptText("username");
        usernameInput.setEditable(true);
        usernameInput.setPrefWidth(200);

        passwordInput = new TextField();
        passwordInput.setPromptText("password");
        passwordInput.setEditable(true);
        passwordInput.setPrefWidth(200);

        walletAmountInput = new TextField();
        walletAmountInput.setPromptText("wallet amount");
        walletAmountInput.setEditable(true);
        walletAmountInput.setPrefWidth(100);

        userTypeInput = new ComboBox<>();
        userTypeInput.setPromptText("ADMIN/USER");
        userTypeInput.getItems().addAll("ADMIN_USER", "REGULAR_USER");

        userTableView = new TableView<>();
        User user = new User(-1, "roxi", "1234", 233, UserType.ADMIN_USER);
        TableGenerator<User> userTableGenerator = new TableGenerator<>();
        userTableView = userTableGenerator.generateTable(user);
        userTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        HBox hBox2 = new HBox(10);
        hBox2.getChildren().addAll(idUserInput, usernameInput, passwordInput, walletAmountInput, userTypeInput);

        addUserButton = new Button("Add");
        editUserButton = new Button("Edit");
        deleteUserButton = new Button("Delete");
        deleteUserButton.setDisable(true);

        HBox hBox3 = new HBox(10);
        hBox3.getChildren().addAll(addUserButton, editUserButton, deleteUserButton);

        Label selectDatesLabel = new Label("Cookies bought in the last X days:");
        GridPane.setConstraints(selectDatesLabel, 0, 0);
        noDaysInput = new TextField();
        noDaysInput.setEditable(true);
        GridPane.setConstraints(noDaysInput, 1, 0);
        Label selectTypeOfFile = new Label("Select the type of file you want to generate");
        GridPane.setConstraints(selectTypeOfFile, 0, 1);
        fileTypeCB = new ComboBox<>();
        fileTypeCB.setPromptText("PDF/TXT");
        fileTypeCB.getItems().addAll("PDF", "TXT");
        GridPane.setConstraints(fileTypeCB, 1, 1);
        Button generateFileButton = new Button("Generate File");
        GridPane.setConstraints(generateFileButton, 2, 2);

        gridPane.getChildren().addAll(selectDatesLabel, noDaysInput, selectTypeOfFile, fileTypeCB, generateFileButton);
        getChildren().addAll(cookieTableView, hBox, hBox1, userTableView, hBox2, hBox3, gridPane);

        new AdminController(this);
    }

    public Button getAddUserButton() {
        return addUserButton;
    }

    public Button getEditUserButton() {
        return editUserButton;
    }

    public Button getDeleteUserButton() {
        return deleteUserButton;
    }

    public Button getAddCookieButton() {
        return addCookieButton;
    }

    public Button getEditCookieButton() {
        return editCookieButton;
    }

    public Button getDeleteCookieButton() {
        return deleteCookieButton;
    }

    public TableView<User> getUserTableView() {
        return userTableView;
    }

    public TableView<Cookie> getCookieTableView() {
        return cookieTableView;
    }

    public TextField getIdCookieInput() {
        return idCookieInput;
    }

    public TextField getNameInput() {
        return nameInput;
    }

    public TextField getPriceInput() {
        return priceInput;
    }

    public TextField getQuantityOfSweeteners() {
        return quantityOfSweeteners;
    }

    public TextField getQuantityOnStock() {
        return quantityOnStock;
    }

    public TextField getIdUserInput() {
        return idUserInput;
    }

    public TextField getUsernameInput() {
        return usernameInput;
    }

    public TextField getPasswordInput() {
        return passwordInput;
    }

    public TextField getWalletAmountInput() {
        return walletAmountInput;
    }

    public TextField getNoDaysInput() {
        return noDaysInput;
    }

    public ComboBox<String> getUserTypeInput() {
        return userTypeInput;
    }

    public ComboBox<String> getFileTypeCB() {
        return fileTypeCB;
    }

    public ComboBox<String> getTypeInputCB() {
        return typeInputCB;
    }
}
