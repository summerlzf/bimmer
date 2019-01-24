package com.kedu.bimmer.service;

import com.kedu.bimmer.base.GUID;
import com.kedu.bimmer.base.Page;
import com.kedu.bimmer.dao.FileInfoTagDAO;
import com.kedu.bimmer.dao.FileTagDAO;
import com.kedu.bimmer.dto.FileTagDTO;
import com.kedu.bimmer.model.FileInfoTag;
import com.kedu.bimmer.model.FileTag;
import com.kedu.bimmer.util.CommonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<FileTag> listAll() {
        return fileTagDAO.query(new FileTag());
    }

    public List<FileTagDTO> listTags(String fileId) {
        List<String> tagIds = GUID.isGUID(fileId) ? fileInfoTagDAO.listByFileId(fileId) : new ArrayList<>();
        List<FileTag> list = fileTagDAO.query(new FileTag()); // 所有的标签
        return list.isEmpty() ? new ArrayList<>() : list.stream().map(vo -> {
            FileTagDTO t = new FileTagDTO();
            BeanUtils.copyProperties(vo, t);
            // 文件标签是否属于当前文件
            t.setBelongToFile(tagIds.contains(vo.getTagId()));
            return t;
        }).collect(Collectors.toList());
    }

    public List<String> listByTagId(String tagId) {
        return fileInfoTagDAO.listByTagId(tagId);
    }

    public FileTag get(String tagId) {
        return fileTagDAO.get(tagId);
    }

    public FileTag getByTagName(String tagName) {
        return fileTagDAO.getByTagName(tagName);
    }

    public void saveFileTags(String fileId, String[] tagIds) {
        FileInfoTag q = new FileInfoTag();
        q.setFileId(fileId);
        fileInfoTagDAO.delete(q); // 先将文件信息-标签关系信息删除
        List<FileInfoTag> list = tagIds.length == 0 ? new ArrayList<>() : CommonUtil.asList(tagIds).stream().filter(GUID::isGUID).map(tid -> {
            FileInfoTag vo = new FileInfoTag();
            vo.setFileId(fileId);
            vo.setTagId(tid);
            return vo;
        }).collect(Collectors.toList());
        if (list.size() > 0) {
            // 批量插入文件信息-标签关系表
            fileInfoTagDAO.insertBatch(list);
        }
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
