/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.invoicesystem.repository;


import hu.anzek.backend.invoicesystem.model.Cikk;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author User
 */
public class CikkBeolvas {
    
    /**
     * A "cikk.txt" kiolvasásához implementált funkcionális interfész body-ja.<br>
     */
     private final VevoCikkRepo<Cikk> cikkRepo = (fajlEleres) -> {         
         final List<Cikk> listaCikk = new ArrayList<>();
         try (BufferedReader reader = new BufferedReader(new FileReader(fajlEleres))) {             
            int mezoIndex = 0;
            String line;
            long kod = 0L;
            String megnevezes = null;   
            String mennyisegi_egyseg = "";
            double egyseg_ar = 0;             
            
            while ((line = reader.readLine()) != null) {
                
                System.out.println("LINE = > " + line);
                
                if ( ! line.trim().isEmpty()) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {

                        String fieldName = parts[0].trim();
                        String value = parts[1].trim();
                        
                        switch (fieldName) {
                            case "kod":
                                kod =  Long.parseLong(value);
                                mezoIndex++;
                                break;
                            case "megnevezes":
                                megnevezes = value;
                                mezoIndex++;
                                break;                                
                            case "mennyisegi_egyseg":
                                mennyisegi_egyseg = value;
                                mezoIndex++;
                                break;
                            case "egyseg_ar":
                                egyseg_ar = Double.parseDouble(value);
                                mezoIndex++;
                                break;                            
                            default:
                                System.out.println("Ismeretlen mezo: " + fieldName);
                                break;
                        }               
                    } else {
                        System.out.println("Hibas adatsor parts.length= " + parts.length + " adatValue(" + line + ")");
                    }
                }else if (mezoIndex == 4) {
                    listaCikk.add(new Cikk(kod, megnevezes, mennyisegi_egyseg, egyseg_ar));
                    kod = 0L;
                    mezoIndex = 0;
                    megnevezes = null;
                    mennyisegi_egyseg = null;                    
                    egyseg_ar = 0.00;
                }
            }
            if (mezoIndex == 4) {
                Cikk cikk = new Cikk(kod, megnevezes, mennyisegi_egyseg, egyseg_ar);
                double ar = cikk.egyseg_ar();
                listaCikk.add(cikk);
                // a CC elvárt kód ez lenne:
                // listaCikk.add(new Cikk(kod, megnevezes, mennyisegi_egyseg, egyseg_ar));
            }            
        } catch (IOException e) {
            e.printStackTrace();
        }             
         
         return listaCikk;
     };
     
     /**
      * Visszadaja a "cikk.txt" tartalmát, egy cikk-entitás lista kollekció formájában.<br>
      * @return cikk-entitás lista kollekció<br>
      */
     public List<Cikk> getCikkList(){         
         VevoCikkRepo<Cikk> repo = this.cikkRepo;
         return repo.beolvasTxtAdattarbol("cikk.txt");
     }

    /**
     * Visszaadja a cikkOlvaso egy implementációját<br> 
     * (tehát a metódust, a futtathatókódot - nem az olvasás eredményét)<br>
     * @return a "cikkRepo" egy példánya<br>
     */ 
    public  VevoCikkRepo<Cikk> cikkOlvas() {        
        return this.cikkRepo;
    }
}
