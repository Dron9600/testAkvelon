package kz.api.test.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

//@Entity
@Embeddable
public class Description {

    String name10;
    String status;
    String descript;
    String priority;

    public Description() {
    }

    public String getName10() {
        return name10;
    }

    public void setName10(String name10) {
        this.name10 = name10;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
