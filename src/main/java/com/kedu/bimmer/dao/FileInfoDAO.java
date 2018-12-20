package com.kedu.bimmer.dao;

import com.kedu.bimmer.dto.FileSearchDTO;
import com.kedu.bimmer.model.FileInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liuzifeng on 2018/12/20.
 */
@Mapper
public interface FileInfoDAO {

	List<FileInfo> query(FileSearchDTO fileSearchDTO);

	FileInfo get(@Param("fileId") String fileId);

	void insert(FileInfo vo);

	void update(FileInfo vo);
}
