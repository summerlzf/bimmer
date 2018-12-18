package com.kedu.bimmer;

import com.kedu.bimmer.base.GUID;
import com.kedu.bimmer.dto.UserBasicInfo;
import com.kedu.bimmer.util.CommonUtil;
import com.kedu.bimmer.util.JSONUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void test2() {
        String str = "ccc[image]https://mmbiz.qpic.cn/mmbiz_jpg/5jBZRtmtTbfBWGhNNNc[/image]789";
		System.out.println(sperate(str));
	}

	private List<String> sperate(String str) {
		int start = str.indexOf("[image]"), end = str.indexOf("[/image]");
		if (start == -1 || end == -1) {
			return CommonUtil.asList(str);
		}
		List<String> list = new ArrayList<>();
		if (start > 0) {
			list.add(str.substring(0, start));
		}
		list.add("<img src=\"" + str.substring(start + 7, end) + "\" />");
		if (end + 8 < str.length()) {
			list.add(str.substring(end + 8));
		}
		return list;
	}
}
