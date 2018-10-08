package com.kedu.bimmer.dto;

/**
 * 保存在cookie中的用户信息
 *
 * @author Jef
 */
public class UserBasicInfo {

    /** 用户id */
    private String userId;

    /** 用户名 */
    private String userName;

    /** 昵称 */
    private String nickName;

    /** 手机号码 */
    private String phone;

    /** E-mail */
    private String email;

    /** 是否记住 */
    private boolean remember;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }
}
