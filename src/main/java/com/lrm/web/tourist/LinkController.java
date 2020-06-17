package com.lrm.web.tourist;

import com.lrm.po.Link;
import com.lrm.po.Type;
import com.lrm.po.User;
import com.lrm.service.LinkService;
import com.lrm.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.HttpCookie;

@Controller
@RequestMapping("/tourist")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/links")
    public String types(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC)
                                Pageable pageable, Model model, HttpSession session) {
        User user= (User) session.getAttribute("tuser");
        model.addAttribute("page", linkService.listOneLink(user,pageable));
        return "tourist/links";
    }

    @GetMapping("/links/input")
    public String input(Model model) {
        model.addAttribute("link", new Link());
        return "tourist/links-input";
    }

    @GetMapping("/links/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("link", linkService.getLink(id));
        return "tourist/links-input";
    }

    @PostMapping("/links")
    public String post(@Valid Link link, BindingResult result, RedirectAttributes attributes, HttpSession session) {
        User user= (User) session.getAttribute("tuser");
        Link link1 = linkService.getLinkByurl(user,link.getUrl());
        if (link1 != null) {
            result.rejectValue("url", "nameError", "不能添加重复的url地址");
        }
        if (result.hasErrors()) {
            return "tourist/links-input";
        }
        link.setUser(user);
        Link l=linkService.saveLink(link);
        if (l == null) {
            //没保存成功
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/tourist/links";
    }

    //更新友链
    @PostMapping("/links/{id}")
    public String editPost(Link link, RedirectAttributes attributes,HttpSession session) {

        User user= (User) session.getAttribute("tuser");
        Link link1 = linkService.getLinkByurl(user,link.getUrl());

        if (link1 != null) {
            attributes.addFlashAttribute("message", "错误:已存在同名的分类");
        } else {
            link.setUser(user);
            linkService.updateLink(link.getId(),link);
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/tourist/links";


    }


    //删除友链
    @GetMapping("/links/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        linkService.deleteLink(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/tourist/links";
    }

    @GetMapping("/linkshow/{userId}")
    public String linkShow(@PathVariable Long userId,Model model){
        model.addAttribute("links",linkService.listLink(userId));
        model.addAttribute("fuser",userId);
        return "tourist/linkshow";
    }

}
