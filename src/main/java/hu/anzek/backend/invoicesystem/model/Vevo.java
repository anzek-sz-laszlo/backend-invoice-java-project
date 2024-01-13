/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package hu.anzek.backend.invoicesystem.model;


/**
 * A szamla vevokent feltuntetett entitasa
 * Engedmény: 0-100 % -lehet (de csak egész százalékérték lehet)
 * @author User
 */
public record Vevo( Long kod,
                    String adoszam,
                    String megnevezes,
                    String cim,
                    int fizmod,
                    int fizhatido,
                    // az engedmény 0-100 % -között lehet (de csak egész százalékérték)
                    int engedmeny,                    
                    String deviza){
}
