package com.lrm.web.tourist;

import com.lrm.po.User;
import com.lrm.service.UserService;
import com.lrm.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/tourist")
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register() {
        return "tourist/register";
    }

    ;

    @PostMapping("/register")
    public String login(@RequestParam String username, @RequestParam String password, @RequestParam String email,
                        User user, HttpSession session) {
        //表单验证（昵称不能重复）
        user.setNickname(username);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setDescription("暂无描述");
        user.setFlags("神秘人物");
        user.setHobbies("未知");
        user.setShowavatar("https://unsplash.it/800/600?image=1005");
        user.setType(2);
        userService.saveRegister(user);
        return "tourist/login";
    }

    @RequestMapping("/isUsernameUnique")
    //转换json数据  @ResponseBody
    @ResponseBody
    public String isUsernameUnique(String username) {
        boolean byUsername = userService.isUsernameRepeat(username);
        //把byUsername转成字符串
        return String.valueOf(byUsername);
    }

}
