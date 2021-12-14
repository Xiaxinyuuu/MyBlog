package com.xiaxinyu.myblog.dao;

import com.xiaxinyu.myblog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

//不用关注增删改查的实现，只需给出字段以及类型即可
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username,String password);
}
