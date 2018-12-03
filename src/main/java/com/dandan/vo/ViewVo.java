package com.dandan.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author 一笑奈何
 * @create 2018-11-29 15:08
 * @desc
 **/
@Data
public class ViewVo {
    @JsonProperty("name")
    private String personName;
    @JsonProperty("address")
    private String personAddress;
}
