package server.business.cookie;


import api.model.Cookie;

public class QuantityOnStockValidator {
    public void validate(Cookie cookie) {
        if (cookie.getQuantityOnStock() < 0) {
            throw new IllegalArgumentException("The quantity on stock cannot be negative!");
        }
    }
}
