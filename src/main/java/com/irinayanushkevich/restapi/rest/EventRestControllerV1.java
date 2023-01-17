package com.irinayanushkevich.restapi.rest;

import com.irinayanushkevich.restapi.model.Event;
import com.irinayanushkevich.restapi.repository.EventRepository;
import com.irinayanushkevich.restapi.repository.FileRepository;
import com.irinayanushkevich.restapi.repository.UserRepository;
import com.irinayanushkevich.restapi.repository.hib_rep_impl.HibEventRepositoryImpl;
import com.irinayanushkevich.restapi.repository.hib_rep_impl.HibFileRepositoryImpl;
import com.irinayanushkevich.restapi.repository.hib_rep_impl.HibUserRepositoryImpl;
import com.irinayanushkevich.restapi.service.EventService;
import com.irinayanushkevich.restapi.util.GsonUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EventRestControllerV1 extends HttpServlet {

    private final EventRepository eventRep = new HibEventRepositoryImpl();
    private final UserRepository userRep = new HibUserRepositoryImpl();
    private final FileRepository fileREp = new HibFileRepositoryImpl();

    private final EventService eventService = new EventService();

    @Override
    //TODO: use paths localhost:8080/api/v1/events/1
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        //TODO: parse request URL request.getRequestURL();
        int id = Integer.parseInt(request.getParameter("id"));
        if (id == 0) {
            List<Event> events = eventService.getAll();
            GsonUtil.writeToJson(response, events);
        } else {
            Event event = eventService.getById(id);
            GsonUtil.writeToJson(response, event);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        //TODO: get all data from the file request.getReader().lines();

        int userId = Integer.parseInt(request.getParameter("user_id"));
        int fileId = Integer.parseInt(request.getParameter("file_id"));
        GsonUtil.writeToJson(response, eventService.save());
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        int eventId = Integer.parseInt(request.getParameter("id"));
        int userId = Integer.parseInt(request.getParameter("user_id"));
        int fileId = Integer.parseInt(request.getParameter("file_id"));
        GsonUtil.writeToJson(response, eventRep.update(new Event(eventId, userRep.getById(userId), fileREp.getById(fileId))));
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        eventService.delete(id);
        GsonUtil.writeToJson(response, "Event with id=" + id + " was deleted");
    }
}