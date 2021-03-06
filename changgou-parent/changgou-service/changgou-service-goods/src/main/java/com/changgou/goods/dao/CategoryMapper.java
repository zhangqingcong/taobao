package com.changgou.goods.dao;
import com.changgou.goods.pojo.Category;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/****
 * @Author:admin
 * @Description:Category的Dao
 * @Date 2019/6/14 0:12
 *****/
@Repository
public interface CategoryMapper extends Mapper<Category> {
}
