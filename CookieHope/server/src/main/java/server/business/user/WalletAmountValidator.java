package server.business.user;


import api.model.User;
import server.business.Validator;

public class WalletAmountValidator implements Validator<User> {
    public void validate(User user) {
        if (user.getWalletAmount() < 0) {
            throw new IllegalArgumentException("The wallet amount cannot be negative!");
        }
    }
}

