package ru.job4j.forum.store;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
