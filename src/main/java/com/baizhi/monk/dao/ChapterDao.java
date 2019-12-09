package com.baizhi.monk.dao;

import com.baizhi.monk.entity.Chapter;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author yumc
 */
public interface ChapterDao  extends Mapper<Chapter>, DeleteByIdListMapper<Chapter,String> {
}
