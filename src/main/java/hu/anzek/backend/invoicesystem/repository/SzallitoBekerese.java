/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.invoicesystem.repository;


import hu.anzek.backend.invoicesystem.model.Szallito;
import hu.anzek.backend.invoicesystem.repository.SzallitoRepo;
import hu.anzek.backend.invoicesystem.service.SzamlaKeszultIdopontja;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


/**
 * A számlakiállító adatainak tényleges beolvasása<br>
 * DE FIGYELEM!<br>
 * Ez a kód (is) ahogy a többi is számos felesleges kiíratást (System.out...) tartalmaz,<br> 
 * ...ezeket a tényleges implementációban hagyjunk el!<br> 
 * Sértjük vele a CC (clearCode) elvet!<br>
 * Utóbb (legalább) majd a kódból töröljük azokat a részeket, amelyek csak debug,<br> 
 * vagy egyéb, tesztelési célokat szolgálnak!<br> 
 * Továbbá:<br>
 * In-line kommentekről :<br> 
 * Az in-line kommentek lehetőleg ne tartalmazzanak hosszú üres sorokat!<br> 
 * ...ne tartalamzzon felesleges szöveget, amely nem ad hozzá a kódértelmezéshez!<br>*
 * @author User
 */
public class SzallitoBekerese{
                  
    /**
     * Ez is egy "sima" Tagváltozó deklaráció (bármilyen furcsa is)<br>
     * mintha csak ezt mondtuk volna:<br>
     * int i = 1;<br> 
     * A funkcionális interfész "body"-jának létrehozása:<br>
     * Lambda kifejezéssel!<br>
     */
    private final SzallitoRepo szallitoRepo = (fajlEleres) -> {        
        Szallito readedSzallito = null;
    
        /**
         * A FileReader osztaly egy karakteres beolvasot biztosit, amely a karaktereket egy fajlbol olvassa be<br>
         * Ugyanakkor a BufferedReader osztaly egy karakterfolyamot biztosit egy bemeneti karakterfolyam folepitesevel,<br> 
         * amely egy Buffer-t hasznal a karakterek memoriaban torteno pufferezesere.<br>
         * Alternatívák vannak még... a Reader abstract osztály implementációjával:<br>
         * new BufferedReader( Reader );<br>
         * ahol a "Reader" egy abstract osztály és számos implementációja létezik...<br>
         * ... és az "InputStreamReader" az egyike annak amely a "Reader" implementációját biztosítja<br> 
         * ...és ez osztály InputStream-ként, karakterek olvasására képes!<br>
         */
        try (
                BufferedReader reader = new BufferedReader( 
                                                            new InputStreamReader( new FileInputStream(fajlEleres), 
                                                                                   StandardCharsets.UTF_8
                                                                                 )
                                                           ) 
            ) {
            String adoszam = null;
            String megnevezes = null;
            String cim = null;
            String deviza = null;    
            String line;
            /**
             * A While() -on beluli resz beagyazott resz! <br>
             * Figyelmeztetessel jar<br>
             * El kell nyomnunk...<br>
             * (Nested assignment 'line = reader.readLine()')<br>
             */
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String fieldName = parts[0].trim();
                    String value = parts[1].trim();

                    switch (fieldName) {
                        case "adoszam":
                            adoszam = value;
                            break;
                        case "megnevezes":
                            megnevezes = value;
                            break;
                        case "cim":
                            cim = value;
                            break;
                        case "deviza":
                            deviza = value;
                            break;
                        default:
                            System.out.println("Ismeretlen mezo: " + fieldName);
                            break;
                    }
                } else {
                    System.out.println("Hibas adatsor parts.length= " + parts.length + " adatValue(" + line + ")");
                }
            }

            readedSzallito = new Szallito(adoszam, megnevezes, cim, deviza);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return readedSzallito;           
    };

    /** 
     * A "SzallitoBekerese()" osztály<br>
     * konstruktora<br>
     */
    public SzallitoBekerese() {
    }
    
    /**
     * Kiolvassa a szallitot.<br>
     * "BufferedReader" : olyan osztaly a java.io csomagban, amely szovegfajlok olvasasat teszi lehetove<br>
     * "FileReader" : egy osztaly a java.io csomagban, karaktereket olvas karakterfolyamot hoz letre.<br>
     * Tehat, amikor a kettot kombinalod, egy hatekony szovegfajl-olvasot kaphatsz!<br>
     * @return a Szallito record egy példányát adja vissza közvetlenül!<br>
     */  
    public Szallito szallito(){
            
        // Ebben az esetben a szallitoRepo.beolvasTxtAdattarbol("szallito.txt") kifejezés 
        // meghívja a SzallitoRepo interfész beolvasTxtAdattarbol metódusát, 
        // és a metódus visszatér a record típusú "Szallito" példánnyal        
        return szallitoRepo.beolvasTxtAdattarbol("szallito.txt");
    }
    
    /**
     * Egy kész "Szallito" rekord példányt ad vissza!<br>
     * Az előbbi és ez a metódus kódrészlete közötti különbség csak a kódszerkezetben rejlik<br> 
     * illetve a hozzáférési pontokban van, de az eredmény azonos lesz!<br>
     * @return visszaadja a szállító rekord egy példányát, de a SzallitoRepo interfész implementációján keresztül!<br>
     */
    public Szallito getSzallito(){
        SzallitoRepo repo = getSzallitoRepo();   
        return repo.beolvasTxtAdattarbol("szallito.txt");    
    }
    
    /**
     * Ez visszadja a funkcionális interfészünk egy példányát<br> 
     * Mégpedig a metódus implementációjával együtt.<br>
     * Ezt a megoldást gyakran láthatjuk az importált könyvtárak,API-k implementációjaként való hivatkozásra!<br>    
     * @return 
     * a "SzallitoRepo" típusú objektumot adja vissza!<br>
     */
    public SzallitoRepo getSzallitoRepo() {

        return szallitoRepo;
    }    
}
