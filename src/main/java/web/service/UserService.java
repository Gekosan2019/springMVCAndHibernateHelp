package web.service;


import web.model.User;

import java.util.List;


public interface UserService {

    void add(User user);
    void delete(String username);
    void edit(String username, User user);
    List<User> listUsers();
    User getUser(String username);
}
