package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PostService {
    private static AtomicInteger POST_ID = new AtomicInteger(0);
    private final Map<Integer, Post> posts = new HashMap<>();

    public PostService() {
        Post post = Post.of("Spring", "Здравствуйте, у меня есть проблемы в понимании Spring, не могли бы вы подсказать хорошие источники по этой теме.");
        save(post);
//        System.out.println(post.toString());
    }

    public Collection<Post> getAll() {
//        System.out.println(posts);
        return posts.values();
    }

    public Post get(Integer id) {
        return posts.get(id);
    }

    public void save(Post post) {
        if (post.getId() == 0) {
            post.setId(POST_ID.incrementAndGet());
        }
        posts.put(post.getId(), post);
    }
}