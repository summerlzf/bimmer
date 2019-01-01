package com.kedu.bimmer.base;

import com.kedu.bimmer.constant.FileType;
import com.kedu.bimmer.model.FileInfo;

/**
 * @author Jef
 */
public class FileHolder {

    private static final String fileURL = "http://file.bimmer.com:8888";

    public static String getUrl(FileInfo vo) {
        return getUrl(vo.getRealName(), FileType.of(vo.getFileType()));
    }

    public static String getUrl(String realName, FileType type) {
        if (type == FileType.UNKNOWN) {
            return "";
        }
        return fileURL + "/" + type.getTypeName() + "/" + realName;
    }

    public static String getFilePath(FileType type) {
        return fileURL + "/" + type.getTypeName();
    }
}
