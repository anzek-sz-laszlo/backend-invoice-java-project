/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.invoicesystem.model;


/**
 * A számla fő adatai<br>
 * @author User
 */
public record Invoice (Long id, 
                       String sorszam, 
                       Szallito szallito, 
                       String sz_adatok,
                       Vevo vevo, 
                       String v_adatok,
                       String keszult,
                       double arfolyam,
                       double netto,
                       double afa,
                       double engedmeny
        ){  
}
