package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserTestInit {

    private final RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    public UserTestInit(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        roleService.saveRole(new Role(1L, "ROLE_ADMIN"));
        roleService.saveRole(new Role(2L, "ROLE_USER"));
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleById(1L));
        User admin = new User("Shamil", "Shundalov", "super.shomka@mail.ru", "alex2002"
        , 19L, roles);
        userService.add(admin);
    }

}
