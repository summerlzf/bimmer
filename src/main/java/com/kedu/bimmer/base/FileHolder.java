package com.kedu.bimmer.base;

import com.kedu.bimmer.constant.FileType;

/**
 * @author Jef
 */
public class FileHolder {

    private static final String fileURL = "http://file.bimmer.com:8888";

    public static String getUrl(String realName, FileType type) {
        if (type == FileType.UNKNOWN) {
            return "";
        }
        return fileURL + "/" + type.getTypeName() + "/" + realName;
    }
}
