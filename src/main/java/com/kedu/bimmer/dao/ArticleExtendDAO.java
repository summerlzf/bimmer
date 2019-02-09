package com.kedu.bimmer.dao;

import com.kedu.bimmer.model.ArticleExtend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jef
 */
@Mapper
public interface ArticleExtendDAO {

    List<ArticleExtend> listByPosition(@Param("position") String position);

    ArticleExtend get(@Param("articleId") String articleId);

    void insert(ArticleExtend vo);

    void update(ArticleExtend vo);

    void delete(@Param("articleId") String articleId);
}
