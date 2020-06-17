package com.lrm.dao;

import com.lrm.po.Message;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {


    List<Message> findByUserIdAndParentCommentNull(Long userId, Sort sort);

    @Transactional
    @Modifying
    @Query("update Message m set m.content='该评论已被删除',m.isExist=false where m.id=?1")
    void updateMessage(Long messageId);


}

