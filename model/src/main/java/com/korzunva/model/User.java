package com.korzunva.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.korzunva.validator.annotations.Length;
import com.korzunva.validator.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class User represents User
 */
public class User extends BaseEntity {

    @NotNull(message = "Login mustn't be null")
    @Length(length = 2, message = "Login must contain at least 2 symbols")
    private String login;

    @NotNull(message = "Password mustn't be null")
    @Length(length = 2, message = "Password must contain at least 2 symbols")
    private String password;

    private List<Role> roles;

    private List<Room> rooms;

    public User() {
        roles = new ArrayList<>(1);
        roles.add(Role.GUEST);
    }

    public User(String id) {
        this.id = id;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public User (String id, String login, List<Role> roles) {
        this.id = id;
        this.login = login;
        this.roles = roles;
    }

    public User(String id, String login, String password, List<Role> roles) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    public User(String id, String login, String password, List<Role> roles, List<Room> rooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.roles = roles;
        this.rooms = rooms;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, roles);
    }

}
