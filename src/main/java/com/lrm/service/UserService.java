package com.lrm.service;

import com.lrm.po.User;

public interface UserService {

    User checkUser(String username, String password, int type);

    void saveRegister(User user);

    void updateUser(User user);

    User backUser(Long id);

    Boolean changePwd(String oldpwd, String newpwd, Long id);

    Boolean changeMail(String oldemail, String newemail, Long id);

    Boolean isUsernameRepeat(String username);

    Boolean isNicknameRepeat(String nickname);

    Boolean isEmailRepeat(String email);
}
