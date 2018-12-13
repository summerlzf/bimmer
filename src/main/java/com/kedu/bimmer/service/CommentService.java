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
import java.util.List;

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
        page.getDataList().forEach(vo -> {
            vo.setContents(CommonUtil.asList(vo.getContent().split("\r\n")));
            vo.setCreateTimeStr(vo.getCreateTime().format(dtf));
        });
        return page.getDataList();
    }

    public void insert(Comment vo) {
        commentDAO.insert(vo);
    }

    public void updateHidden(Comment vo) {
        commentDAO.updateHidden(vo);
    }
}
