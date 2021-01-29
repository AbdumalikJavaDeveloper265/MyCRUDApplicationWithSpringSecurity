package com.example.website2.domain;

import sun.plugin2.message.Message;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class UserData {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    private String firstName;

    private String lastName;

    private String date;

    private String region;

    private int year;

    public UserData() {
    }

    public UserData(String firstName, String lastName, String date, String region, int year, Integer id) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
        this.region = region;
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}