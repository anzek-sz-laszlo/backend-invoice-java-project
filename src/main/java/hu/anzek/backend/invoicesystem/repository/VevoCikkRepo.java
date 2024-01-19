/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hu.anzek.backend.invoicesystem.repository;


import hu.anzek.backend.invoicesystem.service.MySQLConnectionService;
import hu.anzek.backend.invoicesystem.model.Cikk;
import hu.anzek.backend.invoicesystem.model.Vevo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


/**
 * VevoCikkRepo interfesz<br>
 * @author User<br>
 * @param <T> vevo/cikk rekord<br>
 */
@FunctionalInterface
public interface VevoCikkRepo <T>{

    /**
    * A generikusok olyan típusparaméterek, <br>
    * Ezek lehetővé teszik az osztályok és interfészek általánosítását, <br>
    * így azok tetszőleges típusokkal működhetnek. <br>
    * Egy adott típust "átadhatunk" egy osztálynak vagy interfésznek, <br>
    * akkor, amikor azt példányosítjuk vagy használjuk. <br>
    * Ezáltal a kód általánosabb és újrafelhasználhatóbb lesz!<br>
     * @param fajlEleres a kiolvasandó szövegfájl neve, útvonala<br>
     * @return vissza ad egy listakollekciót<br>
    */    
    public List<T> beolvasTxtAdattarbol(String fajlEleres); 
    
    /**
     * Végrehajt egy sql-Insert parancsot, mégpedig példány-osztály függően (generikus metódus)<br>
     * @param bejovoOsztalyPeldany a Cikk, vagy Vevo egy példánya (meghívás -függően)<br>
     * @throws SQLException az sql kezelés valamilyen technikai hiba miatti kivétel<br>
     */
    default public void sqlInsert(T bejovoOsztalyPeldany) throws SQLException {        
        if(bejovoOsztalyPeldany != null){            
            MySQLConnectionService msqlr = new MySQLConnectionService();            
            try (Connection connection = msqlr.getConnection()) { 
                if (bejovoOsztalyPeldany instanceof Cikk cikk) {                
                    String sql = "INSERT INTO cikk (kod, megnevezes, mennyisegi_egyseg, egyseg_ar) VALUES (?, ?, ?, ?)";
                    // így is működne:
                    // String sql = "INSERT INTO cikk (kod, megnevezes, mennyisegi_egyseg, egyseg_ar) VALUES (" 
                    //            + cikk.kod() 
                    //            + ", " 
                    //            + cikk.megnevezes() 
                    //            + " ," 
                    //            + cikk.mennyisegi_egyseg() + ", ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                        // a settelés sorrendje mindegy, de a ParaméterIndex egyáltalán nem mindegy! :
                        preparedStatement.setLong(1, cikk.kod());
                        preparedStatement.setString(2, cikk.megnevezes());
                        preparedStatement.setString(3, cikk.mennyisegi_egyseg());
                        preparedStatement.setDouble(4, cikk.egyseg_ar());
                        // végrehajtás
                        // az Update itt nem az SQL UPDATE-val való azonosságot, hanem a táblafrissítést jelenti!
                        preparedStatement.executeUpdate();
                    }
                }else{

                    Vevo vevo = (Vevo) bejovoOsztalyPeldany;
                    String sql = "INSERT INTO vevo (kod, adoszam, megnevezes, cim, fizmod, fizhatido, engedmeny, deviza) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                        preparedStatement.setLong(1, vevo.kod());
                        preparedStatement.setString(2, vevo.adoszam());
                        preparedStatement.setString(3, vevo.megnevezes());
                        preparedStatement.setString(4, vevo.cim());
                        preparedStatement.setInt(5, vevo.fizmod());
                        preparedStatement.setInt(6, vevo.fizhatido());
                        preparedStatement.setInt(7, vevo.engedmeny());
                        preparedStatement.setString(8, vevo.deviza());
                        // végrehajtás:
                        // az Update itt nem az SQL UPDATE-val való azonosságot, hanem a táblafrissítést jelenti!
                        preparedStatement.executeUpdate();
                    }                
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
    
    /**
     * Egy cikk, vagy vevő entitást (azaz a "kód"-al azonos megkeresésssel) beolvasó metódus<br> 
     * Itt vezetjük be az "Optional pack" fogalmát:<br>
     * Java 8-tól létezik és az érték "opcionális csomag" voltát jelzi a csomagban(létezik, nem létezik), de nem NULL érték!<br>
     * letezikVagySem.isPresent() - ekkor létezik és letezikVagySem.get() -el kiolvasható a csomagolásból<br>
     * ha pedig nem létezik Optional.empty() az értéke!<br>
     * @param kod a kereendő kulcs<bry
     * @return opcionálisan visszaadja a kiolvasott entitást, vagy null értéket<br> 
     */
    default Optional<T> findByKod(Long kod){
        // üres bodyval
        // vagyis ez a metódus csak implementálva lesz használható       
        return Optional.empty();    
    }
    
    /**
     * Ez visszaad egy opcionális listát<br>
     * @return opcionális lista<br>
     */
    default Optional<List<T>> findAll(){
        
        return Optional.empty();  
    }
}
