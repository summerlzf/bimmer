package com.kedu.bimmer;

import com.kedu.bimmer.base.GUID;
import com.kedu.bimmer.dto.UserBasicInfo;
import com.kedu.bimmer.util.JSONUtils;
import org.junit.Test;

/**
 * @author Jef
 */
public class JsonTest {

    @Test
    public void test() {
        UserBasicInfo vo = new UserBasicInfo();
        vo.setUserId(GUID.generate());
        vo.setUserName("jef.liu");
        vo.setNickName("summer");
        vo.setPhone("18922980909");
        vo.setEmail("123@126.com");
        System.out.println(JSONUtils.toJson(vo));
    }

    @Test
    public void test1() {
        String json = "{\"userId\":\"PzqVT5Z0pzha5oYy\",\"userName\":\"jef.liu\",\"nickName\":\"summer\",\"phone\":\"18922980909\",\"email\":\"123@126.com\"}";
        UserBasicInfo vo = JSONUtils.fromJson(json, UserBasicInfo.class);
        System.out.println(vo.getUserId());
        System.out.println(vo.getUserName());
    }
}
