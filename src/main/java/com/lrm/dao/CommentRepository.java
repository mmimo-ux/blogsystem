package com.lrm.dao;

import com.lrm.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);

    @Transactional
    @Modifying
    @Query("update Comment c set c.content='该评论已被删除',c.isExist=false where c.id=?1")
    void updateComment(Long commentId);

    //删除文章后删除相关的所有评论
    void deleteByBlogId(Long blogId);
}
