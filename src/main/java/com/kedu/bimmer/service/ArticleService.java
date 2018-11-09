package com.kedu.bimmer.service;

import com.github.pagehelper.PageHelper;
import com.kedu.bimmer.base.Page;
import com.kedu.bimmer.dao.ArticleDAO;
import com.kedu.bimmer.dto.ArticleDTO;
import com.kedu.bimmer.dto.ArticleSearchDTO;
import com.kedu.bimmer.model.Article;
import com.kedu.bimmer.util.CommonUtil;
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

    public Page<ArticleDTO> query(ArticleSearchDTO articleSearchDTO, int pageNum) {
		com.github.pagehelper.Page<ArticleDTO> pg = PageHelper.startPage(pageNum, 10);
		List<ArticleDTO> list = articleDAO.queryBySearch(articleSearchDTO);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        list.forEach(vo -> {
        	// 最多只显示20个ASCII字符（10个汉字）
			vo.setTitle(CommonUtil.substr(vo.getTitle(), 20, "..."));
        	vo.setContent(CommonUtil.substr(vo.getContent(), 30, "..."));
        	vo.setLastModifyTimeStr(vo.getLastModifyTime().format(dtf));
		});
		Page<ArticleDTO> page = new Page<>(pageNum, 10);
		page.setDataList(list);
		page.setTotalCount((int) pg.getTotal());
        return page;
    }

    public void insert(Article vo) {
        articleDAO.insert(vo);
    }
}
