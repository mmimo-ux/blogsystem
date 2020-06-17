package com.lrm.service;

import com.lrm.dao.FollowMsgRepository;
import com.lrm.po.FollowMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FollowMsgServiceImpl implements FollowMsgService{

    @Autowired
    private FollowMsgRepository followMsgRepository;

    @Override
    public void saveMsg(Long a, Long b, int status) {
        FollowMessage followMessage=new FollowMessage(a,b,new Date(),status);
        followMsgRepository.save(followMessage);
    }

    @Override
    public List<FollowMessage> ShowbyYears(String year, Long buser) {
        return followMsgRepository.findByYear(year,buser);
    }

    @Override
    public List<String> ShowMsgYears(Long buser) {
        return followMsgRepository.findGroupYear(buser);
    }

    /* @Override
    public List<Follow> ShowbyYears(String year,Long buser) {
        return followlikeRepository.findByYear(year,buser);
    }


  /*  @Override
    public Map<String, List<Follow>> showMessage(Long buser) {
        Map<String, List<FollowShow>> map = new HashMap<>();
        for (String year : years) {
            map.put(year, ;
        }
        return map;
    }

    @Override
    public List<String> ShowMsgYears(Long buser) {
        return followlikeRepository.findGroupYear(buser);
    }*/
}
