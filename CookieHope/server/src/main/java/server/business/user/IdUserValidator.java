package server.business.user;


import api.model.User;
import server.business.Validator;

public class IdUserValidator implements Validator<User> {
    public void validate(User user) {
        if (user.getId() < 0) {
            throw new IllegalArgumentException("The id cannot be negative!");
        }
    }
}
