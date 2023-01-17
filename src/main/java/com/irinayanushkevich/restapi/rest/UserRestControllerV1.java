package com.irinayanushkevich.restapi.rest;

import com.irinayanushkevich.restapi.model.User;
import com.irinayanushkevich.restapi.service.UserService;
import com.irinayanushkevich.restapi.util.GsonUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserRestControllerV1 extends HttpServlet {
    private final String urlPattern = "/api/v1/users";

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        String url = request.getRequestURL().toString();
        String id = url.substring(url.indexOf(urlPattern) + urlPattern.length());
        if (id.isBlank()) {
            List<User> users = userService.getAll();
            GsonUtil.writeToJson(response, users);
        } else {
            User user = userService.getById(Integer.parseInt(id));
            GsonUtil.writeToJson(response, user);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("user_name");
        User user = userService.save(name);
        GsonUtil.writeToJson(response, user);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String url = request.getRequestURL().toString();
        String id = url.substring(url.indexOf(urlPattern) + urlPattern.length());
        String name = request.getParameter("user_name");
        User user = userService.update(Integer.parseInt(id), name);
        GsonUtil.writeToJson(response, user);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        userService.delete(id);
        GsonUtil.writeToJson(response, "User with id=" + id + " was deleted");
    }
}
