package com.irinayanushkevich.restapi.service;

import com.irinayanushkevich.restapi.model.Event;
import com.irinayanushkevich.restapi.repository.EventRepository;
import com.irinayanushkevich.restapi.repository.hib_rep_impl.HibEventRepositoryImpl;

import java.util.List;

public class EventService {

    private final EventRepository eventRepository = new HibEventRepositoryImpl();

    public Event getById(Integer id) {
        return eventRepository.getById(id);
    }

    public void delete(Integer id) {
        eventRepository.delete(id);
    }

    public List<Event> getAll() {
        return eventRepository.getAll();
    }
}
