/**
 * Copyright (C), 2019, 公司
 * FileName: RolePermissModel
 * Author:   zgm
 * Date:     2019/8/9 10:43
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
public class RolePermissModel implements Serializable {
    private Integer id;

    private Integer permissionId;

    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "RolePermissModel{" +
                "id=" + id +
                ", permissionId=" + permissionId +
                ", roleId=" + roleId +
                '}';
    }

}
