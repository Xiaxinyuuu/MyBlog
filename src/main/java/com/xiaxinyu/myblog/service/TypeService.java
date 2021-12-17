package com.xiaxinyu.myblog.service;

import com.xiaxinyu.myblog.po.Type;
import org.hibernate.type.ListType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {
    //新增
    Type saveType(Type type);
    //查询
    Type getType(Long id);
    //分页查询
    Page<Type> listType(Pageable pageable);
    //修改
    Type updateType(Long id,Type type);
    //删除
    void deleteType(Long id);

    //返回所有数据
    List<Type> listType();

    //通过名称查询Type
    Type getTypeByName(String name);

}
