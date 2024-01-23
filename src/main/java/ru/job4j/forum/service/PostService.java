package ru.job4j.forum.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.store.PostRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

    private static final Logger LOG = LoggerFactory.getLogger(PostService.class.getName());
    private final PostRepository posts;

    public PostService(PostRepository posts) {
        this.posts = posts;
    }

    public List<Post> getAll() {
        List<Post> rsl = new ArrayList<>();
        posts.findAll().forEach(rsl::add);
        return rsl;
    }

    public Post get(Integer id) {
        return posts.findById(id).get();
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            post.setCreated(Date.from(Instant.now()));

        } else {
            post.setCreated(posts.findById(post.getId()).get().getCreated());
        }
        Post save = posts.save(post);
        LOG.info("Save: " + save);
        return save;
    }
}