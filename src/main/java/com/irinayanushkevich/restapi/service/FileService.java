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

import java.util.List;

public class FileService {

    private final FileRepository fileRepository = new HibFileRepositoryImpl();
    private final UserRepository userRepository = new HibUserRepositoryImpl();
    private final EventRepository eventRepository = new HibEventRepositoryImpl();

    public File save(java.io.File file, Integer userId) {
        File fileForSave = new File();
        fileForSave.setFilePath(file.getPath());
        fileForSave.setName(file.getName());
        File createdFile = fileRepository.create(fileForSave);

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

    public File update(java.io.File file, Integer fileId) {
        File fileForEdit = new File();
        fileForEdit.setId(fileId);
        fileForEdit.setFilePath(file.getPath());
        fileForEdit.setName(file.getName());

        return fileRepository.update(fileForEdit);
    }

    public void delete(Integer id) {
        fileRepository.delete(id);
    }

    public List<File> getAll() {
        return fileRepository.getAll();
    }
}
