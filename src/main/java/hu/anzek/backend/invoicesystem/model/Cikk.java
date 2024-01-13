/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package hu.anzek.backend.invoicesystem.model;



/**
 * A számla cikk-elemeinek az entitása<br>
 * @author User
 */
public record Cikk( Long kod,
                    String megnevezes,
                    String mennyisegi_egyseg,
                    double egyseg_ar) {
}
