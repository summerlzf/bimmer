package com.kedu.bimmer.model;

/**
 * Created by liuzifeng on 2019/1/4.
 */
public class FileTag {

	/** 标签id */
	private String tagId;

	/** 标签名 */
	private String tagName;

	/** 排序序号 */
	private int sortOrder;

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
}
