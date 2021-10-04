package web.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private static long id;

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Transactional
    @Override
    public void edit(Long id, User user) {
        User user1 = entityManager.find(User.class, id);
        user1.setAge(user.getAge());
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setSurname(user.getSurname());
    }

    @Transactional
    @Override
    public List<User> listUsers() {
        List<User> userList = entityManager.createNativeQuery("select * from users", User.class).getResultList();
        return userList;
    }

    @Transactional
    @Override
    public User getUser(Long id) {
        return listUsers().stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }


}
