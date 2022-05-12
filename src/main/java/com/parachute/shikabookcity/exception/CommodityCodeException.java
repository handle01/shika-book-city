package com.parachute.shikabookcity.exception;

/**
 * 商品编码异常
 *
 * @author machi
 * @date 2022/05/12
 */
public class CommodityCodeException extends RuntimeException{

    public CommodityCodeException(String message) {
        super(message);
    }
}
