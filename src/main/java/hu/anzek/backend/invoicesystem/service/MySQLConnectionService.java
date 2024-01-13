/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.invoicesystem.service;

import hu.anzek.backend.invoicesystem.configuration.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * MySql kapcsolati osztály<br>
 * @author User
 */
public class MySQLConnectionService {

    private String url;
    private String username;
    private String password;
    private final DatabaseConfig config;

    /**
     * Nem statikus inicializációs blokk
     */
    {    
        // a JDBC csatlakozáshoz szükséges adatok kiolvasása példányosítással:
        config = new DatabaseConfig().getDatabaseConfig();
    }

    /**
     * A MySql JDBC kapcsolati osztálya<br>
     */
    public MySQLConnectionService() {   
    } 
    
    /**
     * A kapcsolat felépítése<br>
     * @return visszaad egy kapcsolati példányt, amely a DriverManager osztály része<br>
     * @throws SQLException sql hiba esetén áll be!<br>
     */
    public Connection getConnection() throws SQLException {
        // this.url = config.getDatabase().getUrl(); 
        this.username = config.getDatabase().getUsername();
        this.password = config.getDatabase().getPassword();    
        
        return DriverManager.getConnection(config.getDatabase().getUrl(), username, password);
    }

    /**
     * Lezárja az aktuális példányban élő kapcsolatot!<br>
     * Nem kell akkor alkalmazni ha az alábbi feltételek együttesen igazak:<br>
     * - a jelen osztály példányosítását egy blokkon belül-,<br> 
     * - lokális referenciákkal végeztük,<br> 
     * - és a blokk végére futunk, az az a blokk bezáródásával a referenciák életciklusa is véget ér!<br>
     * @param connection a kapcsolati referencia objektum<br>
     * @throws SQLException az sql hibák esetében áll be!<br>
     */
    public void closeConnection(Connection connection) throws SQLException {
        if ( (connection != null) && ( ! connection.isClosed()) ) {
            connection.close();
        }
    }   

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
