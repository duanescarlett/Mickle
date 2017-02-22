package com.example.root.mickle.models;

import com.orm.SugarRecord;

/**
 * Created by root on 2/16/17.
 */

public class Account extends SugarRecord {
    private Long id;
    private String name;
    private String type;
    private Float amount;

    public Account(){}

    public Account(String name, String type, Float amount){
        this.name = name;
        this.type = type;
        this.amount = amount;
    }

    public Long getId() { return this.id; }

    public String getName() { return this.name; }

    public String getType() { return this.type; }

    public Float getAmount() { return this.amount; }
}
