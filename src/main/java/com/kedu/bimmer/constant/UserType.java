package com.kedu.bimmer.constant;

/**
 * @author Jef
 */
public enum UserType {

    NORMAL(0, "普通用户"),
    MEMBER(1, "会员用户"),
    VIP(2, "超级会员");

    private int code;
    private String note;

    UserType(int code, String note) {
        this.code = code;
        this.note = note;
    }
}
