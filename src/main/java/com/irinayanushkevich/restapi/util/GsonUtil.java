package com.irinayanushkevich.restapi.util;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GsonUtil {
    private static final Gson GSON = new Gson();

    public static void writeToJson(HttpServletResponse response, Object object) throws IOException {
        response.setContentType("text/HTML; charset=UTF-8");
        response.getWriter().write(GSON.toJson(object));
    }
}
