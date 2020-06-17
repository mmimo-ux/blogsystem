package com.lrm.web.tourist;

import com.lrm.service.BlogService;
import com.lrm.service.TagService;
import com.lrm.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/tourist")
public class TouristBlogsController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs/{userId}")
    public String lookOthersTitle(@PageableDefault(size = 8, sort = {"id"}, direction = Sort.Direction.DESC)
                                          Pageable pageable, Model model, HttpSession session, @PathVariable Long userId) {
        model.addAttribute("page", blogService.listBlog(pageable, userId));
        model.addAttribute("fuser", userId);
        return "index" ;
    }

    @PostMapping("/blogs/{userId}")
    public String otherSearch(@PageableDefault(size = 8, sort = {"id"}, direction = Sort.Direction.DESC)
                                          Pageable pageable, Model model, @RequestParam String query, @PathVariable Long userId){
        model.addAttribute("page",blogService.listBlog(userId,query,pageable));
        model.addAttribute("query",query);
        return "search";
    }

}
