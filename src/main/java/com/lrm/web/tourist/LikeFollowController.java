package com.lrm.web.tourist;

import com.lrm.po.Follow;
import com.lrm.po.FollowMessage;
import com.lrm.po.User;
import com.lrm.service.FollowLikeService;
import com.lrm.service.FollowMsgService;
import com.lrm.service.UserService;
import com.lrm.vo.FollowShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tourist")
public class LikeFollowController {

    @Autowired
    private FollowLikeService followLikeService;

    @Autowired
    private FollowMsgService followMsgService;

    @Autowired
    private UserService userService;

    @GetMapping("/likefollow")
    public String likeFollow(HttpSession session, Model model){
        User user= (User) session.getAttribute("tuser");
       List<Follow> follows=followLikeService.ListLike(user.getId());
        List<FollowShow> followShows=new ArrayList<>();
        Boolean likeeach;
        for (Follow f:follows){
            User user1=userService.backUser(f.getBuser());
            likeeach=followLikeService.isLikeEach(user.getId(),f.getBuser());
            FollowShow followShow=new FollowShow(user1.getId(),user1.getNickname(),user1.getAvatar(),f.getFollowTime(),likeeach);
            followShows.add(followShow);
        }
        model.addAttribute("likes",followShows);
        return "tourist/likefollow";
    }

    @GetMapping("/history")
    public String historyMessage(HttpSession session, Model model){
        User user= (User) session.getAttribute("tuser");
        Map<String, List<FollowShow>> map = new HashMap<>();
        List<String> years=followMsgService.ShowMsgYears(user.getId());
        List<FollowShow> followShows=new ArrayList<>();
        List<FollowMessage> followMessages;
        for (String year : years) {
            followMessages=followMsgService.ShowbyYears(year,user.getId());
            for (FollowMessage f:followMessages){
                User user1=userService.backUser(f.getAuser());
                FollowShow followShow=new FollowShow(user1.getId(),user1.getNickname(),user1.getAvatar(),f.getFollowTime(),f.getStatus());
                followShows.add(followShow);
            }
            map.put(year,followShows);
            followShows=new ArrayList<>();
        }
        model.addAttribute("historyMap",map);
        return "tourist/history";
    }



}
