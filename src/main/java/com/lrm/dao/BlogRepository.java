package com.lrm.dao;

import com.lrm.po.Blog;
import com.lrm.po.User;
import com.lrm.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

    @Query("select b from Blog b where b.recommend = true")
    List<Blog> findTop(Pageable pageable);

    @Query("select b from Blog b where b.examine='已通过' and b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query, Pageable pageable);


    @Query("select b from Blog b where b.examine='已通过'")
    Page<Blog> findBlogs(Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Blog b set b.examine=?2 where b.id=?1")
    void updateBlog(Long id, String examine);

    Page<Blog> findByUserId(Long userId, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Blog b set b.views = b.views+1 where b.id = ?1")
    int updateViews(Long id);


    @Query("select function('date_format',b.updateTime,'%Y') as year from Blog b group by function('date_format',b.updateTime,'%Y') order by year desc ")
    List<String> findGroupYear();

    @Query("select b from Blog b where function('date_format',b.updateTime,'%Y') = ?1")
    List<Blog> findByYear(String year);

    @Query("select b from Blog b where b.examine='已通过' and b.user=?1 and (b.title like ?1 or b.content like ?1)")
    Page<Blog> findByQueryAndUser(User user, String query, Pageable pageable);
}
