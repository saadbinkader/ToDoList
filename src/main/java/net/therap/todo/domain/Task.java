package net.therap.todo.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: saad
 * Date: 8/31/13
 * Time: 11:02 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class Task {
    private String taskID;
    private String taskTitle;
    private Date taskTimeStamp;
    private User taskOwner;

    @Id
    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Date getTaskTimeStamp() {
        return taskTimeStamp;
    }

    public void setTaskTimeStamp(Date taskTimeStamp) {
        this.taskTimeStamp = taskTimeStamp;
    }

    @ManyToOne
    @JoinTable(name = "TASK_OF_USER",
            joinColumns = {@JoinColumn(name = "taskID")},
            inverseJoinColumns = {@JoinColumn(name = "emailID")}
    )
    public User getTaskOwner() {
        return taskOwner;
    }

    public void setTaskOwner(User taskOwner) {
        this.taskOwner = taskOwner;
    }
}
