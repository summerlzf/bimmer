package com.kedu.bimmer.service;

import com.kedu.bimmer.dao.UserDAO;
import com.kedu.bimmer.model.TbUser;
import com.kedu.bimmer.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

/**
 * @author Jef
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public List<TbUser> query() {
        List<TbUser> list = userDAO.query(new HashMap<>());
        for (TbUser vo : list) {
            if (!CommonUtil.isBlank(vo.getStrNickName())) {
                try {
                    vo.setStrNickName(URLDecoder.decode(vo.getStrNickName(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    //
                }
            }
        }
        return list;
    }
}
