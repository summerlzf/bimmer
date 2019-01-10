package com.kedu.bimmer.dto;

/**
 * @author Jef
 */
public class FileInfoTagDTO {

    /** 文件id */
    private String fileId;

    /** 标签id */
    private String tagId;

    /** 标签名 */
    private String tagName;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
