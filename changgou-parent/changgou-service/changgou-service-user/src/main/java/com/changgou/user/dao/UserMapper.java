package com.changgou.user.dao;
import com.changgou.user.pojo.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/****
 * @Author:admin
 * @Description:User的Dao
 * @Date 2019/6/14 0:12
 *****/
@Repository
public interface UserMapper extends Mapper<User> {
}
