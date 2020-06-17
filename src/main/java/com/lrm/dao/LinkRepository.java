package com.lrm.dao;

import com.lrm.po.Link;
import com.lrm.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LinkRepository extends JpaRepository<Link, Long> {

    Page<Link> findByUser(User user, Pageable pageable);

    Link findByUserAndUrl(User user,String url);

    List<Link> findByUser(User user);

}
