/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vsa.domain;

/**
 *
 * @author Michael
 */
public class FacturatieGroep 
{
    private int nummer;
    private String naam;
    private String zoekcode;
    
    public FacturatieGroep(int nummer)
    {
        this.nummer = nummer;
    }
    
    public int facturatieNummer()
    {
        return this.nummer;
    }
    
    public String facturatieNaam()
    {
        return this.naam;
    }
    
    public String facturatieZoekCode()
    {
        return this.zoekcode;
    }
}
