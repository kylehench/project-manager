package com.kylehench.projectmanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kylehench.projectmanager.models.Project;
import com.kylehench.projectmanager.models.User;
import com.kylehench.projectmanager.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository repository;
	
	// create
    public Project create(Project newProject) {
    	return repository.save(newProject);
    }
    
    // read
    public Project read(Long id) {
        Optional<Project> optional = repository.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }
    // read all
    public List<Project> readAll() {
        return repository.findAll();
    }
    
    // read projects that that user is not in
    public List<Project> notInProject(User user) {
    	return repository.findByTeamNotContains(user);
    }
    
    // read projects that that user is in
    public List<Project> inProject(User user) {
    	return repository.findByTeamContains(user);
    }
    
    // update
    public Project update(Project o) {
    	return repository.save(o);    	
    }
    // delete
	public void delete(Long id) {
		Optional<Project> optional = repository.findById(id);
		if (optional.isPresent()) {
			repository.deleteById(id);
		}
	}
}
