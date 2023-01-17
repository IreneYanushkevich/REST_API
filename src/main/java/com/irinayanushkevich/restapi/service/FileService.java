package com.irinayanushkevich.restapi.service;

import com.irinayanushkevich.restapi.model.Event;
import com.irinayanushkevich.restapi.model.File;
import com.irinayanushkevich.restapi.model.User;
import com.irinayanushkevich.restapi.repository.EventRepository;
import com.irinayanushkevich.restapi.repository.FileRepository;
import com.irinayanushkevich.restapi.repository.UserRepository;
import com.irinayanushkevich.restapi.repository.hib_rep_impl.HibEventRepositoryImpl;
import com.irinayanushkevich.restapi.repository.hib_rep_impl.HibFileRepositoryImpl;
import com.irinayanushkevich.restapi.repository.hib_rep_impl.HibUserRepositoryImpl;

import java.io.InputStream;
import java.util.List;

public class FileService {

    private final FileRepository fileRepository = new HibFileRepositoryImpl();
    private final UserRepository userRepository = new HibUserRepositoryImpl();
    private final EventRepository eventRepository = new HibEventRepositoryImpl();

    public File save(InputStream inputStream, Integer userId, String fileName) {
        File file = new File();
        file.setFilePath("");  ///????????????????????
        file.setName(fileName);
        File createdFile = fileRepository.create(file);

        User user = userRepository.getById(userId);

        Event event = new Event();
        event.setFile(createdFile);
        event.setUser(user);
        Event createdEvent = eventRepository.create(event);

        return createdFile;
    }

    public File getById(Integer id) {
        return fileRepository.getById(id);
    }

    public File update(InputStream inputStream, Integer id, String fileName) {
        File file = fileRepository.getById(id);
        file.setFilePath("");  ///????????????????????
        file.setName(fileName);
        return fileRepository.update(file);
    }

    public void delete(Integer id) {
        fileRepository.getById(id);
    }

    public List<File> getAll() {
        return fileRepository.getAll();
    }
}
