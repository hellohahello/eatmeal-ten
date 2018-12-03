package com.dandan.vo;

import lombok.Data;

/**
 * @author 一笑奈何
 * @create 2018-11-28 15:31
 * @desc
 **/
@Data
public class ResultVo<T> {
    protected Integer code;
    protected String message;
    private T data;
}
