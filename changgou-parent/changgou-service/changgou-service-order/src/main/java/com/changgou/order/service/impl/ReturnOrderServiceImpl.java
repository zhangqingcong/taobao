package com.changgou.order.service.impl;

import com.changgou.order.dao.ReturnOrderMapper;
import com.changgou.order.pojo.ReturnOrder;
import com.changgou.order.service.ReturnOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.persistence.Id;
import java.math.BigInteger;
import java.util.List;
@Service
public class ReturnOrderServiceImpl implements ReturnOrderService {
    @Autowired
    private ReturnOrderMapper returnOrderMapper;
    @Override
    public List<ReturnOrder> findAll() {
        return returnOrderMapper.selectAll();
    }

    @Override
    public ReturnOrder findById(BigInteger id) {
        return returnOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(ReturnOrder returnOrder) {
        returnOrderMapper.insertSelective(returnOrder);
    }

    @Override
    public void delete(BigInteger id) {
        returnOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(ReturnOrder returnOrder) {
        returnOrderMapper.updateByPrimaryKeySelective(returnOrder);
    }

    @Override
    public List<ReturnOrder> findList(ReturnOrder returnOrder) {
        Example example = createExample(returnOrder);
        return returnOrderMapper.selectByExample(example);
    }

    @Override
    public PageInfo<ReturnOrder> findPage(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (PageInfo<ReturnOrder>) returnOrderMapper.selectAll();
    }

    @Override
    public PageInfo<ReturnOrder> findPage(ReturnOrder returnOrder, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        Example example = createExample(returnOrder);
        return (PageInfo<ReturnOrder>) returnOrderMapper.selectByExample(example);
    }
    private Example createExample(ReturnOrder returnOrder){
        Example example = new Example(ReturnOrder.class);
        Example.Criteria criteria = example.createCriteria();
        if (returnOrder!=null){
            if (StringUtils.isEmpty(returnOrder.getId())){
                criteria.andEqualTo("id",returnOrder.getId());
            }
            if (StringUtils.isEmpty(returnOrder.getOrderId())){
                criteria.andEqualTo("orderId",returnOrder.getOrderId());
            }
            if (StringUtils.isEmpty(returnOrder.getApplyTime())){
                criteria.andGreaterThanOrEqualTo("applyTime",returnOrder.getApplyTime());
            }
            if (StringUtils.isEmpty(returnOrder.getUserId())){
                criteria.andEqualTo("userId",returnOrder.getUserId());
            }
            if (StringUtils.isEmpty(returnOrder.getUserAccount())){
                criteria.andEqualTo("userAccount",returnOrder.getUserAccount());
            }
            if (StringUtils.isEmpty(returnOrder.getLinkMan())){
                criteria.andLike("linkMan","%"+returnOrder.getLinkMan()+"%");
            }
            if (StringUtils.isEmpty(returnOrder.getLinkmanMobile())){
                criteria.andEqualTo("linkmanMobile",returnOrder.getLinkmanMobile());
            }
            if (StringUtils.isEmpty(returnOrder.getType())){
                criteria.andEqualTo("type",returnOrder.getType());
            }
            if (StringUtils.isEmpty(returnOrder.getReturnMoney())){
                criteria.andEqualTo("returnMoney",returnOrder.getReturnMoney());
            }
            if (StringUtils.isEmpty(returnOrder.getIsReturnFreight())){
                criteria.andEqualTo("isReturnFreight",returnOrder.getIsReturnFreight());
            }
            if (StringUtils.isEmpty(returnOrder.getStatus())){
                criteria.andEqualTo("status",returnOrder.getStatus());
            }
            if (StringUtils.isEmpty(returnOrder.getDisposeTime())){
                criteria.andGreaterThanOrEqualTo("disposeTime",returnOrder.getDisposeTime());
            }
            if (StringUtils.isEmpty(returnOrder.getReturnCause())){
                criteria.andLike("returnCause","%"+returnOrder.getReturnCause()+"%");
            }
            if (!StringUtils.isEmpty(returnOrder.getEvidence())){
                criteria.andLike("evidence","%"+returnOrder.getEvidence()+"%");
            }
            if (!StringUtils.isEmpty(returnOrder.getDescription())){
                criteria.andLike("description","%"+returnOrder.getDescription()+"%");
            }
            if (!StringUtils.isEmpty(returnOrder.getRemark())){
                criteria.andLike("remark","%"+returnOrder.getRemark()+"%");
            }
            if (!StringUtils.isEmpty(returnOrder.getAdminId())){
                criteria.andEqualTo("adminId",returnOrder.getAdminId());
            }

        }
        return example;
    }
}
