/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vsa.domain;

import vsa.domain.Artikelgroep;
import vsa.enums.BTWCode;
import java.io.Serializable;
import java.util.Date;

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
    private Date ingangsdatum;
    private Date einddatum;
    private int verkoopprijsincl;
    private int verkoopprijsexcl;
    private BTWCode btwcode;
    private String[] notities;
    
    public Artikel(int artikelnr, String omschrijving, String korteomschrijving, 
            Artikelgroep artikelgroep, Date ingangsdatum, Date einddatum, int verkoopprijsincl, int verkoopprijsexcl, BTWCode btwcode, String[] notities)
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
        this.notities = notities;
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
    
    public void setIngangsdatum(Date ingangsdatum)
    {
        this.ingangsdatum = ingangsdatum;
    }
    
    public Date getIngangsdatum()
    {
        return this.ingangsdatum;
    }
    
    public void setEinddatum(Date einddatum)
    {
        this.einddatum = einddatum;
    }
    
    public Date getEinddatum()
    {
        return this.einddatum;
    }
    
    public void setVerkoopprijsincl(int verkoopprijsincl)
    {
        this.verkoopprijsincl = verkoopprijsincl;
    }
    
    public int getVerkoopprijsincl()
    {
        return this.verkoopprijsincl;
    }
    
    //DEZE MOET AUTOMATISCH GEGENEREERD WORDEN AAN DE HAND VAN INCL PRIJS
    //DEZE KRIJGT GEEN WAARDE MEE
    public void setVerkoopprijsexcl()
    {
        if(btwcode == BTWCode.Hoog) 
        {
            this.verkoopprijsexcl = (int)(this.verkoopprijsincl * 0.79);
        }
        if(btwcode == BTWCode.Laag)
        {
            this.verkoopprijsexcl = (int)(this.verkoopprijsincl * 0.94);
        }
        if(btwcode == BTWCode.Nul)
        {
            this.verkoopprijsexcl = this.verkoopprijsincl;
        }
    }
    
    public int getVerkoopprijsexcl()
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
    
    public void setNotities(String[] notities)
    {
        this.notities = notities;
    }
    
    public String[] getNotities()
    {
        return this.notities;
    }   
}
