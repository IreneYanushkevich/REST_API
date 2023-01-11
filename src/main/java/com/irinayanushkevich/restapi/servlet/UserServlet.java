package com.irinayanushkevich.restapi.servlet;

import com.irinayanushkevich.restapi.model.User;
import com.irinayanushkevich.restapi.repository.UserRepository;
import com.irinayanushkevich.restapi.repository.hib_rep_impl.HibUserRepositoryImpl;
import com.irinayanushkevich.restapi.util.GsonUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    private final UserRepository userRep = new HibUserRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GsonUtil gsonUtil = new GsonUtil(response);
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        if (id == 0) {
            gsonUtil.writeToJson(userRep.getAll());
        } else {
            gsonUtil.writeToJson(userRep.getById(id));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GsonUtil gsonUtil = new GsonUtil(response);
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("user_name");
        gsonUtil.writeToJson(userRep.create(new User(id, name)));
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GsonUtil gsonUtil = new GsonUtil(response);
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("user_name");
        gsonUtil.writeToJson(userRep.update(new User(id, name)));
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GsonUtil gsonUtil = new GsonUtil(response);
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        userRep.delete(id);
        gsonUtil.writeToJson("User with id=" + id + " was deleted");
    }
}
