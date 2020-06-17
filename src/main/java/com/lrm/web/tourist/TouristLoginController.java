package com.lrm.web.tourist;

import com.lrm.po.User;
import com.lrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/tourist")
public class TouristLoginController {

    @Autowired
    private UserService userService;

    @GetMapping

    public String loginPage() {
        return "tourist/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes, Model model) {
        User user = userService.checkUser(username, password, 2);
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("tuser", user);
            model.addAttribute("tuser", user);
            return "tourist/welcome";
        } else {
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/tourist";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session, Model model) {

        session.removeAttribute("tuser");
        return "redirect:/";
    }
}
