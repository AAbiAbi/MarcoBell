package com.laioffer.onlineorder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "menuitem")
public class MenuItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    private String name;

    private String description;

    private double price;

    private String imageUrl;

    @ManyToOne//one指的是restaurant
    @JsonIgnore//from json，当我返回数据到前端时，所有数据进行JSON转化，不返回被注释的数据
    private Restaurant restaurant;
    //在返回菜单信息时，不需要order信息，所以没有onetomany指向orderitem



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
