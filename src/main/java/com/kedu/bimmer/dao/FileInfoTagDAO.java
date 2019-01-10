package com.kedu.bimmer.dao;

import com.kedu.bimmer.model.FileInfoTag;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by liuzifeng on 2019/1/10.
 */
@Mapper
public interface FileInfoTagDAO {

	void insert(FileInfoTag vo);

	/**
	 * 根据参数可删除一条或多条记录
	 * @param vo
	 */
	void delete(FileInfoTag vo);
}
