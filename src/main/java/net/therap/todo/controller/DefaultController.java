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

@Controller
@SessionAttributes({"user"})
public class DefaultController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listUsers(@ModelAttribute("user") User user,ModelMap model) {
        model.addAttribute("task", new Task());
        List<Task> userList =  userService.getTaskListByUser(user) ;
        model.addAttribute("taskList",userList);
        return "common/index";
    }

    @ModelAttribute("user")
    public User populateDefaultUser(ModelMap model) {
        User user = new User() ;
        user.setEmailID("Default");
        user.setPassword("Default");
        user.setUserName("Default");
        model.addAttribute("user", user );
        userService.addOrUpdateUser(user);
        return user;
    }
}