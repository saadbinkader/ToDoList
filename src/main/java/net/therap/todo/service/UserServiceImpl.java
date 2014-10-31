package net.therap.todo.service;

import net.therap.todo.dao.UserDao;
import net.therap.todo.domain.Task;
import net.therap.todo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sk.saad
 * Date: 9/1/13
 * Time: 10:58 AM
 * To change this template use File | Settings | File Templates.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserByEmailId(String emailId) {
        User user = new User() ;
        user.setEmailID(emailId);

        String queryParameter = "SELECT c FROM User c" +
                                " WHERE c.emailID = '" + emailId + "'" ;
        TypedQuery query = (TypedQuery) entityManager.createQuery(queryParameter,User.class);
        user = (User) query.getSingleResult();
        return user;
    }

    @Override
    public List<Task> getTaskListByUser(User user) {
        user = getUserByEmailId(user.getEmailID());
        return user.getTaskList();
    }

    @Override
    @Transactional
    public Boolean addOrUpdateUser(User user) {
        try {
            userDao.save(user);
            return true ;
        } catch ( RuntimeException exception) {
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean removeUser(User user) {
        try {
            userDao.delete(user);
            return true ;
        } catch ( RuntimeException exception) {
            return false;
        }
    }
}
