package client.controller;

import api.model.Comment;
import api.model.Cookie;
import api.model.User;
import client.communication.util.CommunicationUtil;
import client.view.CommentView;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.util.Date;

public class CommentController {
    private final CommentView view;
    private Cookie cookie;
    private User user;

    public CommentController(CommentView commentView, Cookie cookie, User user) {
        this.view = commentView;
        this.cookie = cookie;
        this.user = user;
        refreshComments();
        view.getPostComment().setOnAction(e -> postComment());
    }

    private void postComment() {
        TextArea textArea = view.getTextInput();
        textArea.appendText(" - on "+ new Date().toString() + ", by " + user.getUsername());
        String commentText = textArea.getText();
        cookie = CommunicationUtil.postComment(commentText, cookie.getId(), user.getId());
        refreshComments();
        textArea.clear();
    }

    private void refreshComments() {
        VBox commentContainer = view.getCommentContainer();
        commentContainer.getChildren().clear();
        for (Comment comment : cookie.getComments()) {
            Label label = new Label();
            label.setText(comment.getText());
            label.setPadding(new Insets(10));
            commentContainer.getChildren().addAll(label, new Separator());
        }

        if (cookie.getComments().isEmpty()) {
            Label label = new Label();
            label.setText("No Comments");
            commentContainer.getChildren().add(label);
        }
    }
}
