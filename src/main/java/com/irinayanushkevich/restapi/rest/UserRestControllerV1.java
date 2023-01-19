package com.irinayanushkevich.restapi.rest;

import com.irinayanushkevich.restapi.model.User;
import com.irinayanushkevich.restapi.service.UserService;
import com.irinayanushkevich.restapi.util.GsonUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class UserRestControllerV1 extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = userIdMapping(request);
        if (id == 0) {
            List<User> users = userService.getAll();
            GsonUtil.writeToJson(response, users);
        } else {
            User user = userService.getById(id);
            GsonUtil.writeToJson(response, user);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getHeader("name");
        User user = userService.save(name);
        GsonUtil.writeToJson(response, user);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = userIdMapping(request);
        String name = request.getHeader("name");
        User user = userService.update(id, name);
        GsonUtil.writeToJson(response, user);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = userIdMapping(request);
        userService.delete(id);
        GsonUtil.writeToJson(response, "User with id=" + id + " was deleted");
    }

    private Integer userIdMapping(HttpServletRequest request) throws UnsupportedEncodingException {
        String urlPattern = "/api/v1/users/";
        request.setCharacterEncoding("UTF-8");
        String url = request.getRequestURL().toString();
        String id = url.substring(url.indexOf(urlPattern) + urlPattern.length());

        if (id.isBlank()) {
            return 0;
        } else {
            return Integer.parseInt(id);
        }
    }
}
