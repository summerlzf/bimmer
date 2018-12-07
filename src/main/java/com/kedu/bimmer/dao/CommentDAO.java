package com.kedu.bimmer.dao;

import com.kedu.bimmer.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liuzifeng on 2018/12/7.
 */
@Mapper
public interface CommentDAO {

	List<Comment> query(@Param("articleId") String articleId);

	void insert(Comment vo);
}
