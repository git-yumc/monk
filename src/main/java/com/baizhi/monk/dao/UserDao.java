package com.baizhi.monk.dao;

import com.baizhi.monk.entity.User;
import com.baizhi.monk.entity.UserVO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @author yumcw
 */
public interface UserDao extends Mapper<User> {
    Integer countNumber(@Param("sex") String sex, @Param("day") Date day);

    List<UserVO> userAddress();
}
