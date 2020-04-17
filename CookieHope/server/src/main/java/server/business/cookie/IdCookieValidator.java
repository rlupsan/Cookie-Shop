package server.business.cookie;


import api.model.Cookie;
import server.business.Validator;

public class IdCookieValidator implements Validator<Cookie> {
    public void validate(Cookie cookie) {
        if (cookie.getId() < 0) {
            throw new IllegalArgumentException("The id cannot be negative!");
        }
    }
}
