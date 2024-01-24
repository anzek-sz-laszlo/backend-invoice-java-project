/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.invoicesystem.model;

import hu.anzek.backend.invoicesystem.service.FieldLimes;
import hu.anzek.backend.invoicesystem.service.KulsoKulcs;
import hu.anzek.backend.invoicesystem.service.SzamlaKeszultIdopontja;


/**
 * A számla fő adatai<br>
 * @author User
 */
public record Invoice (Long id,             // mysql altal automatikusan növelt sorszámtartalom (is lehetne) de mi megadjuk
                       @FieldLimes(lastLimes="10000",firstLimes="90000")
                       String sorszam,      // számlasorszám
                       String adoszam_sz,   // a szallító adószáma
                       String sz_adatok,    // ezek azok az adatok, amelyek változhatnak az idők során, de a számlában fixen kellenek
                       @KulsoKulcs(table = "vevo")
                       Long vevo_id,        // ez a hivatkozás a "Vevo" bekötése
                       String v_adatok,     // ezek azok az adatok, amelyek változhatnak az idők során, de a számlában fixen kellenek
                       String adoszam_v,    // a vevő adószáma
                       @SzamlaKeszultIdopontja
                       String keszult,      // a számla kiállítás időpontja
                       int fizmod,
                       int fizhatido,   
                       double arfolyam,
                       double netto,
                       double afa,
                       double engedmeny
        ){  
}
