package com.kedu.bimmer.service;

import com.kedu.bimmer.base.Page;
import com.kedu.bimmer.dao.FileTagDAO;
import com.kedu.bimmer.model.FileTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jef
 */
@Service
public class FileTagService {

    @Autowired
    private FileTagDAO fileTagDAO;

    public Page<FileTag> query(FileTag fileTag, int pageNum) {
        return Page.startPage(pageNum, 10, () -> fileTagDAO.query(fileTag));
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
