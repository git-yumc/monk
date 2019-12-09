package com.baizhi.monk.entity;

import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author yumc
 */
public class Banner implements Serializable {
    @Id
    @KeySql(sql = "select uuid()",order = ORDER.BEFORE)
    private String id;
    private String bName;
    private String src;
    private String introduction;
    private String href;
    private Integer status;

    public Banner() {
    }

    public Banner(String id, String bName, String src, String introduction, String href, Integer status) {
        this.id = id;
        this.bName = bName;
        this.src = src;
        this.introduction = introduction;
        this.href = href;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id='" + id + '\'' +
                ", bName='" + bName + '\'' +
                ", src='" + src + '\'' +
                ", introduction='" + introduction + '\'' +
                ", href='" + href + '\'' +
                ", status=" + status +
                '}';
    }
}
