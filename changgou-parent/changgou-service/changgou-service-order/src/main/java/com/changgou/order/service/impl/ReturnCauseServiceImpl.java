package com.changgou.order.service.impl;

import com.changgou.order.dao.ReturnCauseMapper;
import com.changgou.order.pojo.ReturnCause;
import com.changgou.order.service.ReturnCauseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.persistence.Id;
import java.util.List;
@Service
public class ReturnCauseServiceImpl implements ReturnCauseService {
    @Autowired
    private ReturnCauseMapper returnCauseMapper;
    @Override
    public List<ReturnCause> findAll() {
        return returnCauseMapper.selectAll();
    }

    @Override
    public ReturnCause findById(Integer id) {
        return returnCauseMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(ReturnCause returnCause) {
        returnCauseMapper.insertSelective(returnCause);
    }

    @Override
    public void delete(Integer id) {
        returnCauseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(ReturnCause returnCause) {
        returnCauseMapper.updateByPrimaryKeySelective(returnCause);
    }

    @Override
    public List<ReturnCause> findList(ReturnCause returnCause) {
        Example example = createExample(returnCause);
        return returnCauseMapper.selectByExample(example);
    }

    @Override
    public PageInfo<ReturnCause> findPage(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (PageInfo<ReturnCause>) returnCauseMapper.selectAll();
    }

    @Override
    public PageInfo<ReturnCause> findPage(ReturnCause returnCause, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        Example example = createExample(returnCause);
        return (PageInfo<ReturnCause>) returnCauseMapper.selectByExample(example);
    }

    private Example createExample(ReturnCause returnCause){
        Example example = new Example(ReturnCause.class);
        Example.Criteria criteria = example.createCriteria();
        if (returnCause!=null){
            if (!StringUtils.isEmpty(returnCause.getId())){
                criteria.andEqualTo("id",returnCause.getId());
            }
            if (!StringUtils.isEmpty(returnCause.getCause())){
                criteria.andLike("cause","%"+returnCause.getCause()+"%");
            }
            if (!StringUtils.isEmpty(returnCause.getSeq())){
                criteria.andEqualTo("seq",returnCause.getSeq());
            }
            if (!StringUtils.isEmpty(returnCause.getStatus())){
                criteria.andEqualTo("status",returnCause.getStatus());
            }
        }
        return example;
    }
}
