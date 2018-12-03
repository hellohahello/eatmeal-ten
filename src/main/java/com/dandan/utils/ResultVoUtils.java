package com.dandan.utils;

import com.dandan.vo.ResultVo;

/**
 * @author 一笑奈何
 * @create 2018-11-28 18:13
 * @desc
 **/

public class ResultVoUtils {
    /*
    成功
     */
    public static ResultVo success(Object o){
        ResultVo resultVo=new ResultVo();
        resultVo.setMessage("欧克");
        resultVo.setCode(0);
        resultVo.setData(o);
        return resultVo;
    }
    /*
    成功
     */
    public static ResultVo success(){
        ResultVo resultVo=new ResultVo();
        resultVo.setMessage("欧克");
        resultVo.setCode(0);
        return null;
    }
    /*
    错误
     */
}
