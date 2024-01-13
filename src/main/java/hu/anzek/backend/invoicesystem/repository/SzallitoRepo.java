/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hu.anzek.backend.invoicesystem.repository;


import hu.anzek.backend.invoicesystem.service.MySQLConnectionService;
import hu.anzek.backend.invoicesystem.model.Szallito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * SzallitoRepo interfesz
 * @author User
 */
@FunctionalInterface
public interface SzallitoRepo {
 
    /**
     * Beolvassa a számlakiállító adatait egy java rekord entitásba<br>
     * @param fajlEleres a fájl elérése, útvonala<br>
     * @return visszaadja a szállító adatokat<br>
     */
    public Szallito beolvasTxtAdattarbol(String fajlEleres);

    /**
     * Végrehajt egy sql-Insert parancsot a Szallito egy példányán<br>
     * @param szallito a szallito entitás egy példánya<br>
     * @throws SQLException az sql-kezelés egy technikai hibamiatti kivétele<br>
     */
    default public void sqlInsert(Szallito szallito) throws SQLException {        
        if(szallito != null){            
            MySQLConnectionService msqlr = new MySQLConnectionService();
            try (Connection connection = msqlr.getConnection()) {            
                             
                String sql = "INSERT INTO szallito (adoszam, megnevezes, deviza, cim) VALUES (?, ?, ?, ?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {                       
                    // a settelés sorrendje mindegy, de a ParaméterIndex egyáltalán nem mindegy! :
                    preparedStatement.setString(2, szallito.megnevezes());
                    preparedStatement.setString(1, szallito.adoszam());
                    preparedStatement.setString(4, szallito.deviza());
                    preparedStatement.setString(3, szallito.cim());
                    // végrehajtás:
                    // az Update itt nem az SQL UPDATE-val való azonosságot, hanem a táblafrissítést jelenti!                    
                    preparedStatement.executeUpdate();
                }
               
                // ezt itt nem kell kiadni (de lehet, csak felesleges)
                // mert a try(){..}catch(){..} szerkezeten belül lett deklarálva
                // a "connection" referenci hivatkozás, és ha a try-catch "bezáródik", ez is bezáródik...
                // más esetben fontos, hogy le kell zárni!
                msqlr.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }     
}
