package server.business.cookie;


import api.model.Cookie;

public class PriceValidator {
    public void validate(Cookie cookie) {
        if (cookie.getPrice() < 0) {
            throw new IllegalArgumentException("The price cannot be negative!");
        }
    }
}
