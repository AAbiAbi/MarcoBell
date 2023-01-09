package com.laioffer.onlineorder.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {
    @Id
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private boolean enabled;
    @OneToOne(cascade = CascadeType.ALL)//即连，save状态同步
    //理论上是两侧都可以。但是注意要考虑null情况
    //delete时即连
    //创建一个foreign key，但是注意此时的foreign key是允许duplicate的
    @JoinColumn(unique = true)
    //约束foreign key保持不重复属性，因为一对一，但是default是false的

    private Cart cart;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return  enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
