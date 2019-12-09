package com.baizhi.monk.entity;

import java.io.Serializable;

/**
 * @author yumcw
 * @date 2019-12-02
 */
public class UserVO implements Serializable {
    private String name;
    private Integer value;

    public UserVO() {
    }

    public UserVO(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
