package com.kedu.bimmer.dao;

import com.kedu.bimmer.dto.ArticleDTO;
import com.kedu.bimmer.dto.ArticleSearchDTO;
import com.kedu.bimmer.model.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jef
 */
@Mapper
public interface ArticleDAO {

    List<ArticleDTO> queryBySearch(ArticleSearchDTO articleSearchDTO);

    List<Article> queryPopular(@Param("limit") int limit);

    Article get(@Param("articleId") String articleId);

    void insert(Article vo);

    void update(Article vo);

    void updateViewCount(@Param("articleId") String articleId);
}
