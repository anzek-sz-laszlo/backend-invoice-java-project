/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package hu.anzek.backend.invoicesystem;


import hu.anzek.backend.invoicesystem.model.Cikk;
import hu.anzek.backend.invoicesystem.model.Vevo;
import hu.anzek.backend.invoicesystem.repository.CikkBeolvas;
import hu.anzek.backend.invoicesystem.repository.JdbcCikkReadRepo;
import hu.anzek.backend.invoicesystem.repository.VevoBekerese;
import hu.anzek.backend.invoicesystem.repository.VevoCikkRepo;
import hu.anzek.backend.invoicesystem.service.Invoicing;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


/**
 * Szovegfajlos szamlazo rendszer<br>
 * @author User
 */
public class InvoiceSystem {
    // A számlázó tagváltozói:
    // Fontos!
    // ha itt végezzük el a fő-osztályban a folyamt "oroszlánrészét"
    // akkor  "static" típusú osztálytagváltozókkal hivatkozhatjuk meg az itt, futásidőben létrehozandó referenciákat. 
    public static final String CURRENT_DATE = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
    private static CikkBeolvas cikkBeolvas;
    private static JdbcCikkReadRepo jdbcCikkOlvaso;
    private static VevoBekerese vevoBekerese;    
    private static VevoCikkRepo<Cikk> cikkRepo;
    private static VevoCikkRepo<Vevo> vevoRepo;

    void szamlazo(){
    }
    
    public static void main(String[] args) {
        
        System.setProperty("file.encoding", "UTF-8");
        System.out.println("Az Invoice-szovegfajlos szamlazo elindult------------------------------!");
        // 1, cikkeket előkészítő modul:
        //      vagyis a txt fálj tartalmának beolvasása
        // 1/2, a cikk adatok átírása a SQL-be (ahogyan a "MySQLRepositoryTest()" osztályban csináltuk...)
        CikkBeolvas cikkBeolvas = new CikkBeolvas(); 
        // A cikkeknek az Sql-be való kiíratáshoz kell az implementáció (szóval ez csak az implementáció)..
        cikkRepo = new VevoCikkRepo<Cikk>() {
            @Override
            public List<Cikk> beolvasTxtAdattarbol(String fajlEleres) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
        // Alább pedig ugyan az, mint fent, csak másképp...
        // Figyeljük meg az eddigiekhez képest itt megfordult a dolog... most épp nem lambda kifejezéssé alakult, hanem
        // az anonim lambda kifejezés helyett lett egy valós, 
        // néven hivatkozott belső osztály lett:      
        // a kód itt rögtön olvashatóbb lett!
        cikkRepo = new CikkRepoImpl(); 
        if( ! cikkBeolvas.getCikkList().isEmpty()){
            // az adatbázisba INSERT-el (persze csak ha még nem tettük meg, mert az INSERT nem ír felül és nem duplikál:
            cikkBeolvas.getCikkList().forEach(e -> {                                                       
                                                        try {
                                                            cikkRepo.sqlInsert(e);
                                                        } catch (SQLException ex) {
                                                            ex.printStackTrace();
                                                        }
                                                    }
            );
            // teszteljük úgy, hogy a kiírt adatok közül visszaolvasunk egyet, amiről tudjuk milyen értékelkkel kell rendelkeznie:
            Optional<List<Cikk>> optCikkLista = jdbcCikkOlvaso.findAll();
            if ( ( ! optCikkLista.isEmpty()) || (optCikkLista.isPresent())) {
                // visszaolvastuk az összes cikk-adatát, hogy "kéznél legyen"
                // nyilván ilyet nem csinálunk... de most jó lesz:
                List<Cikk> cikkLista = optCikkLista.get();                
            }
        }
        
        // 2, vevőket előkészítő modul:
        //      vagyis a txt fájl tartalmának a beolvasása
        // 2/2, a vevő adatok átírása a SQL-be (ahogyan a "MySQLRepositoryTest()" osztályban csináltuk...)
        VevoBekerese vevoBekerese = new VevoBekerese();
        // A vevők Sql-be való kiíratáshoz kell az implementáció (szóval ez csak az implementáció)..
        // itt a lambda "módszert" el is hagyom már...
        vevoRepo = new VevoRepoImpl(); 
        
        // 3, 
        // 3, a számlázó főmodul meghívása:
        Invoicing invocing = new Invoicing(); 
    }
    
    /**
     * Az anonim lambda kifejezés helyett egy valós,<br> 
     * néven hivatkozott belső osztályt hoztunk létre<br>
     */
    static class CikkRepoImpl implements VevoCikkRepo<Cikk> {
        public CikkRepoImpl() {
        }

        @Override
        public List<Cikk> beolvasTxtAdattarbol(String fajlEleres) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
    
    /**
     * Az anonim lambda kifejezés helyett egy valós,<br> 
     * néven hivatkozott belső osztályt hoztunk létre<br>
     */
    static class VevoRepoImpl implements VevoCikkRepo<Vevo> {
        public VevoRepoImpl() {
        }

        @Override
        public List<Vevo> beolvasTxtAdattarbol(String fajlEleres) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }    
}
