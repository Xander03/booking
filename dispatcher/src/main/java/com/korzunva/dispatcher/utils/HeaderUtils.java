package com.korzunva.dispatcher.utils;

import com.korzunva.common.utils.Base64Encoder;
import com.korzunva.model.User;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HeaderUtils {

    private static final String TOKEN_HEADER = "Token";

    public static User getUser(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);
        if (token != null && !token.equals("")) {
            token = Base64Encoder.decode(token);
        } else {
            return new User();
        }

        try {
            return JsonParser.jsonToObject(User.class, token);
        } catch (IOException e) {
            return new User();
        }
    }

}
