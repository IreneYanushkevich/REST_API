package com.irinayanushkevich.restapi.util;

import com.google.gson.Gson;
import com.irinayanushkevich.restapi.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GsonUtil {
    private static final Gson GSON = new Gson();

    public static User mapJsonToUser(HttpServletRequest request) {
        //TODO: test and fix if required
        return GSON.fromJson("", User.class);
    }

    public static void writeToJson(HttpServletResponse response, Object object) throws IOException {
        response.setContentType("text/HTML; charset=UTF-8");
        response.getWriter().write(GSON.toJson(object));
    }
}
