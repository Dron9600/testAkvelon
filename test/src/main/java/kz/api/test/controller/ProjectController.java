package kz.api.test.controller;

import kz.api.test.model.ProjectRecord;
import kz.api.test.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/")
    public List<ProjectRecord> getAllProjects(){
        return projectService.getAllProjects();
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public void addProject(@RequestBody ProjectRecord projectRecord){
        projectService.addProject(projectRecord);
    }


    @DeleteMapping("/delete/{projectid}")
    private void deleteProject(@PathVariable("projectid") long projectId){
        projectService.deleteProject(projectId);
    }

    @GetMapping("/{projectid}")
    private ProjectRecord getproject(@PathVariable("projectid") long projectId){
        return projectService.getProjectById(projectId);
    }

}