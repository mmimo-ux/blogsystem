package com.lrm.service;

import com.lrm.po.Comment;
import com.lrm.po.User;

import java.util.List;

public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);

    void deleteComment(Long commentId);

    void deleteComments(Long blog_id);

}
