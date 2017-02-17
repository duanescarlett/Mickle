package com.example.root.mickle.models;

import com.orm.SugarRecord;

/**
 * Created by root on 2/16/17.
 */

public class Liability extends SugarRecord {
    private Long id;
    private String name;
    private Float amount;

    public Liability(){}

    public Liability(String name, Float amount){
        this.name = name;
        this.amount = amount;
    }

    public Long getId() { return this.id; }
}
