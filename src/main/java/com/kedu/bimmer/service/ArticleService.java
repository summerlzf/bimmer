package com.kedu.bimmer.service;

import com.kedu.bimmer.base.Page;
import com.kedu.bimmer.dao.ArticleDAO;
import com.kedu.bimmer.dto.ArticleDTO;
import com.kedu.bimmer.dto.ArticleSearchDTO;
import com.kedu.bimmer.model.Article;
import com.kedu.bimmer.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

/**
 * @author Jef
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleDAO articleDAO;

    public Page<ArticleDTO> query(ArticleSearchDTO articleSearchDTO, int pageNum) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Page<ArticleDTO> page = Page.startPage(pageNum, 10, () -> articleDAO.queryBySearch(articleSearchDTO));
        page.getDataList().forEach(vo -> {
            // 最多只显示20个ASCII字符（10个汉字）
            vo.setTitle(CommonUtil.substr(vo.getTitle(), 20, "..."));
            vo.setContent(CommonUtil.substr(vo.getContent(), 30, "..."));
            vo.setLastModifyTimeStr(vo.getLastModifyTime().format(dtf));
        });
        return page;
    }

    public Article get(String articleId) {
        return articleDAO.get(articleId);
    }

    public void insert(Article vo) {
        articleDAO.insert(vo);
    }
}
