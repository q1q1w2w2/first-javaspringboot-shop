package com.apple.shop;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Setter
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer price;

    public Long getId() { return id; }
    public String getTitle() {
        return title;
    }
    public Integer getPrice() {
        return price;
    }

    public Item(){

    }
    public Item(String title, Integer price){
        this.title = title;
        this.price = price;
    }
}