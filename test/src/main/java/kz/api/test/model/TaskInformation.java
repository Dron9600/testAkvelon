package kz.api.test.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.stream.Stream;
import javax.persistence.*;

@Embeddable
public class TaskInformation {

//    @GeneratedValue(strategy = GenerationType.AUTO)
    String taskName;
    private TaskStatus taskStatus;    
    String taskDescription;
    int taskPriority;
    

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
    
    
    public TaskInformation() {
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String description) {
        this.taskDescription = description;
    }

    public int getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(int priority) {
        this.taskPriority = priority;
    }
    
    public enum TaskStatus {

	TYPE1("ToDo"),
	TYPE2("InProgress"),
	TYPE3("Done");
	
	private String code;
	
	private TaskStatus(String code) {
		this.code=code;
	}
	
	@JsonCreator
	public static TaskStatus decode(final String code) {
		return Stream.of(TaskStatus.values()).filter(targetEnum -> targetEnum.code.equals(code)).findFirst().orElse(null);
	}
	
	@JsonValue
	public String getCode() {
		return code;
	}
    }
    
}


