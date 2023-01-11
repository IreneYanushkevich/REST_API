package com.irinayanushkevich.restapi.util;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GsonUtil {
    private final HttpServletResponse response;

    public GsonUtil(HttpServletResponse response) {
        this.response = response;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void writeToJson(Object object) throws IOException {
        response.setContentType("text/HTML; charset=UTF-8");
        response.getWriter().write(new Gson().toJson(object));
    }
}
