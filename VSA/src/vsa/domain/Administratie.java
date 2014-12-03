/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vsa.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vsa.enums.BTWCode;

/**
 *
 * @author Michael
 */
public class Administratie extends Observable
{
    private ArrayList<Afleveradres> adressen;
    private ArrayList<Order> orders;
    private ArrayList<Factuur> facturen;
    private ArrayList<Artikelgroep> artikelgroepen;
    
    private List<Artikel> artikelen;
    private ObservableList<Artikel> observableArtikelen;
    
    private List<Debiteur> debiteuren;
    private ObservableList<Debiteur> observableDebiteuren;
    
    private String naam;
    
    private static Administratie INSTANCE;
    
    private Administratie()
    {
        adressen = new ArrayList();
        orders = new ArrayList();
        facturen = new ArrayList();
        artikelgroepen = new ArrayList();
        
        artikelen = new ArrayList<Artikel>();
        observableArtikelen = FXCollections.observableList(artikelen);
        
        debiteuren = new ArrayList<Debiteur>();
        observableDebiteuren = FXCollections.observableList(debiteuren);
        
        this.setTestData();
        
        INSTANCE = this;
    }
    
    public void setTestData()
    {
        Artikelgroep test = new Artikelgroep(1, "GrootBrood", "brood");
        LocalDate ingang = LocalDate.now();
        LocalDate eind = LocalDate.now();
        
        Artikel artikel = new Artikel(1, "WitBrood", "vierkant wit brood", test, ingang, eind, 2.32, 3.23, BTWCode.Hoog, "NIKS", "broodwit");
        observableArtikelen.add(artikel);
        
        Debiteur debiteurtest = new Debiteur(1,"Gemeente Oosterhout", "Oosterhout", "4904CX", "GemeentePlein 22", "0162-424424", 2);
        observableDebiteuren.add(debiteurtest);
    }
    
    public ArrayList<Afleveradres> getAlleAdressen()
    {
        return this.adressen;
    }
    
    public List<Debiteur> getAlleDebiteuren()
    {
        return this.debiteuren;
    }
    
    public boolean maakNieuweDebiteur(int nummer, String naam, String plaats, String postcode, String adres, String telefoon1, int betalingstermijn)
    {
        Debiteur nieuw = new Debiteur(nummer, naam, plaats, postcode, adres, telefoon1, betalingstermijn);
        
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
        
    public ObservableList<Artikel> getArtikelen()
    {
        return (ObservableList<Artikel>) FXCollections.unmodifiableObservableList(observableArtikelen);
    }
    
    public ObservableList<Debiteur> getDebiteuren()
    {
        return (ObservableList<Debiteur>) FXCollections.unmodifiableObservableList(observableDebiteuren);
    }
    
    public Artikel zoekArtikelopNummer(int nummer)
    {
        for(Artikel a : this.artikelen)
        {
            if(nummer == a.getArtikelnr())
            {
                return a;
            }
        }
        
        return null;
    }
    
    public boolean checkForSerialization()
    {
        //TODO
        return true;
    }
    
    public boolean deSerializeData()
    {
        //TODO
        return true;
    }
    
    public static Administratie getInstance()
    {
        if(INSTANCE == null)
        {
            new Administratie();
        }
        return INSTANCE;
    }
}
