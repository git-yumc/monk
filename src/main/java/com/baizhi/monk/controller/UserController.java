package com.baizhi.monk.controller;

import com.baizhi.monk.entity.User;
import com.baizhi.monk.entity.UserVO;
import com.baizhi.monk.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yumcw
 * @date 2019-12-03
 */
@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("count")
    public Map<String, List<Integer>> count(){
        return userService.count();
    }

    @RequestMapping("address")
    public List<UserVO> address(){
        return userService.userAddress();
    }

    @RequestMapping("login")
    public Map<String, Object> login(String phone, String password){
        Map<String,Object> map =  userService.login(phone,password);
        return map;
    }

    @RequestMapping("register")
    public Map<String, String> register(String code, HttpSession session) {
        Map<String,String> result=new HashMap<>(2);
        Object code1 = session.getAttribute("code");
        if (true) {
            //String phone = session.getAttribute("phone").toString();
            String phone="123";
            User user = new User();
            user.setMobile(phone);
            userService.register(user);
            result.put("status", "200");
            result.put("uid", user.getId());
        } else {
            result.put("status", "-200");
            result.put("message", "注册失败");
        }
        return result;
    }

    @RequestMapping("improve")
    public Map<String, Object> improve(User user, String uid, String photo, String nick_name, Integer sign, String location) {
        Map<String,Object> result=new HashMap<>(2);
        user.setId(uid);
        user.setImg(photo);
        user.setNickname(nick_name);
        user.setStatus(sign);
        user.setAddress(location);
        user.setCreateDate(new Date());
        try {
            User update = userService.update(user);
            result.put("status", 200);
            result.put("user", update);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", -200);
            result.put("status", "更新信息失败");
        }
        return result;
    }

    @RequestMapping("select")
    public Map<String, Object> select(Integer page, Integer rows) {
        Map<String, Object> map = userService.selectAll(page, rows);
        return map;
    }

    @RequestMapping("edit")
    public Map<String, Object> edit(User user, String oper) {
        Map<String, Object> map = new HashMap<>(16);
        if (oper.equals("add")) {
            user.setId(null);
            userService.insert(user);
            map.put("id", user.getId());
        } else if (oper.equals("edit")) {
            userService.update(user);
            map.put("id", user.getId());
        } else if (oper.equals("del")) {
            userService.delete(user);
        }
        return map;
    }

    @RequestMapping("upload")
    public void upload(MultipartFile img, String id, HttpSession session, HttpServletRequest request) {
        // 获取路径
        String realPath = session.getServletContext().getRealPath("/statics/img/user");
        // 判断路径文件夹是否存在
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        // 防止重名操作
        String originalFilename = img.getOriginalFilename();
        originalFilename = id + "_" + originalFilename;
        try {
            img.transferTo(new File(realPath, originalFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setId(id);
        user.setImg(originalFilename);
        userService.update(user);
    }

}
