package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserService {
    private static AtomicInteger USER_ID = new AtomicInteger(0);
    private final Map<Integer, User> users = new HashMap<>();

    public UserService() {
    }

    public Collection<User> getAll() {
        return users.values();
    }

    public User get(Integer id) {
        return users.get(id);
    }

    public void save(User user) {
        if (user.getId() == 0) {
            user.setId(USER_ID.incrementAndGet());
        }
        users.put(user.getId(), user);
    }
}