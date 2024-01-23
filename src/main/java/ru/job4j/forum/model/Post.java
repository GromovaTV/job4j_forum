package ru.job4j.forum.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public static Post of(String name, String desc) {
        Post post = new Post();
        post.name = name;
        post.description = desc;
        return post;
    }

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", created=" + created
                + '}';
    }
}