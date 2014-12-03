/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vsa.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import vsa.domain.Artikelgroep;
import vsa.enums.BTWCode;

/**
 *
 * @author Michael
 */
public class Artikel implements Serializable
{
    private int artikelnr;
    private String omschrijving;
    private String korteomschrijving;
    private Artikelgroep artikelgroep;
    private LocalDate ingangsdatum;
    private LocalDate einddatum;
    private double verkoopprijsincl;
    private double verkoopprijsexcl;
    private BTWCode btwcode;
    private String zoekcode;
    private String notitie;
    
    public Artikel(int artikelnr, String omschrijving, String korteomschrijving, 
            Artikelgroep artikelgroep, LocalDate ingangsdatum, LocalDate einddatum, double verkoopprijsincl, double verkoopprijsexcl, BTWCode btwcode, String notities, String zoekcode)
    {
        this.artikelnr = artikelnr;
        this.omschrijving = omschrijving;
        this.korteomschrijving = korteomschrijving;
        this.artikelgroep = artikelgroep;
        this.ingangsdatum = ingangsdatum;
        this.einddatum = einddatum;
        this.verkoopprijsexcl = verkoopprijsexcl;
        this.verkoopprijsincl = verkoopprijsincl;
        this.btwcode = btwcode;
        this.notitie = notitie;
        this.zoekcode = zoekcode;
    }
    
    public void setArtikelnr(int artikelnr)
    {
        this.artikelnr = artikelnr;
    }
    
    public int getArtikelnr()
    {
        return this.artikelnr;
    }
    
    public void setOmschrijving(String omschrijving)
    {
        this.omschrijving = omschrijving;
    }
    
    public String getOmschrijving()
    {
        return this.omschrijving;
    }
    
    public void setKorteOmschrijving(String korteomschrijving)
    {
        this.korteomschrijving = korteomschrijving;
    }
    
    public String getKorteOmschrijving()
    {
        return this.korteomschrijving;
    }
    
    public void setArtikelgroep(Artikelgroep artikelgroep)
    {
        this.artikelgroep = artikelgroep;
    }
    
    public Artikelgroep getArtikelgroep()
    {
        return this.artikelgroep;
    }
    
    public void setIngangsdatum(LocalDate ingangsdatum)
    {
        this.ingangsdatum = ingangsdatum;
    }
    
    public LocalDate getIngangsdatum()
    {
        return this.ingangsdatum;
    }
    
    public void setEinddatum(LocalDate einddatum)
    {
        this.einddatum = einddatum;
    }
    
    public LocalDate getEinddatum()
    {
        return this.einddatum;
    }
    
    public void setVerkoopprijsincl(int verkoopprijsincl)
    {
        this.verkoopprijsincl = verkoopprijsincl;
    }
    
    public double getVerkoopprijsincl()
    {
        return this.verkoopprijsincl;
    }
    
    //DEZE MOET AUTOMATISCH GEGENEREERD WORDEN AAN DE HAND VAN INCL PRIJS
    //DEZE KRIJGT GEEN WAARDE MEE
    public void setVerkoopprijsexcl()
    {
        if(btwcode == BTWCode.Hoog) 
        {
            this.verkoopprijsexcl = this.verkoopprijsincl * 0.79;
        }
        if(btwcode == BTWCode.Laag)
        {
            this.verkoopprijsexcl = this.verkoopprijsincl * 0.94;
        }
        if(btwcode == BTWCode.Nul)
        {
            this.verkoopprijsexcl = this.verkoopprijsincl;
        }
    }
    
    public double getVerkoopprijsexcl()
    {
        return this.verkoopprijsexcl;
    }
    
    public void setBTWCode(BTWCode btwcode)
    {
        this.btwcode = btwcode;
    }
    
    public BTWCode getBTWCode()
    {
        return this.btwcode;
    }
    
    public void setNotities(String notitie)
    {
        this.notitie = notitie;
    }
    
    public String getNotitie()
    {
        return this.notitie;
    }   
    
    public String getZoekcode()
    {
        return this.zoekcode;
    }
    
    public void setZoekcode(String zoekcode)
    {
        this.zoekcode = zoekcode;
    }
}
