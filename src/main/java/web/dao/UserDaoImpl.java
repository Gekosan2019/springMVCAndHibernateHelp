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

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public void edit(Long id, User user) {
        User user1 = entityManager.find(User.class, id);
        user1.setUsername(user.getUsername());
        user1.setAge(user.getAge());
        user1.setEmail(user.getEmail());
        user1.setRoles(user.getRoles());
        user1.setSurname(user.getSurname());
    }

    @Override
    public List<User> listUsers() {
        List<User> userList = entityManager.createNativeQuery("SELECT * FROM users", User.class).getResultList();
        return userList;
    }

    @Override
    public User getUserByID(Long id) {
        return listUsers().stream().filter(user -> user.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public User getUserByUsername(String username) {
        return listUsers().stream().filter(user -> user.getUsername().equals(username)).findAny().orElse(null);
    }
}
