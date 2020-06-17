package com.lrm.dao;

import com.lrm.po.Follow;
import com.lrm.po.FollowMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowMsgRepository extends JpaRepository<FollowMessage, Long> {

    @Query("select function('date_format',f.followTime,'%Y') as year from FollowMessage f where f.buser=?1 group by function('date_format',f.followTime,'%Y') order by year desc  ")
    List<String> findGroupYear(Long buser);

    @Query("select f from FollowMessage f where function('date_format',f.followTime,'%Y') = ?1 and f.buser=?2"  )
    List<FollowMessage> findByYear(String year, Long buser);
    
}
