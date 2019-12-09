package com.baizhi.monk.service;

import com.baizhi.monk.annotation.Log;
import com.baizhi.monk.dao.ChapterDao;
import com.baizhi.monk.entity.Chapter;
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
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;

    @Override
    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.SUPPORTS)
    public Map<String, Object> selectAll(Integer page, Integer rows,String albumId) {
        Map<String ,Object> map=new HashMap<>(16);
        List<Chapter> chapters = chapterDao.selectByRowBounds(new Chapter(null, null, null, null, null, albumId), new RowBounds((page - 1) * rows, rows));
        int records = chapterDao.selectCount(new Chapter(null, null, null, null, null, albumId));
        int total = records%rows==0?records/rows:records/rows+1;
        map.put("rows",chapters);
        map.put("page",page);
        map.put("records",records);
        map.put("total",total);
        return map;
    }

    @Override
    @Log("添加章节")
    public void insert(Chapter chapter) {
        chapterDao.insert(chapter);
    }

    @Override
    @Log("更新章节")
    public void update(Chapter chapter) {
        chapterDao.updateByPrimaryKeySelective(chapter);
    }

    @Override
    @Log("删除章节")
    public void delete(Chapter chapter) {
        chapterDao.deleteByPrimaryKey(chapter);
    }
}
