package com.lrm.service;

import com.lrm.dao.UserRepository;
import com.lrm.po.User;
import com.lrm.util.MD5Utils;
import com.lrm.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;


    @Override
    public User checkUser(String username, String password, int type) {
        User user = userRepository.findByUsernameAndPasswordAndType(username, MD5Utils.code(password), type);
        return user;
    }

    @Override
    public void saveRegister(User user) {
        String pwd = MD5Utils.code(user.getPassword());
        user.setPassword(pwd);
        user.setAvatar("https://picsum.photos/id/1/100/100");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        System.out.println(user.getNickname());
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        User u = userRepository.findByUsername(user.getUsername());
        BeanUtils.copyProperties(user, u, MyBeanUtils.getNullPropertyNames(user));
        if (user.getFlags() != null) {
            u.setFlags(user.getFlags().replace("，", ","));
        }
        if (user.getHobbies() != null) {
            u.setHobbies(user.getHobbies().replace("，", ","));
        }
        u.setUpdateTime(new Date());
        userRepository.save(u);
    }

    @Override
    public User backUser(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public Boolean changePwd(String oldpwd, String newpwd, Long id) {
        if (!userRepository.findOne(id).getPassword().equals(MD5Utils.code(oldpwd))) {
            return false;
        }
        userRepository.updatePwd(id, MD5Utils.code(newpwd));
        return true;
    }

    @Override
    public Boolean changeMail(String oldemail, String newemail, Long id) {
        if (!userRepository.findOne(id).getEmail().equals(oldemail)) {
            return false;
        }
        userRepository.updateEmail(id, newemail);
        return true;
    }

    @Override
    public Boolean isUsernameRepeat(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean isNicknameRepeat(String nickname) {
        User user = userRepository.findByNickname(nickname);
        if (user == null) {
            return true;
        }
        return false;

    }

    @Override
    public Boolean isEmailRepeat(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return true;
        }
        return false;
    }
}
