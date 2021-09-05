package com.ua.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Patient extends Staff implements Serializable {
    private int id;
    private String telephone;
    private String passport;
    private String name;
    private String surname;
    private long years;
    private int dayBorn;
    private int monthBorn;
    private int yearBorn;
    private List<CaseRecord> caseRecords = new ArrayList<>();

    public Patient(){

    }

    public Patient(String name,String surname){
        this.name=name;
        this.surname=surname;
    }

    public long getYears() {
        return years;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setYears(long years) {
        this.years = years;
    }

    public int getDayBorn() {
        return dayBorn;
    }

    public void setDayBorn(int dayBorn) {
        this.dayBorn = dayBorn;
    }

    public int getMonthBorn() {
        return monthBorn;
    }

    public void setMonthBorn(int monthBorn) {
        this.monthBorn = monthBorn;
    }

    public int getYearBorn() {
        return yearBorn;
    }

    public void setYearBorn(int yearBorn) {
        this.yearBorn = yearBorn;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                "\n, telephone='" + telephone + '\'' +
                "\n, passport='" + passport + '\'' +
                "\n, name='" + name + '\'' +
                "\n, surname='" + surname + '\'' +
                "\n, years=" + years +
                "\n, caseRecords=" + caseRecords +
                '}' + "\n";
    }
}
