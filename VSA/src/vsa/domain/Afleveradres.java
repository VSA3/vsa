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
public class Afleveradres implements Serializable
{
    private int nummer;
    private String naam;
    private String adres;
    private String postcode;
    private String plaats;
    private String telefoon1;
    private String telefoon2;
    
    public Afleveradres(int nummer, String naam, String adres, String postcode, String plaats, String telefoon1, String telefoon2)
    {
        this.nummer = nummer;
        this.naam = naam;
        this.adres = adres;
        this.postcode = postcode;
        this.plaats = plaats;
        this.telefoon1 = telefoon1;
        this.telefoon2 = telefoon2;
    }
    
    public int getNummer()
    {
        return this.nummer;
    }
    
    public void setNummer(int nummer)
    { 
        this.nummer = nummer; 
    }
    
    public String getNaam()
    {
        return this.naam;
    }
    
    public void setNaam(String naam)
    {
        this.naam = naam;
    }
    
    public String getAdres()
    {
        return this.adres;
    }
    
    public void setAdres(String adres)
    {
        this.adres = adres;
    }
    
    public String getPostcode()
    {
        return this.postcode;
    }
    
    public void setPostcode(String postcode)
    {
        this.postcode = postcode;
    }   
}
