package com.baizhi.monk.dao;

import com.baizhi.monk.entity.Banner;
import tk.mybatis.mapper.additional.idlist.DeleteByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author yumc
 */
public interface BannerDao extends Mapper<Banner>, DeleteByIdListMapper<Banner,String> {
}
