package web.service;


import web.model.User;

import java.util.List;


public interface UserService {

    void add(User user);
    void delete(User user);
    void edit(User user);
    List<User> listUsers();

}
