package com.kedu.bimmer.service;

import com.kedu.bimmer.base.Page;
import com.kedu.bimmer.dao.CommentDAO;
import com.kedu.bimmer.dto.CommentDTO;
import com.kedu.bimmer.dto.CommentSearchDTO;
import com.kedu.bimmer.model.Comment;
import com.kedu.bimmer.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Jef
 */
@Service
public class CommentService {

    @Autowired
    private CommentDAO commentDAO;

    public Page<CommentDTO> query(CommentSearchDTO commentSearchDTO, int pageNum) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Page<CommentDTO> page = Page.startPage(pageNum, 10, () -> commentDAO.queryBySearch(commentSearchDTO));
        page.getDataList().forEach(vo -> {
            vo.setContent(CommonUtil.substr(vo.getContent(), 30, "..."));
            vo.setArticleTitle(CommonUtil.substr(vo.getArticleTitle(), 20, "..."));
            vo.setCreateTimeStr(vo.getCreateTime().format(dtf));
        });
        return page;
    }

    public List<CommentDTO> listByArticleId(String articleId) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Page<CommentDTO> page = Page.startPage(1, 5, () -> commentDAO.listByArticleId(articleId));
        List<CommentDTO> list = page.getDataList();
        if (CommonUtil.isBlank(list)) {
            return new ArrayList<>();
        }
        List<String> ids = list.stream().map(CommentDTO::getCommentId).collect(Collectors.toList());
        List<CommentDTO> replyComments = commentDAO.listByReplyCommentId(ids);
        replyComments.forEach(vo -> {
            vo.setContents(CommonUtil.asList(vo.getContent().split("\r\n")));
            vo.setCreateTimeStr(vo.getCreateTime().format(dtf));
        });
        Map<String, List<CommentDTO>> map = replyComments.isEmpty() ? new HashMap<>() : replyComments.stream().collect(Collectors.groupingBy(CommentDTO::getReplyCommentId));
        list.forEach(vo -> {
            vo.setContents(CommonUtil.asList(vo.getContent().split("\r\n")));
            vo.setCreateTimeStr(vo.getCreateTime().format(dtf));
            // 拼装某一评论下的回复评论
            vo.setReplyComments(Optional.ofNullable(map.get(vo.getCommentId())).orElse(new ArrayList<>()));
        });
        return list;
    }

    public void insert(Comment vo) {
        commentDAO.insert(vo);
    }

    public void updateHidden(Comment vo) {
        commentDAO.updateHidden(vo);
    }
}
