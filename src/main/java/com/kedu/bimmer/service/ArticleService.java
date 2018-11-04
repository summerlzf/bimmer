package com.kedu.bimmer.service;

import com.kedu.bimmer.dao.ArticleDAO;
import com.kedu.bimmer.dto.ArticleDTO;
import com.kedu.bimmer.dto.ArticleSearchDTO;
import com.kedu.bimmer.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Jef
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleDAO articleDAO;

    public List<ArticleDTO> query(ArticleSearchDTO articleSearchDTO) {
        List<ArticleDTO> list = articleDAO.queryBySearch(articleSearchDTO);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        list.forEach(vo -> vo.setLastModifyTimeStr(vo.getLastModifyTime().format(dtf)));
        return list;
    }

    public void insert(Article vo) {
        articleDAO.insert(vo);
    }
}
