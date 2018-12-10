package com.kedu.bimmer.service;

import com.kedu.bimmer.base.Page;
import com.kedu.bimmer.dao.CommentDAO;
import com.kedu.bimmer.dto.CommentDTO;
import com.kedu.bimmer.dto.CommentSearchDTO;
import com.kedu.bimmer.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

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
        page.getDataList().forEach(vo -> vo.setCreateTimeStr(vo.getCreateTime().format(dtf)));
        return page;
    }

    public void insert(Comment vo) {
        commentDAO.insert(vo);
    }
}
