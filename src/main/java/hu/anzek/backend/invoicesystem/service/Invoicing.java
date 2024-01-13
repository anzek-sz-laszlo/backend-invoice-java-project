/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.invoicesystem.service;


import hu.anzek.backend.invoicesystem.configuration.DatabaseConfig;
import hu.anzek.backend.invoicesystem.configuration.InvoceConfigOsztaly;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * A szamlazas üzleti logika<br>
 * @author User
 */
public class Invoicing {

    private InvoceConfigOsztaly config = null;
    private DatabaseConfig configDb = null;
    private List<InvoceConfigOsztaly.Invoice> invoiceList = null;
    private InvoceConfigOsztaly.Arfolyam arfolyam = new InvoceConfigOsztaly.Arfolyam();    
    
    /**
     * Ez egy nem statikus (példányszintű) inicializációs blokk.<br>
     * Mint tudjuk ez akkor fut le, amikor az osztály példányosítása, és csaikis egyetlen egyszer.<br>
     * Ez nem egy netódus, hanem egy szolgáltatás<br>
     * Itt egy POJO osztály jön létre (a "config" példánnyal) az adatokból amit a YAML struktúra szerint kap meg:<br>
     * A POJO -ról bővebben a prezentációban (week05 - pdf):<br>
     */ 
    {
        // a JDBC csatlakozáshoz szükséges adatok:
        configDb = new DatabaseConfig().getDatabaseConfig();
        
        try {
            // a számlák előállításához szükséges (beégetett) adatok:
            config = new InvoceConfigOsztaly().getInvoceConfigOsztaly();
        } catch (IOException ex) {
            // ha nem találjuk meg a két konfigurációs fájlt (vagy bármelyiket) hibát dob 
            // a loggolási célt szolgálja a JAVA Logger osztály (a java.util osztlykönyvtár részeként):             
            // a hibát áatdja az "ex" referencia (objektum) változónak (persze ez bármi is lehet)
            // ennek a példánynak lesz számos funkciója, nézzük meg miket tud.
            // ez a stack-et loggolja ki (olyan csúnyán, ráadásul még azt is kiírja, hogy ...és további 167 hiba.)
            Logger.getLogger(Invoicing.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
    /**
     * A számlázás üzleti logika onstruktora<br>
     */
    public Invoicing() {
    }   

    public void readSzamlakeszulActualConfigStatus() throws IOException, IllegalArgumentException, IllegalAccessException{
 
        // Itt következik az árfolyam számítas:
        arfolyam = config.getArfolyam();
        
        // a "szamlakeszul.yml" szerint elkeszítendő számlák:
        invoiceList = config.getInvoceConfigOsztaly().getInvoices(); 
        
        // Számlakészítés:
        // most még minden 27% áfa lesz!
        // Ugy készülnek el, hogy az egyes "számlák" előállításához szükséges adatokon iterálunk:
        for(int i=0; i < invoiceList.size(); i++){
            System.out.println( i + ". szamla lesz a : " + invoiceList.get(i).getMain().getSorszam() );
        }
        
        for (InvoceConfigOsztaly.Invoice invoice : invoiceList) {
            // a szamla egyedi adattagok
            // InvoceConfigOsztaly.Invoices.Invoice.Main main = invoice.getMain();
            InvoceConfigOsztaly.Invoice.Main main = invoice.getMain();
            System.out.println("Az aktualis szamla a(z) : " + main.getSorszam() + " sorszamu");
            
            // a szamla egyedi adattagok között még ez is egy osztály, amely egyetlen eleme egy Lista objektum:
            // InvoceConfigOsztaly.Invoices.Invoice.Items items = invoice.getItems();          
            List<InvoceConfigOsztaly.Invoice.Item> items = invoice.getItems();   
            // például iteráljunk a cikkelemeken úgy, hogy:
            // 1, keressük meg van-e benne 2 cikkód (ha igen, vagy több is azt mindet szedjük fel)
            // 2, és azután írjuk ki a mennyisége(i)t:
            items.stream()
                 .filter(e -> e.getKod()==2)
                 .forEach(e1 -> System.out.println("cikk = " + e1.getKod() + " menny= " + e1.getMennyiseg()));
            
            // or:
            // invoice.getItems().stream().filter(e -> e.getKod()==2).forEach(e-> System.out.println("cikk = " + e.getMennyiseg()));
            // itt számlázzuk le az épp esedékes számlát...
        }
        
        // kiíratjuk a konfigurációs objektumaink szerkezetét:
        objektumunkSzerkezete(config);        
        objektumunkSzerkezete(configDb);
        objektumunkSzerkezete(configDb.getDatabase());        
    }
       
    /**
     * A fő számlázó metódusunk<br>
     * @throws IOException olvaási hiba (amit átdob a benne meghívott "readSzamlakeszulActualConfigStatus()"<br>
     * @throws IllegalArgumentException érévnytelen argumentum(amit szintén átdob ide a "readSzamla.." metódus<br>  
     * @throws IllegalAccessException érvénytelen hozzáférés (amit szintén átdob ide a "readSzaml..." metódus<br>   
     */
    public void szamlazzunkMainMethod() throws IOException, IllegalArgumentException, IllegalAccessException{
    
        // 1. lépés aktualizálás, paraméterek inicializálása:
        readSzamlakeszulActualConfigStatus();
    }
        
    /**
     * Ez a metódus privát (csak a saját osztályában hozzáférhető)<br>
     * Lekérdezi a (feltételezzük, hogy számunkra ismeretlen szerkezetű) objektumunk tagváltozóit<br>
     * @param object egy Object típusú objektumot vár bemenetre<br>
     * @throws IllegalArgumentException ha a metódusnak nem megfelelőek a paraméterei akkor beáll<br>
     * @throws IllegalAccessException ha hozzáférési jogosultsági problémák adódnak akkor beáll<br>
     */
    private static void objektumunkSzerkezete(Object object) throws IllegalArgumentException, IllegalAccessException {
                       
        if(object != null){
            // Létrehozunk egy "Class" objektumot, 
            // olyat, amely reprezentálja az bejövő object típusú objektumunk osztályát és ezt követően
            // már a "myClass" (az "object.getClass()" metódus által) megkapja az objektum tényleges szerkezetét, 
            // lényegében a "myClass" olyan példány, amelyben egy osztály objektum "leírása szerepel", 
            // pl a mezőneke és azok típusai, stb. továbbá hozzájuk tartozó getter metódusok, amelyek ki tudják olvasni.
            Class<?> myClass = object.getClass();
            System.out.println("A lekerdezett osztalyunk [ " + myClass.getName() + " ] mezoi- es azok ertekei: "); 
            // a bejövő osztálypéldányunkban deklarált "mezőkön" iterálunk:
            // maga a mező is objektum!
            for (Field field : myClass.getDeclaredFields()) {
                // fontos:
                // Itt beállítjuk a mező hozzáférési jogosultságot igazra. Vagyis engedélyezzük a hozzáférést!
                // ...ez azért szükséges, mert alapértelmezés szerint a private vagy protected mezőhöz való hozzáférés tiltott 
                // és ezzel a sorral ezt a tiltást feloldjuk:
                field.setAccessible(true);
                // Elkérjük a for ciklus által épp aktuális "mező" objektumot az object típusú referenciától a get metódussal. 
                // Az értékét pedig egy másik, általános object típusú referenciba mentjük:
                // ez nem szükséges lépés, de miért is ne...
                Object fieldObjektum = field.get(object);
                // Kiírja a mező nevét, a típusát és az értékét a konzolra:
                System.out.println( field.getName() + " - " + 
                                    field.getType() + ": " + 
                                    (fieldObjektum != null 
                                     ? fieldObjektum.toString() 
                                     : "null" )
                );
            }
        }else{
            System.out.println("A lekerdezett osztalyunk null erteku referencia!"); 
        }
        System.out.println("...<<");
    }    

    public InvoceConfigOsztaly getSzamlakKonfiguraciosOsztalya() {
        return config;
    }

    public List<InvoceConfigOsztaly.Invoice> getSzamlakListaja() {
        return Collections.unmodifiableList(invoiceList);
    }

    public InvoceConfigOsztaly.Arfolyam getSzamlakArfolyama() {
        return arfolyam;
    }
    
}
