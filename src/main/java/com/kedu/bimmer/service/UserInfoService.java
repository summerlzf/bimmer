package com.kedu.bimmer.service;

import com.kedu.bimmer.dao.UserInfoDAO;
import com.kedu.bimmer.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * @author Jef
 */
@Service
public class UserInfoService {

    @Autowired
    private UserInfoDAO userInfoDAO;

    public List<UserInfo> query() {
        return userInfoDAO.query(new HashMap<>());
    }

    public UserInfo getByUserName(String userName) {
        return userInfoDAO.getByUserName(userName);
    }

    public UserInfo getByPhone(String phone) {
        return userInfoDAO.getByPhone(phone);
    }

    public void insert(UserInfo vo) {
        userInfoDAO.insert(vo);
    }

    public void save(UserInfo vo) {
        if (userInfoDAO.get(vo.getUserId()) == null) {
            userInfoDAO.insert(vo);
        } else {
            userInfoDAO.update(vo);
        }
    }

    public void updateLoginTime(String userId) {
        UserInfo vo = new UserInfo();
        vo.setUserId(userId);
        vo.setLastLoginTime(LocalDateTime.now());
        userInfoDAO.updateLoginTime(vo);
    }
}
