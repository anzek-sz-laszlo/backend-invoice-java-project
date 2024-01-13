/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package hu.anzek.backend.invoicesystem.model;


/**
 * A szamlakiallito - szallito entitasa<br>
 * @author User<br>
 */
public record Szallito( String adoszam,
                        String megnevezes,
                        String cim,
                        String deviza){
}
