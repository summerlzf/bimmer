package com.kedu.bimmer.controller;

import com.kedu.bimmer.base.GUID;
import com.kedu.bimmer.base.Result;
import com.kedu.bimmer.model.Comment;
import com.kedu.bimmer.service.CommentService;
import com.kedu.bimmer.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jef
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment/addComment")
    @ResponseBody
    public Result add(Comment vo) {
        if (CommonUtil.isBlank(vo.getContent())) {
            return Result.fail("请输入评论内容");
        }
        vo.setCommentId(GUID.generate());
        return Result.success();
    }
}