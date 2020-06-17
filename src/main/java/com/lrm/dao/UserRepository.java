package com.lrm.dao;

import com.lrm.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPasswordAndType(String username, String password, int type);

    @Transactional
    @Modifying
    @Query("update User u set u.nickname=?2 ,u.avatar=?3 where u.username=?1 ")
    void updateUser(String username, String nickname, String avatar);

    @Transactional
    @Modifying
    @Query("update User u set u.password=?2 where u.id=?1 ")
    void updatePwd(Long id, String newpwd);

    @Transactional
    @Modifying
    @Query("update User u set u.email=?2 where u.id=?1 ")
    void updateEmail(Long id, String newemail);


    User findByUsername(String username);

    User findByNickname(String nickname);

    User findByEmail(String email);

}
