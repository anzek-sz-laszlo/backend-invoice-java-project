/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.invoicesystem.model;

import hu.anzek.backend.invoicesystem.service.KulsoKulcs;
import hu.anzek.backend.invoicesystem.service.SzamlaKeszultIdopontja;


/**
 * A számla fő adatai<br>
 * @author User
 */
public record Invoice (Long id, 
                       String sorszam,
                       String adoszam_sz,
                       String sz_adatok,    // ezek azok az adatok, amelyek változhatnak az idők során, de a számlában fixen kellenek
                       @KulsoKulcs
                       Long vevo_id,        // ez a hivatkozás a "Vevo" bakötése
                       String v_adatok,     // ezek azok az adatok, amelyek változhatnak az idők során, de a számlában fixen kellenek
                       String adoszam_v,
                       @SzamlaKeszultIdopontja
                       String keszult,
                       double arfolyam,
                       double netto,
                       double afa,
                       double engedmeny
        ){  
}
