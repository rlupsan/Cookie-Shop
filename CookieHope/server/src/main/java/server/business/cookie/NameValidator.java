package server.business.cookie;


import api.model.Cookie;

import java.util.regex.Pattern;

public class NameValidator {
    private static final String nameRegex = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";

    /**
     * source: https://www.regextester.com/93648
     * applies to all possible names
     */
    public void validate(Cookie cookie) {
        Pattern pattern = Pattern.compile(nameRegex);
        if (!pattern.matcher(cookie.getName()).matches()) {
            throw new IllegalArgumentException("Name is not well written!");
        }
    }
}
