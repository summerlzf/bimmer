package com.kedu.bimmer.dto;

/**
 * Created by liuzifeng on 2018/12/20.
 */
public class FileSearchDTO {

	/** 显示文件名 */
	private String fileName;

	/** 文件类型 */
	private int fileType;

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
}
