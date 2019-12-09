package com.baizhi.monk.service;

import com.baizhi.monk.dao.AdminDao;
import com.baizhi.monk.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yumc
 */
@Service
@Transactional(rollbackFor = RuntimeException.class,propagation = Propagation.SUPPORTS)
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Override
    public String login(Admin admin) {
        String msg="登录失败";
        Admin admin1 = adminDao.selectByPrimaryKey(admin.getUsername());
        if(admin1!=null){
            if (admin1.getPassword().equals(admin.getPassword())) {
                msg="1";
            }
        }
        return msg;
    }
}
