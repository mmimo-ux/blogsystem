package com.lrm.service;

import com.lrm.dao.FollowLikeRepository;

import com.lrm.po.Blog;
import com.lrm.po.Follow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FollowLikeServiceImpl implements FollowLikeService {

    @Autowired
    private FollowLikeRepository followlikeRepository;

    @Override
    public void saveFollow(Long a, Long b) {
        Follow follow = new Follow(a, b, new Date());
        followlikeRepository.save(follow);
    }

    @Transactional
    @Override
    public void deleteFollow(Long a, Long b) {
        followlikeRepository.deleteByAuserAndBuser(a,b);
    }

    @Override
    public boolean hasFollow(Long a, Long b) {
        if (followlikeRepository.findByAuserAndBuser(a, b)!=null) {
            return true;
        } else
            return false;
    }

    @Override
    public List<Follow> ListLike(Long a) {
        return followlikeRepository.findByAuser(a);
    }




    @Override
    public Boolean isLikeEach(Long a, Long b) {
        if (followlikeRepository.findByAuserAndBuser(b,a)!=null){
            return true;
        }
        else return false;
    }
}
