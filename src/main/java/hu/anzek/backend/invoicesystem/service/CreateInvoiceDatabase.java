/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.invoicesystem.service;


import hu.anzek.backend.invoicesystem.service.MySQLConnectionService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 *
 * @author User
 */
public class CreateInvoiceDatabase {
    
    // tagváltozók
    MySQLConnectionService msqlr;

    /**
     * nem statikus inicializálás<br>
     */
    {
        this.msqlr = new MySQLConnectionService();
    }
    
    public CreateInvoiceDatabase() {
    }
    
    // Invoice tábla létrehozása
    public void createInvoiceTabla() throws SQLException {        
        // Ebben a megoldásban nincs CATCH ág.
        // a "try( Ha itt a feladatok sikerülnek ){ akkor futhat ez is }" nincs különben semmi...
        // illetve annyi van, hogyha hiba (kivét volt) tovább dobjuk a hívó metódus felé... (fejléc: throws SQLException...)
        try (Connection connection = msqlr.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Invoice ("
                            + "id BIGINT , "                          // így is lehetne:  PRIMARY KEY AUTO_INCREMENT 
                            + "sorszam VARCHAR(30) PRIMARY KEY, "   
                            + "sz_adatok VARCHAR(255), "
                            + "adoszam_sz VARCHAR(30), "
                            + "vevo_id BIGINT, "
                            + "v_adatok VARCHAR(255), "
                            + "adoszam_v VARCHAR(30), "
                            + "keszult VARCHAR(10), "
                            + "fizmod INT, "
                            + "fizhatido INT, "   
                            + "arfolyam DOUBLE, "
                            + "netto DOUBLE, "
                            + "afa DOUBLE, "
                            + "engedmeny DOUBLE, "        
                            + "FOREIGN KEY (vevo_id) REFERENCES Vevo(kod))"
            )             
        ){
            statement.execute();  
        }
    }
    
    // InvoiceItems tábla létrehozása
    public void createInvoiceItemsTabla() throws SQLException {        
        try (Connection connection = msqlr.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS InvoiceItems ("
                            + "id BIGINT PRIMARY KEY AUTO_INCREMENT, "
                            + "sorszam VARCHAR(30), "
                            + "cikk_kod BIGINT, "
                            + "mennyiseg INT, "                
                            + "egyseg_ar DOUBLE, "
                            + "afa_kulcs DOUBLE, "
                            + "afa DOUBLE, "
                            + "FOREIGN KEY (cikk_kod) REFERENCES cikk(kod), "        
                            + "FOREIGN KEY (sorszam) REFERENCES Invoice(sorszam))"
            )  
        ){
            statement.execute();  
        }
    }    
}
