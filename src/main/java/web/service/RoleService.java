package web.service;

import web.model.Role;

import java.util.List;

public interface RoleService {
    Role getRoleByName(String username);
    void saveRole(Role role);
    List<Role> getRoles();
    Role getRoleById(Long id);
}
