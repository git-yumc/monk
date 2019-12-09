package com.baizhi.monk.controller;

import com.baizhi.monk.entity.Admin;
import com.baizhi.monk.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yumc
 */
@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("login")
    public String login(Admin admin, String code, HttpServletRequest req){
        String msg = "验证码错误";
        String code1 = req.getSession().getAttribute("imgCode").toString();
        if (code1.equals(code)) {
            msg=adminService.login(admin);
            if ("1".equals(msg)) {
                req.getSession().setAttribute("admin", admin);
            }
        }
        return msg;
    }
    @RequestMapping("logout")
    public void logout(HttpServletRequest req){
        if (req.getSession().getAttribute("admin") != null) {
            req.getSession().removeAttribute("admin");
        }
    }
    @RequestMapping("get")
    public String get(HttpServletRequest req){
        String name = "-1";
        Admin admin= (Admin) req.getSession().getAttribute("admin");
        if (admin != null) {
            name = admin.getUsername();
        }
        return name;
    }
}
