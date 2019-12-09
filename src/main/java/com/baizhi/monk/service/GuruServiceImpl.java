package com.baizhi.monk.service;

import com.baizhi.monk.annotation.Log;
import com.baizhi.monk.dao.GuruDao;
import com.baizhi.monk.entity.Guru;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yumc
 */
@Service
@Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.REQUIRED)
public class GuruServiceImpl implements GuruService {
    @Autowired
    private GuruDao guruDao;

    @Override
    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.SUPPORTS)
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        Map<String ,Object> map=new HashMap<>(16);
        List<Guru> gurus = guruDao.selectByRowBounds(new Guru(), new RowBounds((page - 1) * rows, rows));
        int records = guruDao.selectCount(new Guru());
        int total = records%rows==0?records/rows:records/rows+1;
        map.put("rows",gurus);
        map.put("page",page);
        map.put("records",records);
        map.put("total",total);
        return map;
    }

    @Override
    @Log("添加")
    public void insert(Guru guru) {
        guruDao.insert(guru);
    }

    @Override
    @Log("更新")
    public void update(Guru guru) {
        guruDao.updateByPrimaryKeySelective(guru);
    }

    @Override
    @Log("删除")
    public void delete(Guru guru) {
        guruDao.deleteByPrimaryKey(guru);
    }
}
