package kz.api.test.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class ProjectRecord
{
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;

    @Embedded
    private List<Description> description;


    public ProjectRecord() {
    }

    public List<Description> getDescription() {
        return description;
    }

    public void setDescription(List<Description> description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}