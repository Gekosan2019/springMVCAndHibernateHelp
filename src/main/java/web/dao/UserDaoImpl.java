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

    @Override
    @Transactional
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void edit(User user) {

    }

    @Transactional
    @Override
    public List<User> listUsers() {
        List<User> userList = entityManager.createNativeQuery("select * from users", User.class).getResultList();
        return userList;
    }
}
