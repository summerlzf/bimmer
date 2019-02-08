package com.kedu.bimmer.service;

import com.kedu.bimmer.dao.ArticleExtendDAO;
import com.kedu.bimmer.model.ArticleExtend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jef
 */
@Service
public class ArticleExtendService {

    @Autowired
    private ArticleExtendDAO articleExtendDAO;

    public ArticleExtend get(String articleId) {
        return articleExtendDAO.get(articleId);
    }

    public void insert(ArticleExtend vo) {
        articleExtendDAO.insert(vo);
    }

    public void save(ArticleExtend vo) {
        ArticleExtend ext = articleExtendDAO.get(vo.getArticleId());
        if (ext == null) {
            articleExtendDAO.insert(vo);
        } else {
            articleExtendDAO.update(vo);
        }
    }

    public void delete(String articleId) {
        articleExtendDAO.delete(articleId);
    }
}
