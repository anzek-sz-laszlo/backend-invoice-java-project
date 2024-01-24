/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.invoicesystem.repository;


import hu.anzek.backend.invoicesystem.service.MySQLConnectionService;
import hu.anzek.backend.invoicesystem.model.Cikk;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * 
 * @author User
 */
public class JdbcCikkReadRepo implements VevoCikkRepo {

    // tagváltozó
    MySQLConnectionService msqlr;
    
    /**
     * nem statikus inicializálás<br>
     */
    {
        this.msqlr = new MySQLConnectionService();
    }
    
    /**
     * Egy Cikk-elemet beolvasó osztály, amely a VevoCikkRepo interfész implementációja<br>
     * Azonban nekünk itt csakis egye konkrét típus kell a két lehetségesből, a "Cikk"<br>  
     */
    public JdbcCikkReadRepo() {
    }

    /**
     * Csak erre a metódusra van itt szükségünk!<br>
     * Az interfész generikus típus (2 lehetséges változatot visel a neve),<br> 
     * azonban elvben bármilyen más is lehetne, mindössze az a lényeg, hogy a primary key "kod" legyen és<br>
     * az Optional(Tetszolgeses_osztaly) lehetne... itt "Cikk" lesz!<br>
     * @param kod az elsődleges kulcs, ami alapján keresünk az adatbázis "cikk" táblájában<br>
     * @return visszaad opcionálisan vagy egy cikk egyedet, vagy semmit<br>
     */
    @Override
    public Optional<Cikk> findByKod(Long kod) {
        
        if(kod != null){
            String sqlString = "SELECT DISTINCT * FROM cikk WHERE kod = ?";
            try (Connection connection = msqlr.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlString)) {
                preparedStatement.setLong(1, kod);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(new Cikk(resultSet.getLong("kod"),
                                                    resultSet.getString("megnevezes"),
                                                    resultSet.getString("mennyisegi_egyseg"),
                                                    resultSet.getDouble("egyseg_ar") 
                                                    )
                                          );
                    }
                }catch(SQLException e){
                    // saját hibakezelés egyik lehtséges módja:
                    // "SELECT DISTINCT * FROM cikk WHERE kod = ?" (? = 1)
                    System.out.println("Figyelem! Hibas sql-lekerdezes a 'cikk' tablabol : \"" + sqlString + "\" (? = " + kod + ")");
                    System.out.println("SQLException-hibaforras: " + e.getMessage() );
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            
            return Optional.empty();        
        }else{
            return Optional.empty(); 
        }
    }

    /**
     * Mindent visszaad egy listában<br>
     * @return visszaad opcionálisan Cikk-listát vagy semmit<br>
     */
    @Override
    public Optional<List<Cikk>> findAll() {
       
        String sql = "SELECT DISTINCT * FROM cikk";
        try (Connection connection = msqlr.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {     
            List<Cikk> cikkek = new ArrayList<>();
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Cikk cikk = new Cikk(resultSet.getLong("kod"),
                                         resultSet.getString("megnevezes"),
                                         resultSet.getString("mennyisegi_egyseg"),
                                         resultSet.getDouble("egyseg_ar") 
                                        );
                    cikkek.add(cikk);
                }
            }
            
            if ( ! cikkek.isEmpty()) {
                return Optional.of(cikkek);
            }            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();        
    }
    
    /**
     * Erre a metódusra nincs szükségünk, mert ez a szövegfájlok beolvasásakor szükséges<br>
     * Üres bodyval implementáltuk, tehát ezen az osztályon keresztül így nem lenne használható!<br>
     * @param fajlEleres a fájl neve és útvonala<br>
     * @return 
     */
    @Override
    public List<Cikk> beolvasTxtAdattarbol(String fajlEleres) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
