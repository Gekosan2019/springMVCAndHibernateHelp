package web.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

// Для того, чтобы в дальнейшим использовать класс User в Spring Security, он должен реализовывать интерфейс UserDetails.
// UserDetails можно представить, как адаптер между БД пользователей и тем что требуется Spring Security внутри SecurityContextHolder
// UserDetails - Предоставляет основную информацию о пользователе.
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @Column(name = "username")
    private String username; // уникальное значение

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    private Long age;

    @OneToMany(cascade =  CascadeType.ALL, mappedBy = "user")
    private Set<Role> roles = new HashSet<>();


    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getAge() {
        return age;
    }

    public User() {

    }

    public User(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Возвращает полномочия, предоставленные пользователю.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // Указывает, истек ли срок действия учетной записи пользователя.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Указывает, заблокирован пользователь или разблокирован.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Указывает, истек ли срок действия учетных данных (пароля) пользователя.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Указывает, включен или отключен пользователь.
    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
