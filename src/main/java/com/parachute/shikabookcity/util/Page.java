package com.parachute.shikabookcity.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 页面
 *
 * @author machi
 * @date 2022/04/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {
    /**
     * 每页大小
     */
    private int size;

    /**
     * 当前页
     */
    private int current;

    /**
     * 总计
     */
    private int total;

    /**
     * 用户名
     */
    private String userName;
}
