package com.example.root.mickle.models;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by root on 2/16/17.
 */
@Table
public class Profile extends SugarRecord {
    private Long id;
    private String name;
    private Float income;

    public Profile(){}

    public Profile(String name, Float income){
        this.name = name;
        this.income = income;
    }

    public Long getId(){
        return this.id;
    }

    public String getName() { return this.name; }

    public Float getIncome() { return this.income; }

}
