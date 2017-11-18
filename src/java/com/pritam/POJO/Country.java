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
public class Country {

    public String Code;
    public String Name;
    public String Continent;
    public String Region;
    public float SurfaceArea;
    public int IndepYear;
    public int Population;
    public float LifeExpectancy;
    public float GNP;
    public float GNPOld;
    public String LocalName;
    public String GovernmentForm;
    public String HeadOfState;
    public int Capital;
    public String Code2;

    
    public Country(String Code) {
        this.Code = Code;
    }

    public Country(String Code, String Name, String Continent, String Region, float SurfaceArea, int IndepYear, int Population, float LifeExpectancy, float GNP, float GNPOld, String LocalName, String GovernmentForm, String HeadOfState, int Capital, String Code2) {
       this(Name, Continent, Region, SurfaceArea, IndepYear, Population, LifeExpectancy, GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2);
		this.Code = Code;
    }   
        
    public Country(String Name, String Continent, String Region, float SurfaceArea, int IndepYear, int Population, float LifeExpectancy, float GNP, float GNPOld, String LocalName, String GovernmentForm, String HeadOfState, int Capital, String Code2) {  
        this.Name = Name;
        this.Continent = Continent;
        this.Region = Region;
        this.SurfaceArea = SurfaceArea;
        this.IndepYear = IndepYear;
        this.Population = Population;
        this.LifeExpectancy = LifeExpectancy;
        this.GNP = GNP;
        this.GNPOld = GNPOld;
        this.LocalName = LocalName;
        this.GovernmentForm = GovernmentForm;
        this.HeadOfState = HeadOfState;
        this.Capital = Capital;
        this.Code2 = Code2;
    }



    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getContinent() {
        return Continent;
    }

    public void setContinent(String Continent) {
        this.Continent = Continent;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String Region) {
        this.Region = Region;
    }

    public float getSurfaceArea() {
        return SurfaceArea;
    }

    public void setSurfaceArea(float SurfaceArea) {
        this.SurfaceArea = SurfaceArea;
    }

    public int getIndepYear() {
        return IndepYear;
    }

    public void setIndepYear(int IndepYear) {
        this.IndepYear = IndepYear;
    }

    public int getPopulation() {
        return Population;
    }

    public void setPopulation(int Population) {
        this.Population = Population;
    }

    public float getLifeExpectancy() {
        return LifeExpectancy;
    }

    public void setLifeExpectancy(float LifeExpectancy) {
        this.LifeExpectancy = LifeExpectancy;
    }

    public float getGNP() {
        return GNP;
    }

    public void setGNP(float GNP) {
        this.GNP = GNP;
    }

    public float getGNPOld() {
        return GNPOld;
    }

    public void setGNPOld(float GNPOld) {
        this.GNPOld = GNPOld;
    }

    public String getLocalName() {
        return LocalName;
    }

    public void setLocalName(String LocalName) {
        this.LocalName = LocalName;
    }

    public String getGovernmentForm() {
        return GovernmentForm;
    }

    public void setGovernmentForm(String GovernmentForm) {
        this.GovernmentForm = GovernmentForm;
    }

    public String getHeadOfState() {
        return HeadOfState;
    }

    public void setHeadOfState(String HeadOfState) {
        this.HeadOfState = HeadOfState;
    }

    public int getCapital() {
        return Capital;
    }

    public void setCapital(int Capital) {
        this.Capital = Capital;
    }

    public String getCode2() {
        return Code2;
    }

    public void setCode2(String Code2) {
        this.Code2 = Code2;
    }

}
