package com.xiaxinyu.myblog.service;

import com.xiaxinyu.myblog.po.User;

public interface UserService {
    User checkUser(String username,String password);
}
