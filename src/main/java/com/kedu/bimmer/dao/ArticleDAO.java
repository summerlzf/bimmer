package com.kedu.bimmer.dao;

import com.kedu.bimmer.model.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Jef
 */
@Mapper
public interface ArticleDAO {

    List<Article> query(Map<String, ?> map);
}
