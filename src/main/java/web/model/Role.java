package web.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

// Этот класс реализует интерфейс GrantedAuthority, в котором необходимо переопределить только один метод getAuthority() (возвращает имя роли).
// Имя роли должно соответствовать шаблону: «ROLE_ИМЯ», например, ROLE_USER.

@Entity
@Table(name = "authorities")
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "authority")
    private String authority;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    public Role() {
    }

    public Role(String authority, User user) {
        this.authority = authority;
        this.user = user;
    }

    public Role(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}