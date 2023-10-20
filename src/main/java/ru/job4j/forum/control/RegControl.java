package ru.job4j.forum.control;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

@Controller
public class RegControl {
    private final PasswordEncoder encoder;
    private final UserService service;

    public RegControl(PasswordEncoder encoder, UserService service) {
        this.encoder = encoder;
        this.service = service;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        System.out.println("START REG");
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        service.save(user);
        System.out.println(service.get(user.getId()).toString());
        System.out.println("FINISH REG");
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}
