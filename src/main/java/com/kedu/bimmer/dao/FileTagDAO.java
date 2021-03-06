package com.kedu.bimmer.dao;

import com.kedu.bimmer.model.FileTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jef
 */
@Mapper
public interface FileTagDAO {

    List<FileTag> query(FileTag vo);

    FileTag get(@Param("tagId") String tagId);

    FileTag getByTagName(@Param("tagName") String tagName);

    void insert(FileTag vo);

    void update(FileTag vo);

    void delete(@Param("tagId") String tagId);
}
