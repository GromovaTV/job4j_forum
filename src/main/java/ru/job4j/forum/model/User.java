package ru.job4j.forum.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity(name = "users")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String password;

    @Column(unique = true)
    private String username;

    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authority authority;

    private boolean enabled;

    public static User of(String username, String password) {
        User user = new User();
        user.username = username;
        user.password = password;
        return user;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", password='" + password + '\''
                + ", username='" + username + '\''
                + ", enabled=" + enabled
                + '}';
    }
}