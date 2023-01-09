package com.laioffer.onlineorder.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//用来传递用户权限
@Entity
@Table(name = "authorities")//数据库里表head均为小写
public class Authorities implements Serializable {
//serializable序列化，与数据库后端有关，
    private static final long serialVersionUID = 2652327633296064143L;
    //版本1

    @Id
    private String email;

    private String authorities;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }
}
