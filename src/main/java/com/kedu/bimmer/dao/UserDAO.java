package com.kedu.bimmer.dao;

import com.kedu.bimmer.model.TbUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDAO {
	
	/**
	 * 根据搜索条件返回列表
	 * @param map
	 * @return
	 */
	List<TbUser> query(Map<String, ?> map);

}
