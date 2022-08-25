package com.kylehench.projectmanager.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.kylehench.projectmanager.models.Project;
import com.kylehench.projectmanager.models.Task;
import com.kylehench.projectmanager.models.User;
import com.kylehench.projectmanager.services.ProjectService;
import com.kylehench.projectmanager.services.TaskService;
import com.kylehench.projectmanager.services.UserService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    
    // view projects
    @GetMapping
    public String viewAll(HttpSession session, Model model) {
    	if (session.getAttribute("userId")==null) return "redirect:/logout";
    	User user = userService.read((long) session.getAttribute("userId"));
    	model.addAttribute("user", user);
    	// add projects user is not in to model
    	List<Project> projectsNotIn = projectService.notInProject(user);
    	model.addAttribute("projectsNotIn", projectsNotIn);
    	// add projects user is in to model
    	List<Project> projectsIn = projectService.inProject(user);
    	model.addAttribute("projectsIn", projectsIn);
    	return "dashboard.jsp";
    }
    
    // view project
    @GetMapping("/{id}")
    public String view(@PathVariable("id") long id, HttpSession session, Model model) {
    	if (session.getAttribute("userId")==null) return "redirect:/logout";
    	model.addAttribute("project", projectService.read(id));
    	return "projects_id.jsp";
    }
    
    // view tasks
    @GetMapping("/{id}/tasks")
    public String viewTasks(@PathVariable("id") long id, HttpSession session, Model model,
    		@ModelAttribute("newTask") Task newTask) {
    	if (session.getAttribute("userId")==null) return "redirect:/logout";
    	model.addAttribute("project", projectService.read(id));
    	return "projects_id_tasks.jsp";
    }
    
    // new task
    @PostMapping("/{id}/tasks/create")
    public String newTask(@PathVariable("id") long project_id, HttpSession session, Model model,
    		@Valid @ModelAttribute("newTask") Task newTask, BindingResult result) {
    	if (session.getAttribute("userId")==null) return "redirect:/logout";
    	if (result.hasErrors()) {
    		model.addAttribute("project", projectService.read(project_id));
    		return "projects_id_tasks.jsp";
    	}
    	newTask.setProject(projectService.read(project_id));
    	newTask.setUser(userService.read((long) session.getAttribute("userId")));
    	taskService.create(newTask);
    	return "redirect:/projects/{id}/tasks";
    }
    
    // user joins project
    @GetMapping("/join/{project_id}")
    public String userJoins(@PathVariable("project_id") long project_id,
    		HttpSession session) {
    	if (session.getAttribute("userId")==null) return "redirect:/logout";
    	User user = userService.read((long) session.getAttribute("userId"));
    	List<Project> projects = user.getProjects();
    	projects.add(projectService.read(project_id));
    	user.setProjects(projects);
    	userService.update(user);
    	return "redirect:/projects";
    }
    
    // user leaves project
    @GetMapping("/leave/{project_id}")
    public String userLeaves(@PathVariable("project_id") long project_id,
    		HttpSession session) {
    	if (session.getAttribute("userId")==null) return "redirect:/logout";
    	User user = userService.read((long) session.getAttribute("userId"));
    	user.getProjects().remove(projectService.read(project_id));
    	user.setProjects(user.getProjects());
    	userService.update(user);
    	return "redirect:/projects";
    }
    
    // new project
    @GetMapping("/new")
    public String nouveau(@ModelAttribute("newProject") Project newProject, HttpSession session) {
    	if (session.getAttribute("userId")==null) return "redirect:/logout";
    	return "projects_new.jsp";
    }
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("newProject") Project newProject, BindingResult result,
    		HttpSession session) {
    	if (session.getAttribute("userId")==null) return "redirect:/logout";
    	if (result.hasErrors()) return "projects_new.jsp";
    	Date now = new Date();
    	if (now.compareTo(newProject.getDueDate())==1) {
    		result.rejectValue("dueDate", "in past", "Please choose a future date");
    	}
    	if (result.hasErrors()) return "projects_new.jsp";
    	User lead = userService.read((long) session.getAttribute("userId"));
    	newProject.setLead(lead);
    	List<User> team = new ArrayList<User>();
    	team.add(lead);
    	newProject.setTeam(team);
    	projectService.create(newProject);
    	return "redirect:/projects";
    }
    
    // edit project
    @GetMapping("/edit/{id}")
    public String edit(HttpSession session, Model model, @PathVariable("id") long id) {
    	if (session.getAttribute("userId")==null) return "redirect:/logout";
    	Project project = projectService.read(id);
    	// verify current user is lead
    	if ((long) session.getAttribute("userId")!=project.getLead().getId()) return "redirect:/logout";
    	model.addAttribute("project", project);
    	model.addAttribute("lead_id", project.getLead().getId());
    	return "projects_edit.jsp";
    }
    @PutMapping("/update")
    public String update(@Valid @ModelAttribute("project") Project project, BindingResult result,
    		HttpSession session, Model model) {
    	// verify that the lead id of the requested project to update == session userId
    	Project oldProject = projectService.read(project.getId());
    	if (session.getAttribute("userId")==null || oldProject.getLead().getId() != (long) session.getAttribute("userId")) return "redirect:/logout";
    	project.setLead(oldProject.getLead());
    	project.setTeam(oldProject.getTeam());
    	// add project (with lead added) to model in case validation fails
    	model.addAttribute("project", project);
    	if (result.hasErrors()) return "projects_edit.jsp";
    	Date now = new Date();
    	if (now.compareTo(project.getDueDate())==1) result.rejectValue("dueDate", "in past", "Please choose a future date");
    	if (result.hasErrors()) return "projects_edit.jsp";
    	projectService.update(project);
    	return "redirect:/projects";
    }
}