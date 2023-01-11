package com.irinayanushkevich.restapi.servlet;

import com.irinayanushkevich.restapi.model.Event;
import com.irinayanushkevich.restapi.repository.EventRepository;
import com.irinayanushkevich.restapi.repository.FileRepository;
import com.irinayanushkevich.restapi.repository.UserRepository;
import com.irinayanushkevich.restapi.repository.hib_rep_impl.HibEventRepositoryImpl;
import com.irinayanushkevich.restapi.repository.hib_rep_impl.HibFileRepositoryImpl;
import com.irinayanushkevich.restapi.repository.hib_rep_impl.HibUserRepositoryImpl;
import com.irinayanushkevich.restapi.util.GsonUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EventServlet extends HttpServlet {

    private final EventRepository eventRep = new HibEventRepositoryImpl();
    private final UserRepository userRep = new HibUserRepositoryImpl();
    private final FileRepository fileREp = new HibFileRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GsonUtil gsonUtil = new GsonUtil(response);
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        if (id == 0) {
            gsonUtil.writeToJson(eventRep.getAll());
        } else {
            gsonUtil.writeToJson(eventRep.getById(id));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GsonUtil gsonUtil = new GsonUtil(response);
        request.setCharacterEncoding("UTF-8");
        int eventId = Integer.parseInt(request.getParameter("id"));
        int userId = Integer.parseInt(request.getParameter("user_id"));
        int fileId = Integer.parseInt(request.getParameter("file_id"));
        gsonUtil.writeToJson(eventRep.create(new Event(eventId, userRep.getById(userId), fileREp.getById(fileId))));
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GsonUtil gsonUtil = new GsonUtil(response);
        request.setCharacterEncoding("UTF-8");
        int eventId = Integer.parseInt(request.getParameter("id"));
        int userId = Integer.parseInt(request.getParameter("user_id"));
        int fileId = Integer.parseInt(request.getParameter("file_id"));
        gsonUtil.writeToJson(eventRep.update(new Event(eventId, userRep.getById(userId), fileREp.getById(fileId))));
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GsonUtil gsonUtil = new GsonUtil(response);
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        eventRep.delete(id);
        gsonUtil.writeToJson("Event with id=" + id + " was deleted");
    }
}