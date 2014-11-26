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
public class Administratie 
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
}
