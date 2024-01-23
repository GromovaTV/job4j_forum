package ru.job4j.forum.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.service.PostService;

@Controller
public class IndexControl {

    private static final Logger LOG = LoggerFactory.getLogger(IndexControl.class.getName());
    private final PostService posts;

    public IndexControl(PostService posts) {
        this.posts = posts;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        LOG.info("Start IndexControl");
        model.addAttribute("posts", posts.getAll());
        return "index";
    }
}