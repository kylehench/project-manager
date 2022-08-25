package com.kylehench.projectmanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kylehench.projectmanager.models.Task;
import com.kylehench.projectmanager.repositories.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository repository;
	
	// create
    public Task create(Task newTask) {
    	newTask.setId(null);
    	return repository.save(newTask);
    }
    
    // read
    public Task read(Long id) {
        Optional<Task> optional = repository.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }
    // read all
    public List<Task> readAll() {
        return repository.findAll();
    }
    
    // update
    public Task update(Task o) {
    	return repository.save(o);    	
    }
    // delete
	public void delete(Long id) {
		Optional<Task> optional = repository.findById(id);
		if (optional.isPresent()) {
			repository.deleteById(id);
		}
	}
}
