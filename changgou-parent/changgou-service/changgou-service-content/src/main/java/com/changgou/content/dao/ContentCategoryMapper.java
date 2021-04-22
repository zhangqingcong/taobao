package com.changgou.content.dao;
import com.changgou.contents.pojo.ContentCategory;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/****
 * @Author:admin
 * @Description:ContentCategory的Dao
 * @Date 2019/6/14 0:12
 *****/
@Repository
public interface ContentCategoryMapper extends Mapper<ContentCategory> {
}
