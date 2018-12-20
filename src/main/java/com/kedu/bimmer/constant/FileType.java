package com.kedu.bimmer.constant;

/**
 * Created by liuzifeng on 2018/12/20.
 */
public enum FileType {

	UNKNOWN(0, "unknown", "未知"),
	IMAGE(1, "image", "图片"),
	VIDEO(2, "video", "视频");

	private int type;
	private String typeName;
	private String note;

	FileType(int type, String typeName, String note) {
		this.type = type;
		this.typeName = typeName;
		this.note = note;
	}

	public static FileType of(int type) {
		for(FileType t : values()) {
			if (t.type == type) {
				return t;
			}
		}
		return UNKNOWN;
	}

	public int getType() {
		return type;
	}

	public String getTypeName() {
		return typeName;
	}

	public String getNote() {
		return note;
	}
}
