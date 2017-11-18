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
public class CountryLang {

    public String CountryCode;
    public String Language;
    public String IsOfficial;
    public float Percentage;

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String CountryCode) {
        this.CountryCode = CountryCode;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String Language) {
        this.Language = Language;
    }

    public String getIsOfficial() {
        return IsOfficial;
    }

    public void setIsOfficial(String IsOfficial) {
        this.IsOfficial = IsOfficial;
    }

    public float getPercentage() {
        return Percentage;
    }

    public void setPercentage(float Percentage) {
        this.Percentage = Percentage;
    }

    public CountryLang(String CountryCode, String Language) {
        this.CountryCode = CountryCode;
        this.Language=Language;
    }

    public CountryLang(String CountryCode, String Language, String IsOfficial, float Percentage) {
        this(Language, IsOfficial, Percentage);
        this.CountryCode = CountryCode;
    }

    public CountryLang(String Language, String IsOfficial, float Percentage) {
        this.Language = Language;
        this.IsOfficial = IsOfficial;
        this.Percentage = Percentage;
    }

}
