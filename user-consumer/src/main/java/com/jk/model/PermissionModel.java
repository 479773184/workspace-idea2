/**
 * Copyright (C), 2019, 公司
 * FileName: PermissionModel
 * Author:   zgm
 * Date:     2019/8/9 10:33
 * Description: a
 * History:
 * zgm          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author zgm
 * @create 2019/8/9
 * @since 1.0.0
 */
public class PermissionModel implements Serializable {
    private Integer id;

    private String name;

    private Integer pid;

    private String text;

    private String url;

    private String state;

    private String description;

    private String checked;

    private Map<String, Object> attributes = new HashMap<String, Object>(); // 添加到节点的自定义属性

    private List<PermissionModel> children; //子节点数据

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public List<PermissionModel> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionModel> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "PermissionModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", text='" + text + '\'' +
                ", url='" + url + '\'' +
                ", state='" + state + '\'' +
                ", description='" + description + '\'' +
                ", checked='" + checked + '\'' +
                ", attributes=" + attributes +
                ", children=" + children +
                '}';
    }
}
