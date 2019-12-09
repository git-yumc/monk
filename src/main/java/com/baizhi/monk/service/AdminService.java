package com.baizhi.monk.service;

import com.baizhi.monk.entity.Admin;

/**
 * @author yumc
 */
public interface AdminService {
    /**
     * 登录
     * @param admin
     * @return
     */
    String login(Admin admin);
}
