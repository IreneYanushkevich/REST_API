package com.irinayanushkevich.restapi.rest;

import com.irinayanushkevich.restapi.model.Event;
import com.irinayanushkevich.restapi.service.EventService;
import com.irinayanushkevich.restapi.util.GsonUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class EventRestControllerV1 extends HttpServlet {

    private final EventService eventService = new EventService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        int id = eventIdMapping(request);
        if (id == 0) {
            List<Event> events = eventService.getAll();
            GsonUtil.writeToJson(response, events);
        } else {
            Event event = eventService.getById(id);
            GsonUtil.writeToJson(response, event);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = eventIdMapping(request);
        eventService.delete(id);
        GsonUtil.writeToJson(response, "Event with id=" + id + " was deleted");
    }

    private Integer eventIdMapping(HttpServletRequest request) throws UnsupportedEncodingException {
        String urlPattern = "/api/v1/events/";
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