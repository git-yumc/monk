package com.baizhi.monk.service;

import com.baizhi.monk.entity.User;
import com.baizhi.monk.entity.UserVO;

import java.util.List;
import java.util.Map;

/**
 * @author yumcw
 */
public interface UserService {
    Map<String, Object> selectAll(Integer page, Integer rows);

    Map<String, List<Integer>> count();

    List<UserVO> userAddress();

    void insert(User user);

    User update(User user);

    void delete(User user);

    Map<String, Object> login(String phone, String password);

    void register(User user);
}
