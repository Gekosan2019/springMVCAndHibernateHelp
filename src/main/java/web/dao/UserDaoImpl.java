package web.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public void delete(String  username) {
        entityManager.remove(entityManager.find(User.class, username));
    }

    @Transactional
    @Override
    public void edit(String username, User user) {
        User user1 = entityManager.find(User.class, username);
        user1.setUsername(user.getUsername());
        user1.setAge(user.getAge());
        user1.setEmail(user.getEmail());
        user1.setRoles(user.getRoles());
        user1.setSurname(user.getSurname());
    }

    @Transactional
    @Override
    public List<User> listUsers() {
        List<User> userList = entityManager.createNativeQuery("SELECT * FROM users", User.class).getResultList();
        return userList;
    }

    @Transactional
    @Override
    public User getUser(String username) {
        return listUsers().stream().filter(user -> user.getUsername().equals(username)).findAny().orElse(null);
    }



}
