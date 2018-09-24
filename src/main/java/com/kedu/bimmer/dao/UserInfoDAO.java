package com.kedu.bimmer.dao;

import com.kedu.bimmer.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Jef
 */
@Mapper
public interface UserInfoDAO {

    List<UserInfo> query(Map<String, ?> map);
}
