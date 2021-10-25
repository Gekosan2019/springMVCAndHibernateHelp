package web.dao;


import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.persist(user);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public void edit(User user) {
        User user1 = entityManager.find(User.class, user.getId());
        user.setRoles(user1.getRoles());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.detach(user1);
        Session session = entityManager.unwrap(Session.class);
        session.update(user);
    }

    @Override
    public List<User> listUsers() {
        List<User> userList = entityManager.createNativeQuery("SELECT * FROM users", User.class).getResultList();
        return userList;
    }

    @Override
    public User getUserByID(Long id) {
        User user = entityManager.createQuery("SELECT r FROM User r WHERE r.id = :id", User.class)
                .setParameter("id", id).getSingleResult();
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = entityManager.createQuery("SELECT r FROM User r WHERE r.username = :username", User.class)
                .setParameter("username", username).getSingleResult();
        return user;
    }
}
