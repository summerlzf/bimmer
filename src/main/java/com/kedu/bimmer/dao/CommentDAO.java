package com.kedu.bimmer.dao;

import com.kedu.bimmer.dto.CommentDTO;
import com.kedu.bimmer.dto.CommentSearchDTO;
import com.kedu.bimmer.model.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by liuzifeng on 2018/12/7.
 */
@Mapper
public interface CommentDAO {

	List<CommentDTO> queryBySearch(CommentSearchDTO commentSearchDTO);

	void insert(Comment vo);
}
