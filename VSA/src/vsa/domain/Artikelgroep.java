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
public class Artikelgroep implements Serializable
{
    private int nummer;
    private String naam;
    private String zoekcode;
    
    public Artikelgroep(int nummer, String naam, String zoekcode)
    {
        this.nummer = nummer;
        this.naam = naam;
        this.zoekcode = zoekcode;
    } 

    public int getNummer() {
        return nummer;
    }

    public String getNaam() {
        return naam;
    }

    public String getZoekcode() {
        return zoekcode;
    }
    
    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setZoekcode(String zoekcode) {
        this.zoekcode = zoekcode;
    }
}
