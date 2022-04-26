package kz.api.test.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class ProjectRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;


    @Embedded
    private ProjectInformation  projectInformation;
    @Embedded
    private TaskInformation taskInformation;

    public ProjectRecord() {
    }

    public ProjectInformation getProjectInformation() {
        return projectInformation;
    }

    public void setInformation(ProjectInformation projectInformation) {
        this.projectInformation = projectInformation;
    }

    public TaskInformation getTaskInformation() {
        return taskInformation;
    }

    public void setTaskInformation(TaskInformation taskInformation) {
        this.taskInformation = taskInformation;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
        public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}