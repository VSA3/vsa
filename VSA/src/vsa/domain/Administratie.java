/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vsa.domain;

import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Michael
 */
public class Administratie extends Observable
{
    private ArrayList<Afleveradres> adressen;
    private ArrayList<Debiteur> debiteuren;
    private ArrayList<Order> orders;
    private ArrayList<Factuur> facturen;
    private ArrayList<Artikelgroep> artikelgroepen;
    
    public Administratie()
    {
        adressen = new ArrayList();
        debiteuren = new ArrayList();
        orders = new ArrayList();
        facturen = new ArrayList();
        artikelgroepen = new ArrayList();
    }
    
    public ArrayList<Afleveradres> getAlleAdressen()
    {
        return this.adressen;
    }
    
    public ArrayList<Debiteur> getAlleDebiteuren()
    {
        return this.debiteuren;
    }
    
    public boolean maakNieuweDebiteur(String naam, String plaats, String postcode, String adres, String telefoon1, int betalingstermijn)
    {
        Debiteur nieuw = new Debiteur(naam, plaats, postcode, adres, telefoon1, betalingstermijn);
        
        if(!debiteuren.contains(nieuw))
        {
            debiteuren.add(nieuw);
            return true;
        }
        else
        {
             return false;
        } 
    }
    
    public boolean verwijderDebiteur(int nummer)
    {
        for(Debiteur d : this.debiteuren)
        {
            if(nummer == d.getNummer())
            {
                debiteuren.remove(d);
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }
    
    
}
