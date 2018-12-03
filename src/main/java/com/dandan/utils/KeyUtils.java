package com.dandan.utils;

import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * @author 一笑奈何
 * @create 2018-11-28 19:18
 * @desc 生成主键的工具
 **/
@Transactional
public class KeyUtils {
    public synchronized  static  String  getKey(){
        Random random=new Random();
        Integer s=random.nextInt(900000)+100000;
        return String.valueOf(s+System.currentTimeMillis());
    }
}
