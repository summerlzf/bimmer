package com.kedu.bimmer.dao;

import com.kedu.bimmer.dto.FileInfoTagDTO;
import com.kedu.bimmer.model.FileInfoTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liuzifeng on 2019/1/10.
 */
@Mapper
public interface FileInfoTagDAO {

	List<FileInfoTagDTO> listAll();

	List<String> listByFileId(@Param("fileId") String fileId);

	void insert(FileInfoTag vo);

	void insertBatch(List<FileInfoTag> list);

	/**
	 * 根据参数可删除一条或多条记录
	 * @param vo
	 */
	void delete(FileInfoTag vo);
}
