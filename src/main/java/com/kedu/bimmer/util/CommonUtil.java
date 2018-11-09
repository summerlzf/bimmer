package com.kedu.bimmer.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author lzf
 */
public final class CommonUtil {

    /**
     * <p>检查字符串是否为null或者空串（""）</p>
     * <pre>
     * CommonUtil.isBlank(null)      = true
     * CommonUtil.isBlank("")        = true
     * CommonUtil.isBlank(" ")       = true
     * CommonUtil.isBlank("bob")     = false
     * CommonUtil.isBlank("  bob  ") = false
     * </pre>
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 检查集合是否为null或者空（0个元素）
     * @param col
     * @return
     */
    public static boolean isBlank(Collection<?> col) {
        return col == null || col.isEmpty();
    }

    /**
     * 将数组转换为{@link List}，空数组返回空的{@link ArrayList}（非null）
     * @param ary
     * @param <T>
     * @return
     */
    public static <T> List<T> asList(T... ary) {
        return ary == null || ary.length == 0 ? new ArrayList<>() : new ArrayList<>(Arrays.asList(ary));
    }

    /**
     * 将集合{@link Collection}转换为{@link List}，空集合返回空的{@link ArrayList}（非null）
     * @param <T>
     * @param col
     * @return
     */
    public static <T> List<T> asList(Collection<T> col) {
        return isBlank(col) ? new ArrayList<>() : new ArrayList<>(col);
    }

    /**
     * 将对象集合按照拼接规则拼接成字符串，类似于JavaScript中的join方法 （空集合，返回emptyValue，可指定空串或null）
     * <p>e.g. : ["1", "2", "3"] -> "1,2,3" or "1;2;3", ...
     * @param col
     * @param split
     * @param emptyValue
     * @return
     */
    public static <T> String join(Collection<T> col, String split, String emptyValue) {
        if(isBlank(col)) return emptyValue;
        StringBuilder sb = new StringBuilder();
        for(T o : col) {
            sb.append(split).append(o);
        }
        return sb.substring(split.length());
    }

    /**
     * 将对象集合按照拼接规则拼接成字符串，类似于JavaScript中的join方法 （空集合，返回null）
     * <p>e.g. : ["1", "2", "3"] -> "1,2,3" or "1;2;3", ...
     * @param col
     * @param split
     * @return
     */
    public static <T> String join(Collection<T> col, String split) {
        return join(col, split, null);
    }

    /**
     * 将对象数组按照拼接规则拼接成字符串，类似于JavaScript中的join方法 （空数组，返回emptyValue，可指定空串或null）
     * <p>e.g. : ["1", "2", "3"] -> "1,2,3" or "1;2;3", ...
     * @param ary
     * @param split
     * @param emptyValue
     * @return
     */
    public static <T> String join(T[] ary, String split, String emptyValue) {
        return ary == null || ary.length == 0 ? emptyValue : join(asList(ary), split, emptyValue);
    }

    /**
     * 将对象数组按照拼接规则拼接成字符串，类似于JavaScript中的join方法 （空数组，返回null）
     * <p>e.g. : ["1", "2", "3"] -> "1,2,3" or "1;2;3", ...
     * @param ary
     * @param split
     * @return
     */
    public static <T> String join(T[] ary, String split) {
        return join(ary, split, null);
    }

    public static int getInteger(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            //
        }
        return defaultValue;
    }

    public static long getLong(String value, long defaultValue) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            //
        }
        return defaultValue;
    }

    /**
     * 生成标准的32位MD5值
     * @param text
     * @return
     */
    public static String MD5(String text) {
        try {
            StringBuilder sb = new StringBuilder();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes(StandardCharsets.UTF_8));
            for (byte b : md.digest()) {
                int n = b;
                if(n < 0) n += 256;
                if(n < 16) sb.append("0");
                sb.append(Integer.toHexString(n));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            //
        }
        return null;
    }

    /**
     * 生成SHA-1哈希
     * @param text
     * @return
     */
    public static String SHA1(String text) {
        try {
            Formatter ft = new Formatter();
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(text.getBytes(StandardCharsets.UTF_8));
            for (byte b : md.digest()) {
                ft.format("%02x", b);
            }
            String str = ft.toString();
            ft.close();
            return str;
        } catch (NoSuchAlgorithmException e) {
            //
        }
        return null;
    }

    public static String hash(String text) {
        String s = isBlank(text) ? null : MD5(text);
        return s == null ? null : SHA1(s);
    }

    /**
     * 生成UUID
     * @return
     */
    public static String UUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 判断是否是合法的密码（合法密码：由6-20位数字、大小写字母、下划线和横线组成）
     * @param pwd
     * @return
     */
    public static boolean isLegalPassword(String pwd) {
        return pwd != null && pwd.matches("[0-9a-zA-Z_\\-]{6,20}$");
    }

    /**
     * 判断是否是手机号码（11位）
     * @param phone
     * @return
     */
    public static boolean isMobilePhone(String phone) {
        return phone != null && phone.length() == 11 && phone.matches("^[1][0-9]{10}$");
    }

    /**
     * 截取字符串长度，ASCII字符长度为1，中文字符长度为2
     * @param str
     * @param maxLength
     * @param suffix
     * @return
     */
    public static String substr(String str, int maxLength, String suffix) {
        if(isBlank(str) || maxLength <= 0) {
            return "";
        }
        int length = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));
            int len = s.getBytes(StandardCharsets.UTF_8).length == 1 ? 1 : 2;
            length += len;
            if(length > maxLength) {
                if(!isBlank(suffix)) {
                    sb.append(suffix);
                }
                break;
            }
            sb.append(s);
        }
        return sb.toString();
    }
}
