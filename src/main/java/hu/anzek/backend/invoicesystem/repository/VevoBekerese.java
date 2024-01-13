/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.invoicesystem.repository;

import hu.anzek.backend.invoicesystem.model.Vevo;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * A Vevők beolvasása osztály<br>
 * DE FIGYELEM!<br>
 * Ez a kód (is) ahogy a többi is számos felesleges kiíratást (System.out...) tartalmaz,<br> 
 * ...ezeket a tényleges implementációban hagyjunk el!<br> 
 * Sértjük vele a CC (clearCode) elvet!<br>
 * Utóbb (legalább) majd a kódból töröljük azokat a részeket, amelyek csak debug,<br> 
 * vagy egyéb, tesztelési célokat szolgálnak!<br> 
 * Továbbá:<br>
 * In-line kommentekről :<br> 
 * Az in-line kommentek lehetőleg ne tartalmazzanak hosszú üres sorokat!<br> 
 * ...ne tartalamzzon felesleges szöveget, amely nem ad hozzá a kódértelmezéshez!<br>
 * @author User
 */
public class VevoBekerese{
    
    /**
     * Ez is egy "sima" Tagváltozó deklaráció (bármilyen furcsa is)<br>
     * mintha csak ezt mondtuk volna:<br>
     * int i = 1;<br>
     * A funkcionális interfész "body"-jának létrehozása:<br>
     * Lambda kifejezéssel!<br>
     */
    private final VevoCikkRepo<Vevo> vevoRepo = (fajlEleres) -> {        
        // Inicializálnunk kell, mert "NullPointerException" -t kaphatunk.
        // így meg üres lsitát vagyis (listaVevo.size()==0, vagy listaVevo.isEmpty())
        final List<Vevo> listaVevo = new ArrayList<>();
        
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
        //try (BufferedReader reader = new BufferedReader(new InputStreamReader( new FileInputStream(fajlEleres), 
        //                                                                       StandardCharsets.UTF_8)
        //                                                     )) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fajlEleres))) {
  
            long kod = 0L;
            String adoszam = null;
            String megnevezes = null;
            String cim = null;
            String deviza = null;    
            int fizmod = 0;
            int fizhatido = 0;
            int engedmeny = 0;
            int mezoIndex = 0;
            String line;

            /**
             * A While() -on beluli resz beagyazott resz! <br>
             * Figyelmeztetessel jar<br>
             * El kell nyomnunk...<br>
             * (Nested assignment 'line = reader.readLine()')<br>
             */
            while ((line = reader.readLine()) != null) {
                
                System.out.println("LINE = > " + line);
                
                if ( ! line.trim().isEmpty()) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {

                        String fieldName = parts[0].trim();
                        String value = parts[1].trim();
                        
                        switch (fieldName) {
                            case "kod":
                                kod = Long.parseLong(value);
                                mezoIndex++;
                                break;
                            case "adoszam":
                                adoszam = value;
                                mezoIndex++;
                                break;
                            case "megnevezes":
                                megnevezes = value;
                                mezoIndex++;
                                break;
                            case "cim":
                                cim = value;
                                mezoIndex++;
                                break;
                            case "deviza":
                                deviza = value;
                                mezoIndex++;
                                break;
                            case "fizmod":
                                fizmod = Integer.parseInt(value);
                                break;
                            case "fizhatido":
                                fizhatido = Integer.parseInt(value);
                                mezoIndex++;
                                break;
                            case "engedmeny":
                                engedmeny = Integer.parseInt(value);
                                mezoIndex++;
                                break;
                            default:
                                System.out.println("Ismeretlen mezo: " + fieldName);
                                break;
                        }               
                    } else {
                        System.out.println("Hibas adatsor parts.length= " + parts.length + " adatValue(" + line + ")");
                    }
                }else if (mezoIndex==7) {
                    // Minden változó értéket kapott, tehát valószínűleg rendben vagyunk:
                    // itt most nem vizsgáljuk a további hibalehetőségeket,
                    // nem vizsgáljuk pl, ha hiányzik adatsor, vagy az egyéb lehetséges adathibákat...
                    listaVevo.add(new Vevo(kod, adoszam, megnevezes, cim, fizmod, fizhatido, engedmeny, deviza));
                    System.out.println("A kiolvasott vevo adatsor = " + listaVevo.get(listaVevo.size() -1) + 
                                       "\n- a listank uj merete: " + listaVevo.size());
           
                    // Nullázni az értékeket, hogy ne befolyásolja a következő rekordot
                    kod = 0L;
                    mezoIndex = 0;
                    adoszam = null;
                    megnevezes = null;
                    cim = null;
                    deviza = null;
                    fizmod = 0;
                    fizhatido = 0;
                    engedmeny = 0;
                }
            }
            if (mezoIndex==7) {
                // Minden változó értéket kapott, tehát valószínűleg rendben vagyunk:
                // itt most nem vizsgáljuk a további hibalehetőségeket,
                // nem vizsgáljuk pl, ha hiányzik adatsor, vagy az egyéb lehetséges adathibákat...
                listaVevo.add(new Vevo(kod, adoszam, megnevezes, cim, fizmod, fizhatido, engedmeny, deviza));
                System.out.println("A kiolvasott vevo adatsor = " + listaVevo.get(listaVevo.size() -1) + 
                                   "\n- a listank uj merete: " + listaVevo.size());
            }            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaVevo;           
    };

    /** 
     * A "VevoBekerese()" osztály<br>
     * konstruktora<br>
     */
    public VevoBekerese() {
    }
    
    /**
     * Kiolvassa a vevőlistát!<br>
     * Felhasználja a
     * Tehat, amikor a kettot kombinalod, egy hatekony szovegfajl-olvasot kaphatsz!<br>
     * @return a Vevo record egy példányát adja vissza közvetlenül!<br>
     */  
    public List<Vevo> vevo(){
            
        // Ebben az esetben a vevoRepo.beolvasTxtAdattarbol("vevo.txt") kifejezés 
        // meghívja a VevoCikkRepo interfész beolvasTxtAdattarbol metódusát, 
        // és a metódus visszatér a record típusú "Vevo" példánnyal        
        return vevoRepo.beolvasTxtAdattarbol("vevo.txt");
    }
    
    /**
     * Egy kész "Vevo" rekord példányt ad vissza!<br>
     * Az előbbi és ez a metódus kódrészlete közötti különbség csak a kódszerkezetben rejlik<br> 
     * illetve a hozzáférési pontokban van, de az eredmény azonos lesz!<br>
     * @return visszaadja a szállító rekord egy példányát, de a VevoCikkRepo interfész implementációján keresztül!<br>
     */
    public List<Vevo> getVevo(){
        VevoCikkRepo<Vevo> repo = getVevoRepo();   
        return repo.beolvasTxtAdattarbol("vevo.txt");    
    }
    
    /**
     * Ez visszadja a funkcionális interfészünk egy példányát<br> 
     * Mégpedig a metódus implementációjával együtt.<br>
     * Ezt a megoldást gyakran láthatjuk az importált könyvtárak,API-k implementációjaként való hivatkozásra!<br>    
     * @return a "VevoCikkRepo" típusú objektumot adja vissza!<br>
     */
    public VevoCikkRepo<Vevo> getVevoRepo() {

        return vevoRepo;
    } 
}
