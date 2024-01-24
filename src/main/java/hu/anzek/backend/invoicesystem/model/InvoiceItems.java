/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.invoicesystem.model;


import hu.anzek.backend.invoicesystem.service.KulsoKulcs;


/**
 * A számla tételei<br>
 * @author User
 */
public record InvoiceItems(Long id, 
                           @KulsoKulcs(table = "invoice")
                           String sorszam, 
                           @KulsoKulcs(table = "cikk")
                           Long cikk_kod,
                           Integer mennyiseg, 
                           double egyseg_ar, 
                           double afa_kulcs
        ){
}
