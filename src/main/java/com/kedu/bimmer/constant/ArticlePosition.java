package com.kedu.bimmer.constant;

import com.kedu.bimmer.util.CommonUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章所在位置
 *
 * @author Jef
 */
public enum ArticlePosition {

    INDEX_BANNER("index-banner", "首页Banner轮播图"),
    INDEX_MIDDLE_NAV("index-middle-nav", "首页中间导航"),
    UNKNOWN("unknown", "未知");

    private String name;
    private String note;

    ArticlePosition(String name, String note) {
        this.name = name;
        this.note = note;
    }

    public static List<ArticlePosition> asList() {
        // 排除 unknown
        return CommonUtil.asList(values()).stream().filter(v -> v != UNKNOWN).collect(Collectors.toList());
    }

    public static boolean exist(String position) {
        for(ArticlePosition p : values()) {
            if (p.name.equals(position)) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }
}
