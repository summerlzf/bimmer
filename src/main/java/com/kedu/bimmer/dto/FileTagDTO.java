package com.kedu.bimmer.dto;

/**
 * @author Jef
 */
public class FileTagDTO {

    /** 标签id */
    private String tagId;

    /** 标签名 */
    private String tagName;

    /** 排序序号 */
    private int sortOrder;

    /** 是否属于、匹配当前文件 */
    private boolean belongToFile;

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

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public boolean isBelongToFile() {
        return belongToFile;
    }

    public void setBelongToFile(boolean belongToFile) {
        this.belongToFile = belongToFile;
    }
}
