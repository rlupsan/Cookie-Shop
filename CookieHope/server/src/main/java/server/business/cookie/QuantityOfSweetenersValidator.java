package server.business.cookie;

import api.model.Cookie;

public class QuantityOfSweetenersValidator {
    public void validate(Cookie cookie) {
        if (cookie.getQuantityOfSweeteners() < 0) {
            throw new IllegalArgumentException("The quantity of sweeteners cannot be negative!");
        }
    }
}
