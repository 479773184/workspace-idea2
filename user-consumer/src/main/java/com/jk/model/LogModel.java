/**
 * Copyright (C), 2019, 公司
 * FileName: LogModel
 * Author:   zgm
 * Date:     2019/8/10 8:37
 * Description: a
 * History:
 * zgm          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author zgm
 * @create 2019/8/10
 * @since 1.0.0
 */
public class LogModel implements Serializable {
    private String id;
    private String name;
    private String logip;
    private String logis;
    private String requerpath;
    private String parame;
    private Integer userId;
    private Object returningValue;
    @DateTimeFormat(pattern="yyyy-MM-dd")// 处理从	前端到后端的时间
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")// 处理从	后端到前端的时间
    private Date createtime;

    @DateTimeFormat(pattern = "yyyy-MM-dd") // 处理从	前端到后端的时间
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")// 处理从	后端到前端的时间
    private Date starDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd") // 处理从	前端到后端的时间
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")// 处理从	后端到前端的时间
    private Date endDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogip() {
        return logip;
    }

    public void setLogip(String logip) {
        this.logip = logip;
    }

    public String getLogis() {
        return logis;
    }

    public void setLogis(String logis) {
        this.logis = logis;
    }

    public String getRequerpath() {
        return requerpath;
    }

    public void setRequerpath(String requerpath) {
        this.requerpath = requerpath;
    }

    public String getParame() {
        return parame;
    }

    public void setParame(String parame) {
        this.parame = parame;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Object getReturningValue() {
        return returningValue;
    }

    public void setReturningValue(Object returningValue) {
        this.returningValue = returningValue;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getStarDate() {
        return starDate;
    }

    public void setStarDate(Date starDate) {
        this.starDate = starDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "LogModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", logip='" + logip + '\'' +
                ", logis='" + logis + '\'' +
                ", requerpath='" + requerpath + '\'' +
                ", parame='" + parame + '\'' +
                ", userId=" + userId +
                ", returningValue=" + returningValue +
                ", createtime=" + createtime +
                ", starDate=" + starDate +
                ", endDate=" + endDate +
                '}';
    }
}
