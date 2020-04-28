package com.zhou.common.result;


import lombok.Data;

import java.io.Serializable;

/**
 * @description 统一返回
 * @author zhouhoujun
 * @date 2019/9/20
 */
@Data
public class ZhResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final int SUCCESS=200;

    private static String MSG_SUCCESS="操作成功";

    private static int FAIL=500;

    private static String MSG_FAIL="操作失败";

    private int code;

    private String msg;

    private T data;

    //分页信息
    private Object meta;

    public static <T> ZhResult<T> ok() {
        return restResult(null, SUCCESS, MSG_SUCCESS, null);
    }
    public static <T> ZhResult<T> okMsg(String msg) {
        return restResult(null, SUCCESS, msg, null);
    }
    public static <T> ZhResult<T> ok(T data) {
        return restResult(data, SUCCESS, MSG_SUCCESS, null);
    }

    public static <T> ZhResult<T> ok(T data, String msg) {
        return restResult(data, SUCCESS, msg, null);
    }
    public static <T> ZhResult<T> okMeta(T data) {
        return restResult(data, SUCCESS, null, data);
    }
    public static <T> ZhResult<T> ok(T data, String msg, Object meta) {
        return restResult(data, SUCCESS, msg, meta);
    }

    public static <T> ZhResult<T> failed() {
        return restResult(null, FAIL, MSG_FAIL, null);
    }

    public static <T> ZhResult<T> failedMsg(String msg) {
        return restResult(null, FAIL, msg, null);
    }

    public static <T> ZhResult<T> failedCodeMsg(int code,String msg) {
        return restResult(null, code, msg, null);
    }

    public static <T> ZhResult<T> failed(T data) {
        return restResult(data, FAIL, MSG_FAIL, null);
    }
    public static <T> ZhResult<T> failedCodeData(int code,T data) {
        return restResult(data, code, MSG_FAIL, null);
    }

    public static <T> ZhResult<T> failed(T data, String msg) {
        return restResult(data, FAIL, msg, null);
    }

    private static <T> ZhResult<T> restResult(T data, int code, String msg, Object meta) {
        ZhResult fwcloudResult = new ZhResult();
        fwcloudResult.setCode(code);
        fwcloudResult.setData(data);
        fwcloudResult.setMsg(msg);
        fwcloudResult.setMeta(meta);
        return fwcloudResult;
    }




}
