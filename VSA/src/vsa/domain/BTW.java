/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsa.domain;

import java.io.Serializable;

/**
 *
 * @author Michael
 */
public class BTW implements Serializable {

    private String omschrijving;
    private int percentage;
    private String naam;

    public BTW(String omschrijving, int percentage, String zoekterm) {
        this.omschrijving = omschrijving;
        this.percentage = percentage;
        this.naam = zoekterm;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public int getPercentage() {
        return percentage;
    }

    public String getNaam() {
        return naam;
    }
    
    
    @Override
    public String toString()
    {
        return this.naam + " Percentage: " + this.percentage;
    }
}
