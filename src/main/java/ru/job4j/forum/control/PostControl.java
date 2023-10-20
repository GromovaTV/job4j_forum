package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PostControl {
    private final PostService service;

    public PostControl(PostService service) {
        this.service = service;
    }

    @GetMapping(path = "/edit")
    public String edit(Model model, @RequestParam(name = "id") Integer id) {
        System.out.println("START EDIT CONTROL");
        Post post;
        if (id == 0) {
            post = Post.of("", "");
        } else {
            post = service.get(id);
        }
        model.addAttribute("post", post);
        System.out.println(post);
        System.out.println("FINISH EDIT CONTROL");
        return "edit";
    }

    @PostMapping(path = "/save")
    public String save(@ModelAttribute Post post, HttpServletRequest req) {
        service.save(post);
        return "redirect:/";
    }

    @GetMapping(path = "/post")
    public String post(Model model, @RequestParam(name = "id") Integer id) {
        System.out.println("START POST CONTROL");
        System.out.println(id);
        System.out.println(service.get(id));
        model.addAttribute("post", service.get(id));
        System.out.println("FINISH POST CONTROL");
        return "post";
    }
}