package net.therap.todo.controller;

import net.therap.todo.dao.UserDao;
import net.therap.todo.domain.Task;
import net.therap.todo.domain.User;
import net.therap.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 9/1/13
 * Time: 10:42 AM
 * To change this template use File | Settings | File Templates.
 */


@Controller
@SessionAttributes({"user"})

@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String listUsers(@ModelAttribute("user") User user,ModelMap model) {
        model.addAttribute("task", new Task());
        List<Task> userList =  userService.getTaskListByUser(user) ;
        model.addAttribute("taskList",userList);
        return "common/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addTask(@ModelAttribute("user") User user,@ModelAttribute("task") Task task, BindingResult result, ModelMap model) {
        List<Task> taskList =  user.getTaskList() ;
        taskList.add(task);

        task.setTaskID(user.generateTaskId());
        task.setTaskOwner(user);
        task.setTaskTimeStamp(new Date());
        userService.addOrUpdateUser(user);

        return "redirect:/";
    }

    @RequestMapping("/delete/{taskId}")
    public String deleteTask(@ModelAttribute("user") User user,@PathVariable("taskId") String taskId, ModelMap model) {

        user = userService.getUserByEmailId(user.getEmailID());
        List<Task> taskList = user.getTaskList() ;
        Task targetTask = null;
        for ( int i = 0 ; i < taskList.size() ; i++ )
            if ( taskList.get(i).getTaskID().equals(taskId) ) {
                targetTask = taskList.get(i) ;
                System.out.println("Found task");
                break;
            }
        user.getTaskList().remove(targetTask);
        model.addAttribute("user",user);
        userService.addOrUpdateUser(user);

        System.out.println("Wanted to delete tast " + targetTask.getTaskTitle());
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("user") User user, BindingResult result, ModelMap model) {
        User targetUser = userService.getUserByEmailId(user.getEmailID());
        if ( targetUser != null && targetUser.getPassword().equals(user.getPassword())) {
            user = targetUser;
            model.addAttribute("user",user);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/logout")
    public String logout(@ModelAttribute("user") User user, BindingResult result, ModelMap model) {
        user = userService.getUserByEmailId("Default");
        model.addAttribute("user",user);
        return "redirect:/";
    }

    @ModelAttribute("user")
    public User populateDefaultUser(ModelMap model) {
        User user = new User() ;
        user.setEmailID("Default");
        user.setPassword("Default");
        user.setUserName("Default");
        model.addAttribute("user", user );
        return user;
    }
}