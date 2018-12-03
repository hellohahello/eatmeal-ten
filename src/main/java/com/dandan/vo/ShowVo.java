package com.dandan.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 一笑奈何
 * @create 2018-11-29 15:06
 * @desc
 **/
@Data
public class ShowVo {
    @JsonProperty("response")
    private Integer showCode;
    @JsonProperty("message")
    private String showMessage;
    @JsonProperty("list")
    private List<ViewVo> showData;

    public ShowVo() {
    }

    public ShowVo(Integer showCode, String showMessage, List<ViewVo> showData) {
        this.showCode = showCode;
        this.showMessage = showMessage;
        this.showData = showData;
    }
}
