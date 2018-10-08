package com.kedu.bimmer.base;

import com.kedu.bimmer.dto.UserBasicInfo;

/**
 * @author Jef
 */
public class SystemContext {

    private static final ThreadLocal<UserBasicInfo> userHolder = new ThreadLocal<>();

    public static UserBasicInfo getUser() {
        return userHolder.get();
    }

    public static void setUser(UserBasicInfo user) {
        userHolder.set(user);
    }

    public static void clearUser() {
        userHolder.remove();
    }
}
