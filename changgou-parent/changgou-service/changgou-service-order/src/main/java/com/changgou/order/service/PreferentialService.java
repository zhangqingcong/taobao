package com.changgou.order.service;

import com.changgou.order.pojo.Preferential;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PreferentialService {
    List<Preferential> findAll();
    Preferential findById(Integer id);
    void add(Preferential preferential);
    void delete(Integer id);
    void update(Preferential preferential);
    List<Preferential> findList(Preferential preferential);
    PageInfo<Preferential> findPage(Integer page,Integer size);
    PageInfo<Preferential> findPage(Preferential preferential,Integer page,Integer size);
}
