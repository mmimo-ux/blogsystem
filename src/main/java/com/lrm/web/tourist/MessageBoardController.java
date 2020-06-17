package com.lrm.web.tourist;

import com.lrm.po.Blog;
import com.lrm.po.Comment;
import com.lrm.po.Message;
import com.lrm.po.User;
import com.lrm.service.MessageService;
import com.lrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/tourist")
public class MessageBoardController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;


    @GetMapping("/board/{userId}")
    public String MessageShow(@PathVariable Long userId, HttpSession session, Model model) {
        if (userId != 0) {
            model.addAttribute("comments", messageService.listMessageByUserId(userId));
        } else {

            User user = (User) session.getAttribute("tuser");
            model.addAttribute("comments", messageService.listMessageByUserId(user.getId()));

        }
        model.addAttribute("fuser", userId);
        return "tourist/messageboard";
    }


    @PostMapping("/comments/{userId}")
    public String post(@PathVariable Long userId, Message message, HttpSession session) {
        User user = (User) session.getAttribute("tuser");    //登录者
        message.setCommenter(user);
        message.setExist(true);
        if (userId == 0) {   //看自己的留言板
            message.setUser(user);
            message.setAdminComment(true);
        } else {
            message.setUser(userService.backUser(userId)); //这对象应该是被访问者
            message.setAdminComment(false);
        }
        messageService.saveMessage(message);
        return "redirect:/tourist/backtoboard/" + userId;
    }

    @GetMapping("/backtoboard/{userId}")
    public String BacktoBoard(@PathVariable Long userId, HttpSession session, Model model) {
        if (userId == 0) {//自己看自己的留言板
            User user = (User) session.getAttribute("tuser");
            model.addAttribute("comments", messageService.listMessageByUserId(user.getId()));
        } else {//看别人的留言板，所以要显示别人的留言板信息
            model.addAttribute("comments", messageService.listMessageByUserId(userId));
        }
        model.addAttribute("fuser", userId);
        return "tourist/messageboard::commentList";
    }

    @PostMapping("/deletecomment/{userId}")
    public String deleteComment(@PathVariable Long userId, Message message) {
        messageService.deleteMessage(message.getId());

        return "redirect:/tourist/backtoboard/" + userId;
    }

/*    @GetMapping("/messageboard/{userId}")   //进入别人留言板的入口
    public String chatToWriter(@PathVariable Long userId,Model model,HttpSession session){
        model.addAttribute("comments",messageService.listMessageByUserId(userId));
        return "redirect:/tourist/board/"+userId;
    }*/
}
