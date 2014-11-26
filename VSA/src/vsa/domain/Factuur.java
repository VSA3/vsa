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
    
    //FACTUURSOORT
    private FactuurSoort factuursoort;
    
    //EXTERNE FACTUUR
    private int factuurnummer;
    private Date factuurdatum;
    private double totaalbedrag;
    private Boolean afgedrukt;
    private Boolean export;
    private Boolean betaald;
    private Boolean exportbmp;
    
    //INTERNE FACTUUR
    private Date datum;
    private Boolean interngefactureerd;
    private Periode periode;
    
    //FACTUUR INFO
    private ArrayList<Artikelgroep> artikelgroepen;
    private double[] bedragen;
    private String[] notitie;
    private String[] winkelverkopen;
    
}
