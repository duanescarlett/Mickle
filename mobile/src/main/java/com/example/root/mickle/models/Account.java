package com.example.root.mickle.models;

import com.orm.SugarRecord;

/**
 * Created by root on 2/16/17.
 */

public class Account extends SugarRecord {
    private Long id;
    private String name;
    private String type;
    private Long amount;

    public Account(){}

    public Account(String name, String type, Long amount){
        this.name = name;
        this.type = type;
        this.amount = amount;
    }

    public Long getId() { return this.id; }
}
