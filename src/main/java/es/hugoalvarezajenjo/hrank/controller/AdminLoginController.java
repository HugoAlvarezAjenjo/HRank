package es.hugoalvarezajenjo.hrank.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {

    @Value("${admin.password}")
    private String adminPassword;

    @GetMapping("/login")
    public String loginPage() {
        return "admin-login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String password, HttpSession session, Model model) {
        if (adminPassword.equals(password)) {
            session.setAttribute("adminLoggedIn", true);
            return "redirect:/admin";
        } else {
            model.addAttribute("error", "Invalid password");
            return "admin-login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("adminLoggedIn");
        return "redirect:/admin/login";
    }
}
