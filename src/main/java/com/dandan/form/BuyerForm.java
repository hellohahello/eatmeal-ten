package com.dandan.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author 一笑奈何
 * @create 2018-11-29 11:06
 * @desc
 **/
@Data
public class BuyerForm {
    @NotEmpty(message = "项目不能为空")
    private String name;
@NotEmpty(message = "手机号不能为空")
    private String phone;
@NotEmpty(message = "地址不能为空")
    private String address;
@NotEmpty(message = "openid不能为空")
    private String openid;
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
