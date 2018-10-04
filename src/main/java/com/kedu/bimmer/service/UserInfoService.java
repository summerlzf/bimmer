package com.kedu.bimmer.service;

import com.kedu.bimmer.dao.UserInfoDAO;
import com.kedu.bimmer.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
