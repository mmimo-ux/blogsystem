package com.lrm.web.tourist;

import com.lrm.po.User;
import com.lrm.service.FollowLikeService;
import com.lrm.service.FollowMsgService;
import com.lrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class AboutShowController {

    @Autowired
    private UserService userService;

    @Autowired
    private FollowLikeService followLikeService;

    @Autowired
    private FollowMsgService followMsgService;

    @GetMapping("/about/{userId}")
    public String about(@PathVariable Long userId, HttpSession session, Model model) {
        //判断对方我有没关注
        User user = (User) session.getAttribute("tuser");
        if (user != null) {        //用户已经登录
            if (followLikeService.hasFollow(user.getId(), userId)) {
                model.addAttribute("follow", true);
            } else {
                model.addAttribute("follow", false);
            }
        }

        model.addAttribute("user", userService.backUser(userId));
        model.addAttribute("fuser", userId);
        return "about";
    }

    @GetMapping("/tourist/follow/{userId}")
    public String follow(@PathVariable Long userId, HttpSession session) {
        //添加关注
        User user = (User) session.getAttribute("tuser");
        followLikeService.saveFollow(user.getId(), userId);
        followMsgService.saveMsg(user.getId(),userId,1);

        return "redirect:/about/" + userId;

    }

    @GetMapping("/tourist/disfollow/{userId}")
    public String dislike(@PathVariable Long userId, HttpSession session) {
        //取消关注
        User user = (User) session.getAttribute("tuser");
        followLikeService.deleteFollow(user.getId(), userId);
        followMsgService.saveMsg(user.getId(),userId,0);
        return "redirect:/about/" + userId;

    }
}
