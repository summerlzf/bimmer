package com.kedu.bimmer.model;

import java.time.LocalDateTime;

/**
 * Created by liuzifeng on 2018/12/20.
 */
public class FileInfo {

	/** 文件id */
	private String fileId;

	/** 真实文件名 */
	private String realName;

	/** 显示文件名 */
	private String fileName;

	/** 文件类型 */
	private int fileType;

	/** 是否隐藏 */
	private boolean hidden;

	/** 创建时间 */
	private LocalDateTime createTime;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getFileType() {
		return fileType;
	}

	public void setFileType(int fileType) {
		this.fileType = fileType;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
}
