package com.kedu.bimmer.dto;

/**
 * Created by liuzifeng on 2018/12/21.
 */
public class FileInfoDTO {

	/** 文件id */
	private String fileId;

	/** 真实文件名 */
	private String realName;

	/** 显示文件名 */
	private String fileName;

	/** 文件类型 */
	private String fileTypeName;

	/** 是否隐藏 */
	private boolean hidden;

	/** 创建时间 */
	private String createTimeStr;

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

	public String getFileTypeName() {
		return fileTypeName;
	}

	public void setFileTypeName(String fileTypeName) {
		this.fileTypeName = fileTypeName;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
}
