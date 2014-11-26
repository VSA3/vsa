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
    private int betalingstermijn;
    private int debiteurnummeradministratie;
    private FacturatieGroep facturatiegroep;
    private ArrayList<Factuur> facturen;
    
    public Debiteur(int nummer)
    {
        this.nummer = nummer;
        this.debiteurnummeradministratie = nummer;
        facturen = new ArrayList();
    }
    
    public void setNummer(int nummer)
    {
        this.nummer = nummer;
    }
    
    public int getNummer()
    {
        return this.nummer;
    }
}
