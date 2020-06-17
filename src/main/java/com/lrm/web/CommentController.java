package com.lrm.web;

import com.lrm.po.Blog;
import com.lrm.po.Comment;
import com.lrm.po.User;
import com.lrm.service.BlogService;
import com.lrm.service.CommentService;
import com.lrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {
        Long blogId = comment.getBlog().getId();
        Blog blog = blogService.getBlog(blogId);
        comment.setBlog(blog);
        User user = (User) session.getAttribute("tuser");
        comment.setCommenter(user);
        comment.setExist(true);
        if (user.getId().equals(blog.getUser().getId())) {
            comment.setAdminComment(true);
        }

        commentService.saveComment(comment);
        return "redirect:/comments/" + comment.getBlog().getId();
    }

    @PostMapping("/deletecomment")
    public String deleteComment(Comment comment) {
        commentService.deleteComment(comment.getId());
        return "redirect:/comments/" + comment.getBlog().getId();
    }
}
