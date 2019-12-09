package com.baizhi.monk.service;

import com.baizhi.monk.dao.UserDao;
import com.baizhi.monk.entity.User;
import com.baizhi.monk.entity.UserVO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author yumcw
 * @date 2019-12-02
 */
@Service
@Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final HttpSession session;
    public UserServiceImpl(UserDao userDao, HttpSession session) {
        this.userDao = userDao;
        this.session = session;
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.SUPPORTS)
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        Map<String ,Object> map=new HashMap<>(16);
        List<User> users = userDao.selectByRowBounds(new User(), new RowBounds((page - 1) * rows, rows));
        int records = userDao.selectCount(new User());
        int total = records%rows==0?records/rows:records/rows+1;
        map.put("rows",users);
        map.put("page",page);
        map.put("records",records);
        map.put("total",total);
        return map;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.SUPPORTS)
    public Map<String, List<Integer>> count() {
        Map<String, List<Integer>> map = new HashMap<>();
        ArrayList<Integer> list1=new ArrayList<>();
        ArrayList<Integer> list2=new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        list1.add(userDao.countNumber("男", calendar.getTime()));
        list2.add(userDao.countNumber("女", calendar.getTime()));
        calendar.add(Calendar.DATE, -6);
        list2.add(userDao.countNumber("女", calendar.getTime()));
        list1.add(userDao.countNumber("男", calendar.getTime()));
        calendar.add(Calendar.DATE, -23);
        list2.add(userDao.countNumber("女", calendar.getTime()));
        list1.add(userDao.countNumber("男", calendar.getTime()));
        calendar.add(Calendar.DATE, -335);
        list1.add(userDao.countNumber("男", calendar.getTime()));
        list2.add(userDao.countNumber("女", calendar.getTime()));
        map.put("man", list1);
        map.put("woman", list2);
        return map;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.SUPPORTS)
    public List<UserVO> userAddress() {
        return userDao.userAddress();
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public User update(User user) {
        userDao.updateByPrimaryKeySelective(user);
        return userDao.selectByPrimaryKey(user);
    }

    @Override
    public void delete(User user) {
        userDao.deleteByPrimaryKey(user);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.SUPPORTS)
    public Map<String, Object> login(String phone, String password) {
        Map<String, Object> result=new HashMap<>(4);
        User user = new User();
        user.setMobile(phone);
        user.setPassword(password);
        User selectOne = userDao.selectOne(user);
        if (selectOne != null) {
            session.setAttribute("user", selectOne);
            result.put("status", 200);
            result.put("user", selectOne);
        } else {
            result.put("status", -200);
            result.put("message", "手机号或密码错误");
        }
        return result;
    }

    @Override
    public void register(User user) {
        userDao.insert(user);
    }
}
