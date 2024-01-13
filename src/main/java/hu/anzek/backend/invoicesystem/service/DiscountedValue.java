/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hu.anzek.backend.invoicesystem.service;


/**
 *
 * @author User
 */
@FunctionalInterface
public interface DiscountedValue {
    
    /**
     * Engedményezés<br>
     * @param alap a bemeneti (eredeti) alap értéke<br>
     * @param engedmeny az engedmény mértéke 0..100 % -ban!<br>
     * @return 
     */
    public double engedmenyezo(double alap, int engedmeny);
    
    /**
     * A bemenetre küldött "double" értéket két tizedesre kerekíti<br>
     * Fontos!<br>
     * A Math.round() - visszatérő értéke "long",!!! (nem keveredő majd a random -al)<br> 
     * ...tehát típuskényszerítés kell, különben egészre csonkolja!<br>
     * @param value a kerekítendő érték<br>
     * @return a két tizedesre kerekített érték<br>
     */
    default public double rndTwo(double value){        
        return (double) Math.round(value * 100) / 100;
    } 
}
