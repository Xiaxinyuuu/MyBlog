package com.xiaxinyu.myblog.service;

import com.xiaxinyu.myblog.dao.UserRepository;
import com.xiaxinyu.myblog.po.User;
import com.xiaxinyu.myblog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired//注入
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        //findByUsernameAndPassword方法没有实现是怎么调用起来的呢？
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        //采用MD5加密，避免明码在网络上传输
        return user;
    }
}
