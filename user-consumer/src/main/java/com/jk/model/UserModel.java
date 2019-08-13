/**
 * Copyright (C), 2019, 公司
 * FileName: UserModel
 * Author:   zgm
 * Date:     2019/8/9 10:37
 * Description: a
 * History:
 * zgm          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.model;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author zgm
 * @create 2019/8/9
 * @since 1.0.0
 */
public class UserModel implements Serializable {
    private Integer id;

    private String username;

    private String userpass;
    private String imgCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public String getImgCode() {
        return imgCode;
    }

    public void setImgCode(String imgCode) {
        this.imgCode = imgCode;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userpass='" + userpass + '\'' +
                ", imgCode='" + imgCode + '\'' +
                '}';
    }
}
