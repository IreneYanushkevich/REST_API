package com.irinayanushkevich.restapi.service;

import com.irinayanushkevich.restapi.model.Event;
import com.irinayanushkevich.restapi.repository.EventRepository;
import com.irinayanushkevich.restapi.repository.FileRepository;
import com.irinayanushkevich.restapi.repository.UserRepository;
import com.irinayanushkevich.restapi.repository.hib_rep_impl.HibEventRepositoryImpl;
import com.irinayanushkevich.restapi.repository.hib_rep_impl.HibFileRepositoryImpl;
import com.irinayanushkevich.restapi.repository.hib_rep_impl.HibUserRepositoryImpl;

import java.util.List;

public class EventService {

    private final FileRepository fileRepository = new HibFileRepositoryImpl();
    private final UserRepository userRepository = new HibUserRepositoryImpl();
    private final EventRepository eventRepository = new HibEventRepositoryImpl();

    public Event save() {
        return null;
    }

    public Event getById(Integer id) {
        return eventRepository.getById(id);
    }

    public Event update() {
        return null;
    }

    public void delete(Integer id) {
        eventRepository.delete(id);
    }

    public List<Event> getAll() {
        return eventRepository.getAll();
    }
}
