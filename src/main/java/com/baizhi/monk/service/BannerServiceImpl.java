package com.baizhi.monk.service;

import com.baizhi.monk.annotation.Log;
import com.baizhi.monk.dao.BannerDao;
import com.baizhi.monk.entity.Banner;
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
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;

    @Override
    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.SUPPORTS)
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        Map<String ,Object> map=new HashMap<>(16);
        List<Banner> banners = bannerDao.selectByRowBounds(new Banner(), new RowBounds((page - 1) * rows, rows));
        int records = bannerDao.selectCount(new Banner());
        int total = records%rows==0?records/rows:records/rows+1;
        map.put("rows",banners);
        map.put("page",page);
        map.put("records",records);
        map.put("total",total);
        return map;
    }

    @Override
    public List<Banner> select() {
        Banner banner = new Banner();
        banner.setStatus(1);
        return bannerDao.select(banner);
    }

    @Override
    @Log("添加轮播图")
    public void insert(Banner banner) {
        bannerDao.insert(banner);
    }

    @Override
    @Log("更新轮播图")
    public void update(Banner banner) {
        bannerDao.updateByPrimaryKeySelective(banner);
    }

    @Override
    @Log("删除轮播图")
    public void delete(Banner banner) {
        bannerDao.deleteByPrimaryKey(banner);
    }

    @Override
    @Log("批量删除轮播图")
    public void deleteList(List<String> list) {
        bannerDao.deleteByIdList(list);
    }
}
