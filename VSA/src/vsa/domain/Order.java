/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vsa.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import vsa.enums.MutatieRegel;

/**
 *
 * @author Michael
 */
public class Order implements Serializable
{
    private Calendar jaar;
    private Calendar week;
    private int nummer;
    private int levering;
    private String naam;
    private Factuur factuur;
    private String notities;
    private Debiteur debiteur;
    
    //ARTIKELEN
    private Artikel[] artikelen;
    private MutatieRegel mutatieregel;
}
