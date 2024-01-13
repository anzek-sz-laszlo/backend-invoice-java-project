/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.invoicesystem.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * FONTOS (statikus - nem statikus állpothoz:<br>
 * Ha statikus belső osztályokat használunk, akkor nincs szükség arra, hogy létrehozzuk a külső osztály egy példányát!<br> 
 * (már persze a belső osztály példányosításához...)<br> 
 * Tehát, ha egy osztály statikus, akkor annak példányosításához nincs szükség a külső osztály példányára, csak a hivatkozására!<br> 
 * Ez különösen hasznos lehet, ha az osztályok között nincs szoros kapcsolat,<br> 
 * vagyis a külső osztály példányosított adataitól nem függenek a belső osztály adatai (vagy fordítva)!<br>
 * Ebben a példában a "InvoceConfigOsztaly" osztály tartalmazza a konfigurációhoz szükséges belső osztályokat<br> 
 * és csak ezért van szükségünk rá...<br> 
 * Ha ezek a belső osztályok nem statikusak hanem példányszintűek lennének, akkor:<br> 
 - a belső osztályok példányosításukhoz először létre kellene hozni egy InvoceConfigOsztaly példányt is,<br> 
 * - majd ezen a példányon keresztül érhetnénk csak el a belső osztályokat!<br>
 * Ez erőforrás lekötési felesleg!<br>
 * A statikus belső osztályok alkalmazásával közvetlenül elérhetőek a belső osztályok által fedett tartalom<br>
 * a külső osztályon keresztül, anélkül, hogy egy- egy külső példányt kellene létrehozni!<br>
 * de...<br>
 * A fentiek ellenére mégis az a jellemző, hogy a teljes példányt előre inicializálva elkészítjük,<br> 
 * - beolvasva abba az összes releváns paraméter előkészítést, és a kiolvasás mindig a belsőosztály getterén át történik!<br>
 * @author User
 */
public class InvoceConfigOsztaly {
    
    
    /**
     * Az 1. osztály tagváltozó<br>
     * a külső (a konfigurációs) osztály tagváltozói!<br> 
     * Fontos!<br>
     * az elenevezésüknek azonosnak kelllenniük a YAML-ben használt legkülső tagolási elemek nevével (kis/nagybetű érzékenyen!)<br>
     */ 
    private Arfolyam arfolyam;

    /**
     * Az árfolyamokat kiolvasó osztály<br>
     */
    public static class Arfolyam {
        private Map<String, Double> values;

        public Map<String, Double> getValues() {
            return Collections.unmodifiableMap(values);
        }

        public void setValues(Map<String, Double> values) {

            this.values = values;
        }
    }    

    /**
     * Az árfolyam osztályt adja vissza, amelynek egy List Invoice tagváltozója van!<br>
     * @return egy Arfolyam osztály példány<br>
     */
    public Arfolyam getArfolyam() {
        return arfolyam;
    }

    public void setArfolyam(Arfolyam arfolyam) {
        this.arfolyam = arfolyam;
    }    

    // 2. osztály tagváltozó    
    private List<Invoice> invoices;
    
    public static class Invoice {
    
        private Main main;
        private List<Item> items;

        public static class Main {
            private String sorszam;
            private int kod;
            private String keszult;

            public String getSorszam() {
                return sorszam;
            }

            public void setSorszam(String sorszam) {
                this.sorszam = sorszam;
            }

            public int getKod() {
                return kod;
            }

            public void setKod(int kod) {
                this.kod = kod;
            }

            public String getKeszult() {
                return keszult;
            }

            public void setKeszult(String keszult) {
                this.keszult = keszult;
            }
        }

        public static class Item {
            private int kod;
            private int mennyiseg;

            public int getKod() {
                return kod;
            }

            public void setKod(int kod) {
                this.kod = kod;
            }

            public int getMennyiseg() {
                return mennyiseg;
            }

            public void setMennyiseg(int mennyiseg) {
                this.mennyiseg = mennyiseg;
            }
        }

        public Main getMain() {
            return main;
        }

        public void setMain(Main main) {
            this.main = main;
        }

        public List<Item> getItems() {
            return Collections.unmodifiableList(items);
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }
    }

    public List<Invoice> getInvoices() {
        return Collections.unmodifiableList(invoices);
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    /**
     * Ez a megoldás a kivételkezeléssel "oldja meg" az esetlegesen bekövetkező problémákat<br>
     * a yml fájl tartalom olvasása esetén,<br> 
     * így azonban (vegyük észre), hogy a kivételeket át kell adni,<br> 
     * és a példányosítás, illetve az adatbetöltés helyén kell a try(){..}catch(){..} -et alkalmazni!<br>
     * tehát a hívó helyen... szemben a "DatabaseConfig.java" hasonló megoldásával,<br> 
     * ahol maga az osztály elvégzi a teljes hibakezelési folyamatot!<br>
     * @param filePath fájl utvonal és megnevezése
     * @return visszaadja az számlakonfigurációs paraméterek osztályt a tartalommal együtt!<br>
     * @throws IOException I/O hiba esetén áll be,<br>
     */
    static InvoceConfigOsztaly loadConfig(String filePath) throws IOException {
        // Yaml yaml = new Yaml();
        // InvoceConfigOsztaly config = yaml.load(InvoceConfigOsztaly.class.getClassLoader().getResourceAsStream("szamlakeszul.yml"));
        // return config;        
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(filePath), InvoceConfigOsztaly.class);
    }

    /**
     * Elvégezteti a számlaparaméterek beolvastatását a "loadConfog()" metódussal -<br> 
     * Mivel így több a kód, ez pazarló, felesleges megoldás (nem CC), az egészet be lehetne ide húzni egybe...<br>
     * @return visszaadja az számlakonfigurációs paraméterek osztályt a tartalommal együtt!<br>
     * @throws IOException I/O hiba esetén áll be,<br>
     */
    public InvoceConfigOsztaly getInvoceConfigOsztaly() throws IOException {
        return loadConfig("szamlakeszul.yml");
    }
}