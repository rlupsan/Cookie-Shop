package client.view;


import api.model.Cookie;
import api.model.CookieType;
import api.model.User;
import client.controller.UserController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserView extends VBox {
    private final Button commentButton;
    private final Button newOrderButton;
    private final Button addToOrderButton;
    private final TextField quantityInput;
    private TableView<Cookie> cookieTableView;
    private Button addFav;
    private Button finisOrderButton;

    public UserView(User user) {
        setSpacing(8);
        cookieTableView = new TableView<>();
        Cookie cookie = new Cookie(-1, "Cookie1", CookieType.APRICOT_PISTACHIO, 23, 50, 10);
        TableGenerator<Cookie> cookieTableGenerator = new TableGenerator<>();
        cookieTableView = cookieTableGenerator.generateTable(cookie);
        cookieTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        cookieTableView.setMaxWidth(900);
        cookieTableView.setMaxHeight(200);

        Label label2 = new Label("Quantity: ");
        quantityInput = new TextField();
        quantityInput.setEditable(true);

        HBox hBox2 = new HBox(10);
        hBox2.getChildren().addAll(label2, quantityInput);

        HBox orderHBox = new HBox(10);

        finisOrderButton = new Button("Finish Order");
        newOrderButton = new Button("New Order");
        addToOrderButton = new Button("Add to Order");
        orderHBox.getChildren().addAll(newOrderButton, addToOrderButton, finisOrderButton);

        addFav = new Button("Add to Favourites");
        commentButton = new Button("Comment");
        getChildren().addAll(cookieTableView, hBox2, orderHBox, addFav, commentButton);

        new UserController(this, user);

    }

    public TableView<Cookie> getCookieTableView() {
        return cookieTableView;
    }

    public Button getAddFav() {
        return addFav;
    }

    public Button getFinisOrderButton() {
        return finisOrderButton;
    }

    public Button getNewOrderButton() {
        return newOrderButton;
    }

    public Button getAddToOrderButton() {
        return addToOrderButton;
    }

    public TextField getQuantityInput() {
        return quantityInput;
    }

    public Button getCommentButton() {
        return commentButton;
    }
}
