package com.baizhi.monk.entity;

import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author yumc
 */
public class Log implements Serializable {
    @Id
    @KeySql(sql = "select uuid()",order = ORDER.BEFORE)
    private String id;
    private String admin;
    private Date time;
    private String action;
    private String result;

    public Log() {
    }

    public Log(String id, String admin, Date time, String action, String result) {
        this.id = id;
        this.admin = admin;
        this.time = time;
        this.action = action;
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id='" + id + '\'' +
                ", admin='" + admin + '\'' +
                ", time=" + time +
                ", action='" + action + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
