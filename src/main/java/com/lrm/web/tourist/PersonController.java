package com.lrm.web.tourist;

import com.lrm.po.User;
import com.lrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/tourist")
public class PersonController {

    @Autowired
    private UserService userService;

    @GetMapping("/pmessage")
    public String messageShow(HttpSession session, Model model) {
        User user = (User) session.getAttribute("tuser");
        model.addAttribute("user", userService.backUser(user.getId()));
        return "tourist/pmessage";
    }

    @PostMapping("/pmessage")
    public String messageChange(User user, RedirectAttributes attributes) {
        if (user.getDescription().equals("")) {
            user.setDescription("暂无描述");
        }
        if (user.getFlags().equals("")) {
            user.setFlags("神秘人物");
        }
        if (user.getHobbies().equals("")) {
            user.setHobbies("未知");
        }
        userService.updateUser(user);
        attributes.addFlashAttribute("message", "修改成功!");
        return "redirect:pmessage";
    }

    @GetMapping("/changepwd")
    public String changePwd() {
        return "tourist/changepwd";
    }

    @PostMapping("/changepwd")
    public String toChangePwd(@RequestParam String oldpwd,
                              @RequestParam String password, HttpSession session, RedirectAttributes attributes) {
        User user = (User) session.getAttribute("tuser");
        if (userService.changePwd(oldpwd, password, user.getId())) {
            attributes.addFlashAttribute("message", "修改成功!");

        } else {
            attributes.addFlashAttribute("failmessage", "修改失败，旧密码输入错误!");
        }
        return "redirect:changepwd";
    }

    @RequestMapping("/isNicknameUnique")
    //转换json数据  @ResponseBody
    @ResponseBody
    public String isNicknameUnique(String nickname) {
        boolean byNickname = userService.isNicknameRepeat(nickname);
        //把byUsername转成字符串
        return String.valueOf(byNickname);
    }

    @GetMapping("/changemail")
    public String changeMail() {
        return "tourist/changemail";
    }

    @PostMapping("/changemail")
    public String toChangeMail(@RequestParam String oldemail,
                               @RequestParam String newemail, HttpSession session, RedirectAttributes attributes) {
        User user = (User) session.getAttribute("tuser");
        if (userService.changeMail(oldemail, newemail, user.getId())) {
            attributes.addFlashAttribute("message", "修改成功!");

        } else {
            attributes.addFlashAttribute("failmessage", "修改失败，旧邮箱输入错误!");
        }
        return "redirect:changemail";
    }

    @RequestMapping("/isEmailUnique")
    //转换json数据  @ResponseBody
    @ResponseBody
    public String isEmailUnique(String email) {
        boolean byEmail = userService.isEmailRepeat(email);
        //把byUsername转成字符串
        return String.valueOf(byEmail);
    }
}
