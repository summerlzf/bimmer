package com.kedu.bimmer.base;

/**
 * @author Jef
 */
public class FileHolder {

    private static final String fileURL = "http://file.bimmer.com:8888";

    public static String getUrl(String realName) {
        return fileURL + "/image/" + realName;
    }
}
