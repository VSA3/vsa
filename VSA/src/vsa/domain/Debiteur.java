/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vsa.domain;

import java.util.ArrayList;

/**
 *
 * @author Michael
 */
public class Debiteur 
{
    private int nummer;
    private String naam;
    private String plaats;
    private String postcode;
    private String adres;
    private String telefoon1;
    private String telefoon2;
    private String zoekcode;
    private double percentagefactuurkorting;
    private int betalingstermijn;
    private int debiteurnummeradministratie;
    private FacturatieGroep facturatiegroep;
    private ArrayList<Factuur> facturen;
    private ArrayList<Order> orders;
    
    public Debiteur(int nummer, String naam, String plaats, String postcode, String adres, String telefoon1, int betalingstermijn)
    {
        this. nummer = nummer;
        this.naam = naam;
        this.plaats = plaats;
        this.postcode = postcode;
        this.adres = adres;
        this.telefoon1 = telefoon1;
        this.betalingstermijn = betalingstermijn;
        this.debiteurnummeradministratie = nummer;
        facturen = new ArrayList();
        orders = new ArrayList();
    }
        
    public String getNaam()
    {
        return this.naam;
    }
    
    public int getNummer()
    {
        return this.nummer;
    }
    
    public String getPlaats()
    {
        return this.plaats;
    }
    
    public String getTelefoon1()
    {
        return this.telefoon1;
    }
    
    public String getAdres()
    {
        return this.adres;
    }   
    
    public String getZoekcode()
    {
        return this.zoekcode;
    }
}
