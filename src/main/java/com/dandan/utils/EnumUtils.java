package com.dandan.utils;

import com.dandan.Enum.CodeEnum;
import lombok.Data;

/**
 * @author 一笑奈何
 * @create 2018-11-29 21:07
 * @desc
 **/
@Data
public class EnumUtils {
    public static <T extends CodeEnum> T getMessage(Integer code, Class<T> enumClass){
        /*
        通过反射获取所有对象
         */
        for (T t:enumClass.getEnumConstants()){
            if (code.equals(t.getCode())){
                return t;
            }
        }
        return null;
    }
}
