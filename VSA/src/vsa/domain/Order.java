/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vsa.domain;

import java.io.Serializable;
import java.util.Date;
import vsa.enums.HerkomstOrder;
import vsa.enums.MutatieRegel;

/**
 *
 * @author Michael
 */
public class Order implements Serializable
{
    private int jaar;
    private int nummer;
    private int week;
    private int levering;
    private Afleveradres afleveradres;
    private String naam;
    private Date startdatum;
    private Date einddatum;
    private Factuur factuur;
    private String notities;
    
    //DAGEN
    private Boolean maandag;
    private Boolean dinsdag;
    private Boolean woensdag;
    private Boolean donderdag;
    private Boolean vrijdag;
    private Boolean zaterdag;
    private Boolean zondag;
    
    //ARTIKELEN
    private Artikel[] artikelen;
    private MutatieRegel mutatieregel;
    private HerkomstOrder herkomstorder;
    
    
    
    
    
}
