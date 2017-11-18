/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pritam.POJO;

/**
 *
 * @author Pritam
 */
public class City {

    private int ID;
    private String Name;
    private String CountryCode;
    private String District;
    private int Population;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String CountryCode) {
        this.CountryCode = CountryCode;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String District) {
        this.District = District;
    }

    public int getPopulation() {
        return Population;
    }

    public void setPopulation(int Population) {
        this.Population = Population;
    }

   

    public City(int ID) {
        this.ID = ID;
    }


    public City(int ID, String Name, String CountryCode,  String District, int Population) {
        this( Name, CountryCode, District, Population);
        this.ID = ID;
    }

    public City(String Name, String CountryCode, String District, int Population) {
        this.Name = Name;
        this.CountryCode = CountryCode;
        this.District = District;
        this.Population = Population;
    }
}
