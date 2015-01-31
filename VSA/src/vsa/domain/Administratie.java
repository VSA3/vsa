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

/**
 *
 * @author Michael
 */
public class Administratie extends Observable
{
    private ArrayList<Order> orders;
    private ArrayList<Factuur> facturen;
    
    private List<Artikel> artikelen;
    private ObservableList<Artikel> observableArtikelen;
    
    private List<Debiteur> debiteuren;
    private ObservableList<Debiteur> observableDebiteuren;
    
    private List<BTW> btwtarieven;
    private ObservableList<BTW> observableBTW;
    
    private List<Artikelgroep> artikelgroepen;
    private ObservableList<Artikelgroep> observableArtikelgroepen;
    
    private String naam;
        
    private static Administratie INSTANCE;
    
    private Administratie()
    {
        this.setAdministrationData();
        
        INSTANCE = this;  
    }
    
    /**
     * Methode om alles te instantieren
     * @return 
     */
    public boolean setAdministrationData()
    {
        try
        {
            orders = new ArrayList();
            facturen = new ArrayList();

            artikelen = new ArrayList<Artikel>();
            observableArtikelen = FXCollections.observableList(artikelen);

            debiteuren = new ArrayList<Debiteur>();
            observableDebiteuren = FXCollections.observableList(debiteuren);
                        
            btwtarieven = new ArrayList<BTW>();
            observableBTW = FXCollections.observableList(btwtarieven);
            
            artikelgroepen = new ArrayList<Artikelgroep>();
            observableArtikelgroepen = FXCollections.observableList(artikelgroepen);
        }
        catch(Exception e)
        {
              return false;      
        }
        
        try
        {
            this.checkForSerialization();
        }
        catch(Exception ez)
        {
            return false;        
        }
        
        try
        {
            this.deSerializeData();
            this.setTestData();
            return true;
        }
        catch(Exception ex)     
        {
            return false;
        }      
    }
    
    /**
     * Methode voor het toevoegen van testdata aan de applicatie
     * voor test doeleinden.
     */
    public void setTestData()
    {
        Artikelgroep test = new Artikelgroep(1, "GrootBrood", "brood");
        observableArtikelgroepen.add(test);
        Artikelgroep test2 = new Artikelgroep(2, "Banket", "gebak");
        observableArtikelgroepen.add(test2);
        
        LocalDate ingang = LocalDate.now();
        LocalDate eind = LocalDate.now();
        
        BTW btwhoog = new BTW("Hoog", 21, "Hoog tarief BTW");
        observableBTW.add(btwhoog);
        BTW btwlaag = new BTW("Laag", 6, "Laag tarief BTW");
        observableBTW.add(btwlaag);
        BTW btwnull = new BTW("Nul", 0, "Nul tarief BTW");
        observableBTW.add(btwnull);
        
        Artikel artikel = new Artikel(1, "WitBrood", "vierkant wit brood", test, ingang, eind, 3.32, 2.23, btwlaag, "NIKS", "broodwit");
        observableArtikelen.add(artikel);
        Artikel artikel2 = new Artikel(2, "BruinBrood", "vierkant bruin brood", test, ingang, eind, 3.32, 2.23, btwlaag, "NIKS", "broodwit");
        observableArtikelen.add(artikel2);
        
        Debiteur debiteurtest = new Debiteur(1,"Gemeente Oosterhout", "Oosterhout", "4904CX", "GemeentePlein 22", "0162-424424", 2);
        observableDebiteuren.add(debiteurtest);
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
    
    /**
     * Methode voor het verwijderen van een Debiteur object in de debiteuren lijsten
     * doormiddels van een uniek nummer kan de Debiteur geidentificeerd worden
     * @param nummer
     * @return 
     */
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
    
    public ObservableList<BTW> getBTW()
    {
        return (ObservableList<BTW>) FXCollections.unmodifiableObservableList(observableBTW);
    }
    
    public ObservableList<Artikelgroep> getArtikelgroepen()
    {
        return (ObservableList<Artikelgroep>) FXCollections.unmodifiableObservableList(observableArtikelgroepen);
    }
    
    /**
     * Zoekt een artikel groep in de lijst doormiddels van een NUMMER
     * @param nummer
     * @return 
     */
    public Artikelgroep zoekArtikelGroepOpNummer(int nummer)
    {
        for(Artikelgroep a : this.artikelgroepen)
        {
            if(nummer == a.getNummer())
            {
                return a;
            }
        }
        
        return null;
    }
    
    /**
     * Zoekt een artikel groep in de lijst doormiddels van een NAAM
     * @param naam
     * @return 
     */
    public Artikelgroep zoekArtikelGroepOpNaam(String naam)
    {
        for(Artikelgroep a : this.artikelgroepen)
        {
            if(naam.equals(a.getNaam()))
            {
                return a;
            }
        }
        
        return null;
    }
    
    /**
     * Zoekt een artikel in de lijst van artikelen doormiddels van een nummer 
     * @param nummer
     * @return 
     */
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
    
    /**
     * Methode om te kijken of serializeren valide is
     * @return 
     */
    public boolean checkForSerialization()
    {
        //TODO
        return true;
    }
    
    /**
     * Methode voor het ophalen en laden van alle data uit de administratie
     * klass bij het opnieuw opstarten van het programma
     * @return 
     */
    public boolean deSerializeData()
    {
        //TODO
        return true;
    }
    
    /**
     * Returnt de singleton instance van de Administratie klass
     * @return 
     */
    public static Administratie getInstance()
    {
        if(INSTANCE == null)
        {
            new Administratie();
        }
        return INSTANCE;
    }
    
    /**
     * Methode om de huidige BTW aan te passen met het oog op de toekomst
     * @param naam
     * @param percentage
     * @return 
     */
    public boolean aanpassenBTW(String naam, int percentage)
    {
        for(BTW btw : this.btwtarieven)
        {
            if(btw.getNaam().equals(naam))
            {
                btw.setPercentage(percentage);
                observableBTW = FXCollections.observableList(btwtarieven);
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Zoekt een BTW object op naam in de lijst van btwtarieven
     * @param naam
     * @return 
     */
    public BTW zoekBTWOpNaam(String naam)
    {
        for(BTW btw : this.btwtarieven)
        {
            if(btw.getNaam().equals(naam))
            {
                return btw;
            }
        }
        
        return null;
    }
    
    public boolean toevoegenArtikel(Artikel artikel) {
        if (artikel == null) {
            return false;
            //SHIT GAAT FOUT
        } else {
            for (Artikel a : this.artikelen) {
                if (artikel == a) {
                    return false;
                } else {
                    this.artikelen.add(artikel);
                    return true;
                }
            }
        }

        return false;
    }
}
