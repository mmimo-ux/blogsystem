package com.lrm.web.admin;

import com.lrm.po.Blog;
import com.lrm.po.User;
import com.lrm.service.BlogService;
import com.lrm.service.TagService;
import com.lrm.service.TypeService;
import com.lrm.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class AdminBlogController {

    private static final String EXAMINE = "admin/blogs-examine";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "admin/blogs :: blogList";

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                Pageable pageable, BlogQuery blog, Model model) {

        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blog, null));

        return LIST;

    }

    @PostMapping("/blogs")
    public String post(@RequestParam Long id, @RequestParam String examine, RedirectAttributes attributes, HttpSession session) {
        blogService.examineBlog(id, examine);
        // attributes.addFlashAttribute("message","操作成功");
        return "redirect:/admin/blogs";

    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                 Pageable pageable, BlogQuery blog, Model model) {

        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blog, null));

        return REDIRECT_LIST;//局部刷新

    }


    @GetMapping("/blogs/{id}/examine")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getAndConvert(id));
        return EXAMINE;
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {

        blogService.deleteBlog(id);

        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";

    }

}
