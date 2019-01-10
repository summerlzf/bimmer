package com.kedu.bimmer.service;

import com.kedu.bimmer.base.Page;
import com.kedu.bimmer.dao.FileInfoTagDAO;
import com.kedu.bimmer.dao.FileTagDAO;
import com.kedu.bimmer.dto.FileInfoTagDTO;
import com.kedu.bimmer.dto.FileTagDTO;
import com.kedu.bimmer.model.FileTag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jef
 */
@Service
public class FileTagService {

    @Autowired
    private FileTagDAO fileTagDAO;
    @Autowired
    private FileInfoTagDAO fileInfoTagDAO;

    public Page<FileTag> query(FileTag fileTag, int pageNum) {
        return Page.startPage(pageNum, 10, () -> fileTagDAO.query(fileTag));
    }

    public List<FileTagDTO> listTags(String fileId) {
        List<String> tagIds = fileInfoTagDAO.listByFileId(fileId);
        List<FileTag> list = fileTagDAO.query(new FileTag()); // 所有的标签
        return list.stream().map(vo -> {
            FileTagDTO t = new FileTagDTO();
            BeanUtils.copyProperties(vo, t);
            t.setBelongToFile(tagIds.contains(vo.getTagId()));
            return t;
        }).collect(Collectors.toList());
    }

    public FileTag get(String tagId) {
        return fileTagDAO.get(tagId);
    }

    public FileTag getByTagName(String tagName) {
        return fileTagDAO.getByTagName(tagName);
    }

    public void insert(FileTag vo) {
        fileTagDAO.insert(vo);
    }

    public void update(FileTag vo) {
        fileTagDAO.update(vo);
    }

    public void delete(String tagId) {
        fileTagDAO.delete(tagId);
    }
}
