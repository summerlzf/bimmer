package com.kedu.bimmer.service;

import com.kedu.bimmer.base.FileHolder;
import com.kedu.bimmer.base.Page;
import com.kedu.bimmer.constant.FileType;
import com.kedu.bimmer.dao.FileInfoDAO;
import com.kedu.bimmer.dao.FileInfoTagDAO;
import com.kedu.bimmer.dto.FileInfoDTO;
import com.kedu.bimmer.dto.FileInfoTagDTO;
import com.kedu.bimmer.dto.FileSearchDTO;
import com.kedu.bimmer.model.FileInfo;
import com.kedu.bimmer.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by liuzifeng on 2018/12/21.
 */
@Service
public class FileInfoService {

	@Autowired
	private FileInfoDAO fileInfoDAO;
	@Autowired
	private FileInfoTagDAO fileInfoTagDAO;

	public Page<FileInfoDTO> query(FileSearchDTO fileSearchDTO, int pageNum) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Page<FileInfo> page = Page.startPage(pageNum, 10, () -> fileInfoDAO.query(fileSearchDTO));
		List<FileInfo> vos = page.getDataList();
		List<String> fileIds = vos.isEmpty() ? new ArrayList<>() : vos.stream().map(FileInfo::getFileId).collect(Collectors.toList());
		Map<String, List<String>> tagMap = new HashMap<>();
		if (fileIds.size() > 0) {
			List<FileInfoTagDTO> infoTags = fileInfoTagDAO.listByFileIds(fileIds);
			Map<String, List<FileInfoTagDTO>> map = infoTags.isEmpty() ? new HashMap<>() : infoTags.stream().collect(Collectors.groupingBy(FileInfoTagDTO::getFileId));
			map.forEach((key, val) -> {
				List<String> names = val.stream().map(FileInfoTagDTO::getTagName).collect(Collectors.toList());
				tagMap.put(key, names);
			});
		}
		List<FileInfoDTO> list = vos.isEmpty() ? new ArrayList<>() : vos.stream().map(vo -> {
			FileInfoDTO dto = new FileInfoDTO();
			dto.setFileId(vo.getFileId());
			dto.setRealName(vo.getRealName());
			dto.setFileName(vo.getFileName());
			dto.setFileType(vo.getFileType());
			FileType t = FileType.of(vo.getFileType());
			dto.setFileTypeName(t.getNote() + "（" + t.getTypeName() + "）");
			dto.setFileTagNames(tagMap.get(vo.getFileId()));
			dto.setUrl(FileHolder.getUrl(vo.getRealName(), t));
			dto.setHidden(vo.isHidden());
			dto.setCreateTimeStr(vo.getCreateTime().format(dtf));
			return dto;
		}).collect(Collectors.toList());
		Page<FileInfoDTO> p = new Page<>(pageNum, 10);
		p.setDataList(list);
		p.setTotalCount(page.getTotalCount());
		return p;
	}

	public List<FileInfoDTO> listByTagId(String tagId) {
		// 只查图片数据
		List<FileInfo> list = fileInfoDAO.listByTagId(tagId);
		return list.isEmpty() ? new ArrayList<>() : list.stream().map(vo -> {
			FileInfoDTO dto = new FileInfoDTO();
			dto.setFileId(vo.getFileId());
			dto.setRealName(vo.getRealName());
			dto.setFileName(vo.getFileName());
			dto.setUrl(FileHolder.getUrl(vo));
			return dto;
		}).collect(Collectors.toList());
	}

	public FileInfo get(String fileId) {
		return fileInfoDAO.get(fileId);
	}

	public void insert(FileInfo vo) {
		fileInfoDAO.insert(vo);
	}

	public void update(FileInfo vo) {
		fileInfoDAO.update(vo);
	}
}
