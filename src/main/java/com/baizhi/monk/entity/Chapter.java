package com.baizhi.monk.entity;

import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author yumc
 */
public class Chapter implements Serializable {
    @Id
    @KeySql(sql = "select uuid()",order = ORDER.BEFORE)
    private String id;
    private String cName;
    private String size;
    private String length;
    private String src;
    private String albumId;

    public Chapter() {
    }

    public Chapter(String id, String cName, String size, String length, String src, String albumId) {
        this.id = id;
        this.cName = cName;
        this.size = size;
        this.length = length;
        this.src = src;
        this.albumId = albumId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id='" + id + '\'' +
                ", cName='" + cName + '\'' +
                ", size=" + size +
                ", length='" + length + '\'' +
                ", src='" + src + '\'' +
                ", albumId='" + albumId + '\'' +
                '}';
    }
}
