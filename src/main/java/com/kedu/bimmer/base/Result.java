package com.kedu.bimmer.base;

/**
 * @author Jef
 */
public class Result<T> {

    private T data;

    private int code;

    private String message;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /** =================== 以下是静态方法 ==================== */

    public static <T> Result success(T data) {
        Result<T> r = new Result<>();
        r.setCode(0);
        r.setData(data);
        r.setMessage("操作成功");
        return r;
    }

    public static Result success() {
        return success(null);
    }

    public static Result fail(int code, String message) {
        Result r = new Result();
        r.setCode(code);
        r.setMessage(message);
        return r;
    }

    public static Result fail(int code) {
        return fail(code, "操作失败");
    }

    public static Result fail(String message) {
        return fail(-1, message);
    }
}
