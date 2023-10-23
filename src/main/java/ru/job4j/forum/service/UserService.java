package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.store.UserRepository;

@Service
public class UserService {
    private final UserRepository users;

    public UserService(UserRepository users) {
        this.users = users;
    }

    public User findUserByName(String name) {
        return users.findByUsername(name).get();
    }

    public User get(Integer id) {
        return users.findById(id).get();
    }

    public void save(User user) {
        if (findUserByName(user.getUsername()) == null) {
            users.save(user);
        }
    }

//    private static AtomicInteger USER_ID = new AtomicInteger(0);
//    private final Map<Integer, User> users = new HashMap<>();
//
//    public UserService() {
//    }
//
//    public Collection<User> getAll() {
//        return users.values();
//    }
//
//    public User get(Integer id) {
//        return users.get(id);
//    }
//
//    public void save(User user) {
//        if (user.getId() == 0) {
//            user.setId(USER_ID.incrementAndGet());
//        }
//        users.put(user.getId(), user);
//    }
}