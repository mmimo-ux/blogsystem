package com.lrm.service;

import com.lrm.po.FollowMessage;

import java.util.List;

public interface FollowMsgService {

    void saveMsg(Long a,Long b,int status);

    List<FollowMessage> ShowbyYears(String year, Long buser);

    List<String> ShowMsgYears(Long buser);

}
