package com.kedu.bimmer.service;

import com.kedu.bimmer.dao.ArticleDAO;
import com.kedu.bimmer.dto.ArticleSearchDTO;
import com.kedu.bimmer.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jef
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleDAO articleDAO;

    public List<Article> query(ArticleSearchDTO articleSearchDTO) {
        return articleDAO.query(articleSearchDTO);
    }

    public void insert(Article vo) {
        articleDAO.insert(vo);
    }
}
