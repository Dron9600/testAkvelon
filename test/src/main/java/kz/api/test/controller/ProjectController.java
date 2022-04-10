package kz.api.test.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import kz.api.test.database.PostgreDb;
import kz.api.test.model.ProjectRecord;
import kz.api.test.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import java.util.List;


@RestController
public class ProjectController {
    PostgreDb postgreDb = new PostgreDb();

    @Autowired
    private ProjectService projectService;

    @GetMapping("/")
    public List<ProjectRecord> getAllProjects(){
        return projectService.getAllProjects();
    }
//    @RequestMapping("/")
//    public List<ProjectRecord> getAllProjects(){
//        return projectService.getAllProjects();
//    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public void addProject(@RequestBody List<ProjectRecord> projectRecord){
        projectService.addProject(projectRecord);
//        postgreDb.addToDb(projectRecord);
    }

//    @RequestMapping(value="/add", method=RequestMethod.POST)
//    public void addProject(@RequestBody String strrf){
//        System.out.println(strrf);
////        postgreDb.addToDb(projectRecord);
//    }







//    @DeleteMapping("/delete/{projectid}")
//    private void deleteProject(@PathVariable("projectid") String projectid){
//        projectService.deleteProject(projectid);
//        postgreDb.deleteFromDb(projectid);
//    }
//
//    @GetMapping("/{projectid}")
//    private ProjectRecord getproject(@PathVariable("projectid") String projectid){
//        return projectService.getProjectById(projectid);
//    }
//
////    @PostMapping("/")
////    private String saveProject(@RequestBody ProjectRecord projectRecord){
////        projectService.saveOrUpdate(projectRecord);
////        return projectRecord.getId();
////    }
//
//    @PutMapping("/")
//    private ProjectRecord update(@RequestBody ProjectRecord projectRecord){
//        projectService.saveOrUpdate(projectRecord);
//        postgreDb.update(projectRecord);
//        return projectRecord;
//    }
}