package com.parachute.shikabookcity.util;/*
 *项目名: shika-book-city
 *文件名: Result
 *创建者: 马驰
 *创建时间:2022/4/15 15:46
 *描述: TODO

 */

import java.io.Serializable;

/**
 * 结果
 *
 * @author machi
 * @date 2022/04/25
 */
public class Result implements Serializable {
    /**
     * 返回结果标识
     * 执行结果，true为执行成功 false为执行失败
     */
    private boolean flag;
    /**
     * 消息
     * 返回提示信息，主要用于页面提示信息
     */
    private String message;
    /**
     * 数据
     * 返回数据
     */
    private Object data;

    /**
     * 无返回数据
     *
     * @param flag    国旗
     * @param message 消息
     */
    public Result(boolean flag, String message) {
        super();
        this.flag = flag;
        this.message = message;
    }

    /**
     * 带返回数据
     *
     * @param flag    国旗
     * @param message 消息
     * @param data    数据
     */
    public Result(boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    /**无返回数据
     * @param flag    国旗
     * @param message 消息
     * @return {@link Result}
     */
    public static Result of(boolean flag, String message) {
        return new Result(flag, message);
    }

    /**带返回数据
     * @param flag    国旗
     * @param message 消息
     * @param data    数据
     * @return {@link Result}
     */
    public static Result of(boolean flag, String message, Object data) {
        return new Result(flag, message,data);
    }


    public boolean isFlag() {
        return flag;
    }


    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public Object getData() {
        return data;
    }


    public void setData(Object data) {
        this.data = data;
    }
}

