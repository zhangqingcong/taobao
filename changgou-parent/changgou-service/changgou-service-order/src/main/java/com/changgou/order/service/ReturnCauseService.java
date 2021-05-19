package com.changgou.order.service;

import com.changgou.order.pojo.ReturnCause;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ReturnCauseService {
    List<ReturnCause> findAll();
    ReturnCause findById(Integer id);
    void add(ReturnCause returnCause);
    void delete(Integer id);
    void update(ReturnCause returnCause);
    List<ReturnCause> findList(ReturnCause returnCause);
    PageInfo<ReturnCause> findPage(Integer page,Integer size);
    PageInfo<ReturnCause> findPage(ReturnCause returnCause,Integer page,Integer size);
}
