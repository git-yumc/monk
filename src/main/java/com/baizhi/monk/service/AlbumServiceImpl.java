package com.baizhi.monk.service;

import com.baizhi.monk.annotation.Log;
import com.baizhi.monk.dao.AlbumDao;
import com.baizhi.monk.dao.ChapterDao;
import com.baizhi.monk.entity.Album;
import com.baizhi.monk.entity.Chapter;
import org.apache.ibatis.session.RowBounds;
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
public class AlbumServiceImpl implements AlbumService {
    private final AlbumDao albumDao;
    private final ChapterDao chapterDao;

    public AlbumServiceImpl(AlbumDao albumDao, ChapterDao chapterDao) {
        this.albumDao = albumDao;
        this.chapterDao = chapterDao;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.SUPPORTS)
    public Map<String, Object> selectAll(Integer page, Integer rows) {
        Map<String ,Object> map=new HashMap<>(16);
        List<Album> albums = albumDao.selectByRowBounds(new Album(), new RowBounds((page - 1) * rows, rows));
        int records = albumDao.selectCount(new Album());
        int total = records%rows==0?records/rows:records/rows+1;
        map.put("rows",albums);
        map.put("page",page);
        map.put("records",records);
        map.put("total",total);
        return map;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.SUPPORTS)
    public Album selectOne(String id) {
        Album album = new Album();
        album.setId(id);
        Album album1 = albumDao.selectByPrimaryKey(album);
        Chapter chapter = new Chapter();
        chapter.setAlbumId(id);
        album1.setChapters(chapterDao.select(chapter));
        return album1;
    }

    @Override
    @Log("添加专辑")
    public void insert(Album album) {
        albumDao.insert(album);
    }

    @Override
    @Log("更新专辑")
    public void update(Album album) {
        albumDao.updateByPrimaryKeySelective(album);
    }

    @Override
    @Log("删除专辑")
    public void delete(Album album) {
        albumDao.deleteByPrimaryKey(album);
    }

    @Override
    @Log("批量删除专辑")
    public void deleteList(List<String> list) {
        albumDao.deleteByIdList(list);
    }
}
