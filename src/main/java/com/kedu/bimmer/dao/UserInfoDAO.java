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

    UserInfo get(String userId);

    /**
     * 根据用户名（唯一）获取用户信息
     * @param userName
     * @return
     */
    UserInfo getByUserName(String userName);

    /**
     * 根据手机号码（唯一）获取用户信息
     * @param phone
     * @return
     */
    UserInfo getByPhone(String phone);

    void insert(UserInfo vo);

    void update(UserInfo vo);

    /**
     * 更新登录时间
     * @param vo
     */
    void updateLoginTime(UserInfo vo);
}
