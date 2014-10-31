package net.therap.todo.service;

import net.therap.todo.domain.Task;
import net.therap.todo.domain.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 9/1/13
 * Time: 10:55 AM
 * To change this template use File | Settings | File Templates.
 */
public interface UserService {
    User getUserByEmailId(String emailId);
    List<Task> getTaskListByUser(User user);
    Boolean addOrUpdateUser(User user);
    Boolean removeUser(User user);
}
