/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vsa.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import vsa.domain.Artikelgroep;
import vsa.enums.FactuurSoort;

/**
 *
 * @author Michael
 */
public class Factuur implements Serializable
{
    //DEBITEUR
    private Debiteur debiteur;
    
    //Vanuit de orders ga je een list van artikelen ophalen
    //en vanuit die list kan er dan een prijs berekend worden
    //dit gaat van maandag -> zondag
    private ArrayList<Order> orders;
    
    //Is het nummer van de meegegeven debiteur
    private int debiteurnummer;
    
    //FACTUURSOORT
    private FactuurSoort factuursoort;
    
    //EXTERNE FACTUUR
    private int factuurnummer;
    private Date factuurdatum;
    private double totaalbedrag;
    private Boolean afgedrukt;
   
}
