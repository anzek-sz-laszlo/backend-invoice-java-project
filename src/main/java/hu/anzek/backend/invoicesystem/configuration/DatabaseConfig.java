/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.invoicesystem.configuration;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.yaml.snakeyaml.Yaml;


/**
 *
 * @author User
 */
public class DatabaseConfig {
    
    private Database database;
    
    /**
     * az adatbázis konfigurációs osztály, amely a properties-ből veszi a beállításokat<br>
     */
    public static class Database {
        private String url;
        private String username;
        private String password;

        public Database() {
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
    
    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
    
    /**
     * Ez a megoldás maga az osztály elvégzi a teljes hibakezelési folyamatot,<br> 
     * a yml fájl tartalom olvasása esetén.<br> 
     * így (vegyük észre), hogy nem kell a hívó oldalon,<br> 
     * vagyis a példányosítás, illetve az adatbetöltés helyén sem hibakezelő- sem kivételeket átadás sem!<br> 
     * Szemben a "InvoiceConfigOsztaly.java" hasonló megoldásávalés, ahol viszont<br> 
     * kell a try(){..}catch(){..} -et alkalmazni! a hívó helyeken,<br> 
     * mert az kivételkezeléssel "oldja meg" az esetlegesen bekövetkező fájlovasási és adatbetöltési problémákat!<br>     
     * @return visszaad egy adatokkal feltöltött DatabaseConfig osztálypéldányt!<br>
     */
    public DatabaseConfig getDatabaseConfig(){
        try (InputStream input = Files.newInputStream(Paths.get("src/main/resources/application.yml"))) {
            Yaml yaml = new Yaml();
            DatabaseConfig config = yaml.loadAs(input, DatabaseConfig.class);

            if (config == null || config.getDatabase() == null) {
                System.err.println("Error: Failed to load database configuration from YAML.");
            }

            return config;
        } catch (IOException e) {
            e.printStackTrace();
            return null; // vagy kezeld az exception-t a megfelelő módon
        }
    }

    public DatabaseConfig() {
    }
}
