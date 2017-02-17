package com.example.root.mickle.models;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by root on 2/16/17.
 */

public class Budget extends SugarRecord {
    private Long id;
    private Date timeDate;
    private String name;
    private Float amount;

    public Budget(){}

    public Budget(Date timeDate, String name, Float amount){
        this.timeDate = timeDate;
        this.name = name;
        this.amount = amount;
    }
}
