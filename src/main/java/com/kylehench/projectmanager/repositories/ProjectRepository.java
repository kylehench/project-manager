package com.kylehench.projectmanager.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kylehench.projectmanager.models.Project;
import com.kylehench.projectmanager.models.User;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
	
	// this method retrieves all the projects from the database
    List<Project> findAll();
    
    // List of projects that user is not in
    List<Project> findByTeamNotContains(User user);
    
    // List of projects that user is in
    List<Project> findByTeamContains(User user);
}