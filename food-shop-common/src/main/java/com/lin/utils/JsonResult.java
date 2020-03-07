package com.lin.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 自定义响应数据结构
 * 200：表示成功
 * 500：表示错误，错误信息在 msg 字段中
 * 501：bean 验证错误，不管多少个错误都以 map 形式返回
 * 502：拦截器拦截到用户 token 出错
 * 555：异常抛出信息
 * 556：用户 QQ 校验异常
 * @author lkmc2
 * @date 2020/3/7 22:21
 */
public class JsonResult {

    /** jackson 对象 **/
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /** 响应业务状态 **/
    private Integer status;

    /** 响应消息 **/
    private String msg;

    /** 响应中的数据 **/
    private Object data;

    @JsonIgnore
    private String ok;

    public static JsonResult build(Integer status, String msg, Object data) {
        return new JsonResult(status, msg, data);
    }

    public static JsonResult build(Integer status, String msg, Object data, String ok) {
        return new JsonResult(status, msg, data, ok);
    }

    public static JsonResult ok(Object data) {
        return new JsonResult(data);
    }

    public static JsonResult ok() {
        return new JsonResult(null);
    }

    public static JsonResult errorMsg(String msg) {
        return new JsonResult(500, msg, null);
    }

    public static JsonResult errorMap(Object data) {
        return new JsonResult(501, "error", data);
    }

    public static JsonResult errorTokenMsg(String msg) {
        return new JsonResult(502, msg, null);
    }

    public static JsonResult errorExcepiton(String msg) {
        return new JsonResult(555, msg, null);
    }

    public static JsonResult errorUserQq(String msg) {
        return new JsonResult(556, msg, null);
    }

    public JsonResult() {
    }

    public JsonResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public JsonResult(Integer status, String msg, Object data, String ok) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.ok = ok;
    }

    public JsonResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOk() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", ok='" + ok + '\'' +
                '}';
    }

}
