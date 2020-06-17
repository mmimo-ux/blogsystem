package com.lrm.service;

import com.lrm.po.Blog;
import com.lrm.po.Follow;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface FollowLikeService {

    void saveFollow(Long a, Long b);

    void deleteFollow(Long a, Long b);

    boolean hasFollow(Long a, Long b);

    List<Follow> ListLike(Long a);

    Boolean isLikeEach(Long a,Long b);

}
