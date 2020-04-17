package client.view;

import api.model.Cookie;
import api.model.User;
import client.controller.CommentController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class CommentView extends VBox {

    private VBox commentContainer;
    private TextArea textInput;
    private Button postComment;

    public CommentView(Cookie cookie, User user) {
        init();
        new CommentController(this, cookie, user);
    }

    private void init() {
        commentContainer = new VBox(10);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(900, 450);
        scrollPane.setContent(commentContainer);
        getChildren().addAll(scrollPane, new Separator());

        textInput = new TextArea();
        textInput.setWrapText(true);
        textInput.setPrefWidth(850);
        textInput.setPrefHeight(150);
        postComment = new Button("Post Comment");
        postComment.setPadding(new Insets(5));
        getChildren().addAll(textInput, postComment);
    }

    public VBox getCommentContainer() {
        return commentContainer;
    }

    public TextArea getTextInput() {
        return textInput;
    }

    public Button getPostComment() {
        return postComment;
    }
}
