package com.lrm.service;

import com.lrm.po.Message;

import java.util.List;

public interface MessageService {

    List<Message> listMessageByUserId(Long useId);

    Message saveMessage(Message message);

    void deleteMessage(Long messageId);

}
