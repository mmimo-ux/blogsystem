package com.lrm.dao;

import com.lrm.po.Blog;
import com.lrm.po.Follow;
import com.lrm.po.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowLikeRepository extends JpaRepository<Follow, Long> {

    Follow findByAuserAndBuser(Long a, Long b);

    List<Follow> findByAuser(Long a);

    void deleteByAuserAndBuser(Long a,Long b);
}
