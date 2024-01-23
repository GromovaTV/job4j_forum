package ru.job4j.forum.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PostControl {

    private static final Logger LOG = LoggerFactory.getLogger(PostControl.class.getName());
    private final PostService posts;

    public PostControl(PostService posts) {
        this.posts = posts;
    }

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public String get(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("post", posts.get(id));
        return "post";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Post post, HttpServletRequest req) {
        posts.save(post);
        return "redirect:/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam(name = "id") Integer id, Model model) {
        LOG.info("START EDIT CONTROL");
        Post post;
        if (id == 0) {
            post = Post.of("", "");
        } else {
            post = posts.get(id);
        }
        model.addAttribute("post", post);
        LOG.info("FINISH EDIT CONTROL");
        return "edit";
    }
}